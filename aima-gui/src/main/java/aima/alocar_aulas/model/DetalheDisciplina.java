package aima.alocar_aulas.model;

public class DetalheDisciplina extends Entidade {

	private static final long serialVersionUID = -1273536257865814586L;

	private int id;
	private int creditos;
	private int cargaHoraria;
	private Disciplina disciplina;

	public DetalheDisciplina() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
}