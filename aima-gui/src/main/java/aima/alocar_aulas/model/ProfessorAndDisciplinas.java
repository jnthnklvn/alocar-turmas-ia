package aima.alocar_aulas.model;

import java.util.List;

public class ProfessorAndDisciplinas {
    private int professor;
    private List<Integer> preferencias;

	public ProfessorAndDisciplinas(int professor, List<Integer> preferencias) {
		this.professor = professor;
		this.preferencias = preferencias;
	}

	public int getProfessor() {
		return professor;
	}

	public List<Integer> getDisciplinas() {
		return preferencias;
	}
}