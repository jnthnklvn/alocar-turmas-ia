package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;
import aima.alocar_aulas.csp.Turma;

public class HorarioIndisponivelProfessorConstraint implements Constraint<Variable, String> {
	
	private List<Variable> scope;
	private List<Turma> turmas;
	private Variable professor;
	private String horario;
	
	public HorarioIndisponivelProfessorConstraint(Variable professor, String horario, List<Turma> turmas) {
		
		this.scope = new ArrayList<Variable>();
		this.professor = professor;
		this.horario = horario;
		this.turmas = turmas;
		
		this.scope.add(professor);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Variable getProfessor() {
		return professor;
	}
	
	public String getHorario() {
		return horario;
	}
	
	public List<Turma> getTurmas() {
		return turmas;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {
		
		String valueProfessor = (String) assignment.getValue(professor);
		
		if (valueProfessor != null) {
			
			TurmaProfessorConstraint constraint = new TurmaProfessorConstraint(turmas, valueProfessor);
			
			List<Variable> horarios = constraint.getHorariosByProfessor(valueProfessor, assignment);
			
			for (Variable horario : horarios) {
				
				String valueHorario = (String) assignment.getValue(horario);
				
				if (valueHorario != null) {
					
					if (valueHorario.equals(getHorario())) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
}