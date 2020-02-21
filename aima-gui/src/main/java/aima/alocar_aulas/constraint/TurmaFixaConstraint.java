package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.alocar_aulas.csp.Turma;
import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class TurmaFixaConstraint implements Constraint<Variable, String> {

	Variable disciplina;
	String disciplina1;
	String horario1;
	String horario2;
	String horario3;
	String horario4;
	List<Variable> horarios;

	private List<Variable> scope;

	public TurmaFixaConstraint(Turma turma, String disciplina1, String horario1, String horario2, String horario3,
			String horario4) {

		this.scope = new ArrayList<Variable>();

		this.horario1 = horario1;
		this.horario2 = horario2;
		this.horario3 = horario3;
		this.horario4 = horario4;

		this.disciplina = turma.getDisciplina();
		this.disciplina1 = disciplina1;

		this.horarios = turma.getHorarios();

		this.scope.add(disciplina);

		for (Variable horario : horarios) {
			this.scope.add(horario);
		}
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {

		String valueDisciplina = (String) assignment.getValue(disciplina);

		if (valueDisciplina != null && disciplina1 != null) {

			if (horario1 != null && horario2 != null && horario3 != null && horario4 != null) {
				if (valueDisciplina != disciplina1) {
					for (Variable horario : horarios) {
						String valueHorario = (String) assignment.getValue(horario);
						if (valueHorario != null && (valueHorario.equals(horario1) || valueHorario.equals(horario2)
								|| valueHorario.equals(horario3) || valueHorario.equals(horario4))) {
							return false;
						}
					}
				} else {
					for (Variable horario : horarios) {
						String valueHorario = (String) assignment.getValue(horario);
						if (valueHorario != null && (!valueHorario.equals(horario1) && !valueHorario.equals(horario2)
								&& !valueHorario.equals(horario3) && !valueHorario.equals(horario4))) {
							return false;
						}
					}
				}
			}
		}

		return true;
	}
}
