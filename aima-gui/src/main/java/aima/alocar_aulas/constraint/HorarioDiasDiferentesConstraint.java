package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class HorarioDiasDiferentesConstraint implements Constraint<Variable, String> {
	
	private List<Variable> scope;
	private Variable horario1;
	private Variable horario2;
	String [] dias;
	
	public HorarioDiasDiferentesConstraint(Variable horario1, Variable horario2, String [] dias) {
		
		this.scope = new ArrayList<Variable>();
		
		this.horario1 = horario1;
		this.horario2 = horario2;
		
		this.scope.add(horario1);
		this.scope.add(horario2);
		
		this.dias = dias;
	}
	
	public Variable getHorario1() {
		return horario1;
	}
	
	public Variable getHorario2() {
		return horario2;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {
		
		if (horario1 == null || horario2 == null || (horario1 == horario2))
			return true;
		
		List<String> lista = new ArrayList<String>(Arrays.asList(dias));
		
		String valueHorario1 = (String) assignment.getValue(horario1);
		String valueHorario2 = (String) assignment.getValue(horario2);
		
		if (valueHorario1 != null && valueHorario2 != null) {
			
			String dia1 = valueHorario1.split("_")[0];
			String dia2 = valueHorario2.split("_")[0];
			
			int indexDia1 = lista.indexOf(dia1);
			int indexDia2 = lista.indexOf(dia2);
			
			if (dia1.equals(dia2))
				return false;
			
			if (indexDia1 + indexDia2 < 2)
				return false;
		}
		
		return true;
	}

}
