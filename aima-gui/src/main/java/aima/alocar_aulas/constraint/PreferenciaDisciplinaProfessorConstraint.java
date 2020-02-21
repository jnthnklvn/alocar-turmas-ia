package aima.alocar_aulas.constraint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.Variable;

public class PreferenciaDisciplinaProfessorConstraint implements Constraint<Variable, String> {
	
	private List<Variable> scope;
	private Variable professor;
	private Variable disciplina;
	private Map<String, Integer[]> prefs;
	private Map<String, Integer[]> skills;
	private String[] professores;
	private String[] disciplinas;
	
	public PreferenciaDisciplinaProfessorConstraint(Variable professor, String[] professores, Variable disciplina, Map<String, Integer[]> prefs, Map<String, Integer[]> skills, String[] disciplinas) {
		this.scope = new ArrayList<Variable>();
		this.scope.add(professor);
		this.scope.add(disciplina);
		this.professor = professor;
		this.disciplina = disciplina;
		this.prefs = prefs;
		this.skills = skills;
		this.disciplinas = disciplinas;
		this.professores = professores;
	}
	
	@Override
	public List<Variable> getScope() {
		return scope;
	}
	
	public Map<String, Integer[]> getPrefs() {
		return prefs;
	}
	
	public Map<String, Integer[]> getSkills() {
		return skills;
	}
		
	public Integer[] getPreferencias(String key) {
		return prefs.get(key);
	}
		
	public Integer[] getHabilidades(String key) {
		return skills.get(key);
	}
	
	public boolean prefereDisciplina(String key, String disciplina) {
		
		Integer[] values = prefs.get(key);
		
		if (values != null) {
			
			for (Integer value : values) {
				if (disciplinas[value].equals(disciplina))
					return true;
			}
		}
		
		return false;
	}
	
	public boolean podeMinistrarDisciplina(String key, String disciplina) {
		
		Integer[] values = skills.get(key);
		
		if (values != null) {
			
			for (Integer value : values) {
				if (disciplinas[value].equals(disciplina))
					return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isSatisfiedWith(Assignment<Variable, String> assignment) {
		
		String valueDisciplina = (String) assignment.getValue(disciplina);
		String valueProfessor = (String) assignment.getValue(professor);
		
		if (valueDisciplina != null) {
		
			if (professor.getName().endsWith(disciplina.getName())) {
				if (valueProfessor != null) {
					if (!podeMinistrarDisciplina(valueProfessor, valueDisciplina)) {
						return false;
					}
					if (prefereDisciplina(valueProfessor, valueDisciplina)) {
						return true;
					}
					for (String professor : professores) {
						if (prefereDisciplina(professor, valueDisciplina) && podeMinistrarDisciplina(professor, valueDisciplina)) {
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}
}
