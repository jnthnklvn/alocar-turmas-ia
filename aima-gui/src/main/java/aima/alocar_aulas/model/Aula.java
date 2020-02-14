package aima.alocar_aulas.model;

public class Aula extends Entidade {

	private static final long serialVersionUID = 7872936954532471767L;
	
	private int id;
	
	private Professor professor;
	
	private Disciplina disciplina;
		
	private Horario horario;
	
	public Aula() {
		
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
	
	public Horario getHorario() {
		return horario;
	}
	
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
}