package aima.alocar_aulas.env;

import aima.core.search.framework.Node;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.Problem;
import aima.core.util.datastructure.Pair;
import aima.core.util.datastructure.XYLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Provides useful functions for two versions of the n-queens problem. The
 * incremental formulation and the complete-state formulation share the same
 * RESULT function but use different ACTIONS functions.
 *
 * @author Ruediger Lunde
 */
public class AulasFunctions {

	public static Problem<AulasBoard, AulaAction> createIncrementalFormulationProblem(int boardRows, int boardColumns) {
		return new GeneralProblem<>(new AulasBoard(boardRows, boardColumns), AulasFunctions::getIFActions,
				AulasFunctions::getResult, AulasFunctions::testGoal);
	}

	public static Problem<AulasBoard, AulaAction> createCompleteStateFormulationProblem(int boardRows, int boardColumns,
			Pair<String, XYLocation> l) {
		return new GeneralProblem<>(new AulasBoard(boardRows, boardColumns, l), AulasFunctions::getCSFActions,
				AulasFunctions::getResult, AulasFunctions::testGoal);
	}

	/**
	 * Implements an ACTIONS function for the incremental formulation of the aulas
	 * problem.
	 * <p>
	 * Assumes that queens are placed column by column, starting with an empty
	 * board, and provides queen placing actions for all non-attacked positions of
	 * the first free column.
	 */
	public static List<AulaAction> getIFActions(AulasBoard state) {
		List<AulaAction> actions = new ArrayList<>();
		for (int i = 0; i < state.getRows(); i++) {
			for (int j = 0; j < state.getColumns(); j++) {
				if (!(state.AulaExistsAt(i, j))) {
					XYLocation newLocation = new XYLocation(i, j);
					for (String aula : state.getAulasPendentes()) {
						actions.add(new AulaAction(AulaAction.MOVE_AULA, newLocation, aula));
					}
				}
			}
		}
		return actions;
	}

	/**
	 * Implements an ACTIONS function for the complete-state formulation of the
	 * n-queens problem.
	 * <p>
	 * Assumes exactly one queen in each column and provides all possible queen
	 * movements in vertical direction as actions.
	 */
	public static List<AulaAction> getCSFActions(AulasBoard state) {
		List<AulaAction> actions = new ArrayList<>();
		for (int i = 0; i < state.getRows(); i++) {
			for (int j = 0; j < state.getColumns(); j++) {
				if (!state.AulaExistsAt(i, j)) {
					XYLocation loc = new XYLocation(i, j);
					for (String aula : state.getAulasPendentes()) {
						actions.add(new AulaAction(AulaAction.MOVE_AULA, loc, aula));
					}
				}
			}
		}
		return actions;
	}

	/**
	 * Implements a RESULT function for the n-queens problem. Supports queen
	 * placing, queen removal, and queen movement actions.
	 */
	public static AulasBoard getResult(AulasBoard state, AulaAction action) {
		AulasBoard result = new AulasBoard(state.getRows(), state.getColumns());
		result.setAulasAt(state.getAulasPositions());
		if (Objects.equals(action.getName(), AulaAction.PLACE_AULA))
			result.addAula(action.getAula(), action.getLocation());
		else if (Objects.equals(action.getName(), AulaAction.REMOVE_AULA))
			result.removeAulaFrom(action.getAula(), action.getLocation());
		else if (Objects.equals(action.getName(), AulaAction.MOVE_AULA))
			result.addAula(action.getAula(), action.getLocation());
		// if action is not understood or is a NoOp
		// the result will be the current state.
		return result;
	}

	/**
	 * Implements a GOAL-TEST for the n-queens problem.
	 */
	public static boolean testGoal(AulasBoard state) {
		return state.getAulasPendentes().size() == 0;
	}

	/**
	 * Estimates the distance to goal by the number of attacking pairs of queens on
	 * the board.
	 */
	public static double getNumberOfAttackingPairs(Node<AulasBoard, AulaAction> node) {
		return node.getState().getAulasPendentes().size();
	}
}
