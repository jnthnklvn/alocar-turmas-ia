package aima.alocar_aulas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import aima.alocar_aulas.csp.AlocaTurma;
import aima.alocar_aulas.env.AulasBoard;
import aima.alocar_aulas.view.AulasViewCtrl;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CspHeuristics;
import aima.core.search.csp.CspListener;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.MinConflictsSolver;
import aima.core.search.csp.Variable;
import aima.core.search.csp.inference.AC3Strategy;
import aima.core.search.csp.inference.ForwardCheckingStrategy;
import aima.core.util.datastructure.XYLocation;
import aima.gui.fx.framework.IntegrableApplication;
import aima.gui.fx.framework.Parameter;
import aima.gui.fx.framework.TaskExecutionPaneBuilder;
import aima.gui.fx.framework.TaskExecutionPaneCtrl;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Integrable application which demonstrates how different CSP solution
 * strategies solve the N-Queens problem.
 *
 * @author Ruediger Lunde
 */
public class HorariosCspApp extends IntegrableApplication {

	public static void main(String[] args) {

		double inicio = System.currentTimeMillis();
		FlexibleBacktrackingSolver<Variable, String> fBSolver = new FlexibleBacktrackingSolver<>();
		AlocaTurma alocaTurma = new AlocaTurma();
		Optional<Assignment<Variable, String>> solucao = fBSolver.solve(alocaTurma);
		solucao.ifPresent(System.out::println);
		double fim = System.currentTimeMillis();
		System.out.println("\nTempo: " + (fim - inicio));

		launch(args);
	}

	private final static String PARAM_STRATEGY = "strategy";
	private final static String PARAM_VAR_SELECT = "varSelect";
	private final static String PARAM_VAL_SELECT = "valOrder";
	private final static String PARAM_INFERENCE = "inference";

	private final static String PARAM_ROWS_NUMER = "rowsNumber";
	private final static String PARAM_COLUMNS_NUMER = "columnsNumber";

	private AulasViewCtrl stateViewCtrl;
	private TaskExecutionPaneCtrl taskPaneCtrl;
	private CSP<Variable, String> csp;
	private CspSolver<Variable, String> solver;
	private CspListener.StepCounter<Variable, String> stepCounter = new CspListener.StepCounter<>();

	@Override
	public String getTitle() {
		return "N-Queens CSP App";
	}

	/**
	 * Defines state view, parameters, and call-back functions and calls the
	 * simulation pane builder to create layout and controller objects.
	 */
	@Override
	public Pane createRootPane() {
		BorderPane root = new BorderPane();

		StackPane stateView = new StackPane();
		stateViewCtrl = new AulasViewCtrl(stateView);

		List<Parameter> params = createParameters();

		TaskExecutionPaneBuilder builder = new TaskExecutionPaneBuilder();
		builder.defineParameters(params);
		builder.defineStateView(stateView);
		builder.defineInitMethod(this::initialize);
		builder.defineTaskMethod(this::startExperiment);
		taskPaneCtrl = builder.getResultFor(root);
		taskPaneCtrl.setParam(TaskExecutionPaneCtrl.PARAM_EXEC_SPEED, 0);

		return root;
	}

	protected List<Parameter> createParameters() {
		Parameter p1 = new Parameter(PARAM_STRATEGY, "Backtracking", "Min-Conflicts");
		Parameter p2 = new Parameter(PARAM_VAR_SELECT, "Default", "MRV", "DEG", "MRV&DEG");
		Parameter p3 = new Parameter(PARAM_VAL_SELECT, "Default", "LCV");
		Parameter p4 = new Parameter(PARAM_INFERENCE, "None", "Forward Checking", "AC3");
		p2.setDependency(PARAM_STRATEGY, "Backtracking");
		p3.setDependency(PARAM_STRATEGY, "Backtracking");
		p4.setDependency(PARAM_STRATEGY, "Backtracking");
		Parameter p5 = new Parameter(PARAM_ROWS_NUMER, 6);
		Parameter p6 = new Parameter(PARAM_COLUMNS_NUMER, 5);
		p5.setDefaultValueIndex(0);
		p6.setDefaultValueIndex(0);
		return Arrays.asList(p1, p2, p3, p4, p5, p6);
	}

	/**
	 * Displays the initialized board on the state view.
	 */
	@Override
	public void initialize() {
		csp = new AlocaTurma();
		Object strategy = taskPaneCtrl.getParamValue(PARAM_STRATEGY);
		if (strategy.equals("Backtracking")) {
			FlexibleBacktrackingSolver<Variable, String> bSolver = new FlexibleBacktrackingSolver<>();
			switch ((String) taskPaneCtrl.getParamValue(PARAM_VAR_SELECT)) {
			case "MRV":
				bSolver.set(CspHeuristics.mrv());
				break;
			case "DEG":
				bSolver.set(CspHeuristics.deg());
				break;
			case "MRV&DEG":
				bSolver.set(CspHeuristics.mrvDeg());
				break;
			}
			switch ((String) taskPaneCtrl.getParamValue(PARAM_VAL_SELECT)) {
			case "LCV":
				bSolver.set(CspHeuristics.lcv());
				break;
			}
			switch ((String) taskPaneCtrl.getParamValue(PARAM_INFERENCE)) {
			case "Forward Checking":
				bSolver.set(new ForwardCheckingStrategy<>());
				break;
			case "AC3":
				bSolver.set(new AC3Strategy<>());
				break;
			}
			solver = bSolver;
		} else if (strategy.equals("Min-Conflicts")) {
			solver = new MinConflictsSolver<>(1000);

		}
		solver.addCspListener(stepCounter);
		solver.addCspListener((csp, assign, var) -> {
			if (assign != null)
				updateStateView(getBoard(assign));
		});
		stepCounter.reset();

		stateViewCtrl.update(new AulasBoard(6, 6));
		taskPaneCtrl.setStatus("");
	}

	@Override
	public void cleanup() {
		taskPaneCtrl.cancelExecution();
	}

	/**
	 * Starts the experiment.
	 */
	public void startExperiment() {
		Optional<Assignment<Variable, String>> solution = solver.solve(csp);
		if (solution.isPresent()) {
			AulasBoard board = getBoard(solution.get());
			stateViewCtrl.update(board);
		}
	}

	private AulasBoard getBoard(Assignment<Variable, String> assignment) {
		AulasBoard board = new AulasBoard(6, 6);
		for (Variable var : assignment.getVariables()) {
			// int col = Integer.parseInt(var.getName().substring(1)) - 1;
			// int row = assignment.getValue(var) - 1;
			System.out.println(var);
			System.out.println(assignment.getValue(var));
			board.addAula(assignment.getValue(var).toString(), new XYLocation(0, 0));
		}
		return board;
	}

	/**
	 * Caution: While the background thread should be slowed down, updates of the
	 * GUI have to be done in the GUI thread!
	 */
	private void updateStateView(AulasBoard board) {
		Platform.runLater(() -> {
			stateViewCtrl.update(board);
			taskPaneCtrl.setStatus(stepCounter.getResults().toString());
		});
		taskPaneCtrl.waitAfterStep();
	}
}
