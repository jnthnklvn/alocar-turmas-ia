package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class HorarioDiasIguasConstraint implements Constraint<Variable, String> {
	
	private List<Variable> scope;
	private Variable horario1;
	private Variable horario2;
	
	public HorarioDiasIguasConstraint(Variable horario1, Variable horario2) {
		
		this.scope = new ArrayList<Variable>();
		
		this.horario1 = horario1;
		this.horario2 = horario2;
		
		this.scope.add(horario1);
		this.scope.add(horario2);
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Variable getHorario1() {
		return horario1;
	}
	
	public Variable getHorario2() {
		return horario2;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {
		
		String valueHorario1 = (String) assignment.getValue(horario1);
		String valueHorario2 = (String) assignment.getValue(horario2);
		
		if (valueHorario1 != null && valueHorario2 != null) {
			
			String dia1 = valueHorario1.split("_")[0];
			String dia2 = valueHorario2.split("_")[0];
			
			if (!dia1.equals(dia2))
				return false;
		}
		
		return true;
	}
}
