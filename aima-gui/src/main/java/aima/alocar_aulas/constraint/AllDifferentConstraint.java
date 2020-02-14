package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class AllDifferentConstraint implements Constraint<Variable, String> {
	
	private List<Variable> scope;
	
	public AllDifferentConstraint(List<Variable> vars) {
		this.scope = vars;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {
		
		if (scope.size() > 0) {
			
			List<String> list = new ArrayList<String>();
			
			for (Variable variable : scope) {
				
				String value = (String) assignment.getValue(variable);
				
				if (value != null) {
					list.add(value);
				}
			}
			
			Set<String> set = new HashSet<String>(list);
			
			if (set.size() < list.size())
				return false;
		}
		
		return true;
	}
}