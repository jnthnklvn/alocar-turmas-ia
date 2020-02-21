package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class LocaisIguaisConstraint implements Constraint<Variable, String> {

	private List<Variable> scope;

	Variable local1;
	Variable local2;

	public LocaisIguaisConstraint(Variable local1, Variable local2) {

		this.scope = new ArrayList<Variable>();

		this.local1 = local1;
		this.local2 = local2;

		this.scope.add(local1);
		this.scope.add(local2);
	}

	@Override
	public List<Variable> getScope() {
		return scope;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {

		String valueLocal1 = (String) assignment.getValue(local1);
		String valueLocal2 = (String) assignment.getValue(local2);

		if (valueLocal1 != null && valueLocal2 != null) {

			if (!valueLocal1.equals(valueLocal2))
				return false;
		}

		return true;
	}

}
