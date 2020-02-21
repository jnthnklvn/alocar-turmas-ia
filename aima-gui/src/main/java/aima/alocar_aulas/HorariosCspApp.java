package aima.alocar_aulas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import aima.alocar_aulas.csp.AlocaTurma;
import aima.alocar_aulas.model.ProfessorAndDisciplinas;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.Variable;

public class HorariosCspApp {

	public static void main(String[] args) {

		double inicio = System.currentTimeMillis();
		FlexibleBacktrackingSolver<Variable, String> fBSolver = new FlexibleBacktrackingSolver<>();
		List<String> professores = new ArrayList<String>();
		final String[] professoresPadroes = { "Walter", "Elena", "Evelyn", "Mia", "Robert", "Hulk", "Viuva" };
		List<ProfessorAndDisciplinas> preferenciasProfessores = new ArrayList<ProfessorAndDisciplinas>();
		List<ProfessorAndDisciplinas> habilidadesProfessores = new ArrayList<ProfessorAndDisciplinas>();

		for (int i = 0; i < professoresPadroes.length; i++) {
			List<Integer> habs = new ArrayList<Integer>();
			habs.add(professoresPadroes.length - 1 - i);
			professores.add(professoresPadroes[i]);
			preferenciasProfessores.add(new ProfessorAndDisciplinas(i, new ArrayList<Integer>()));
			habilidadesProfessores.add(new ProfessorAndDisciplinas(i, habs));
		}

		AlocaTurma alocaTurma = new AlocaTurma(professores, preferenciasProfessores, habilidadesProfessores, "QUA_1300",
				"QUA_1350", "SEX_1300", "SEX_1350");
		Optional<Assignment<Variable, String>> solucao = fBSolver.solve(alocaTurma);

		if (solucao.isPresent()) {
			String sol = solucao.get().toString().replace(", P", ":\n\nP").replace(", D", "\n\nD").replace(", ", "\n");
			System.out.println(sol.substring(1, sol.length() - 1));
		}

		double fim = System.currentTimeMillis();
		System.out.println("\nTempo: " + (fim - inicio));
	}
}
