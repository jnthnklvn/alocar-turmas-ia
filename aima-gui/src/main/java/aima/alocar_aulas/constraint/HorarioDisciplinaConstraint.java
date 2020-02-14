package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class HorarioDisciplinaConstraint implements Constraint<Variable, String> {

	Variable disciplina1;
	Variable horario1;
	Variable disciplina2;
	Variable horario2;

	private List<Variable> scope;

	Map<Integer, String[]> periodos;

	public HorarioDisciplinaConstraint(Variable disciplina1, Variable horario1, Variable disciplina2,
			Variable horario2) {

		this.scope = new ArrayList<Variable>();

		this.disciplina1 = disciplina1;
		this.horario1 = horario1;
		this.disciplina2 = disciplina2;
		this.horario2 = horario2;

		this.scope.add(disciplina1);
		this.scope.add(horario1);
		this.scope.add(disciplina2);
		this.scope.add(horario2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {

		String valueDisciplina1 = (String) assignment.getValue(disciplina1);
		String valueDisciplina2 = (String) assignment.getValue(disciplina2);

		if (valueDisciplina1 != null && valueDisciplina2 != null) {

			String valueHorario1 = (String) assignment.getValue(horario1);
			String valueHorario2 = (String) assignment.getValue(horario2);

			if (valueHorario1 != null && valueHorario2 != null) {

				if (valueHorario1.equals(valueHorario2))
					return false;
			}
		}

		return true;
	}

}
