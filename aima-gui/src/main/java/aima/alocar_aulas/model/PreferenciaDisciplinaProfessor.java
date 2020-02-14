package aima.alocar_aulas.model;

public class PreferenciaDisciplinaProfessor extends Entidade {

	private static final long serialVersionUID = 5311232373351030581L;
	
	private int id;
	
	private Professor professor;
	
	private Disciplina disciplina;
	
	public PreferenciaDisciplinaProfessor() {
	
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public Disciplina getDisciplina() {
		return disciplina;
	}
	
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}