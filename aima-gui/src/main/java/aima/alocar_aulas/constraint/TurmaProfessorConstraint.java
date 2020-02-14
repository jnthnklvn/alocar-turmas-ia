package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import aima.alocar_aulas.csp.Turma;

public class TurmaProfessorConstraint implements Constraint<Variable, String> {

	private List<Variable> scope;
	private List<Turma> turmas;
	String professor;

	public TurmaProfessorConstraint(List<Turma> turmas, String professor) {

		this.scope = new ArrayList<Variable>();
		this.turmas = turmas;
		this.professor = professor;

		for (Turma turma : turmas) {

			this.scope.add(turma.getProfessor());

			for (Variable horario : turma.getHorarios()) {
				this.scope.add(horario);
			}
		}
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	public List<Variable> getHorariosByProfessor(String professor, Assignment<Variable, String> assignment) {

		List<Variable> list = new ArrayList<Variable>();

		for (Turma timeturma : turmas) {

			String prof = (String) assignment.getValue(timeturma.getProfessor());

			if (prof != null && prof.equals(professor)) {
				list.addAll(timeturma.getHorarios());
			}
		}

		return list;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {

		List<Variable> horarios = getHorariosByProfessor(professor, assignment);

		return new AllDifferentConstraint(horarios).isSatisfiedWith(assignment);
	}
}
