package aima.alocar_aulas.model;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Variable;
import aima.core.util.datastructure.Pair;

public class TurmaProfessor {
	
	private Variable professor;
	private List<Pair<Variable, Variable>> turmas;
	
	public TurmaProfessor(Variable professor) {	
		this.professor = professor;
		this.turmas = new ArrayList<Pair<Variable, Variable>>();
	}
	
	public void addTurma(Variable dia, Variable hora) {
		
		Pair<Variable, Variable> turma = new Pair<Variable, Variable>(dia, hora);
		turmas.add(turma);
	}
	
	public Variable getProfessor() {
		return professor;
	}
	
	public List<Pair<Variable, Variable>> getTurmas() {
		return turmas;
	}
}
