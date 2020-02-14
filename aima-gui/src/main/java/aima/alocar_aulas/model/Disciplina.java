package aima.alocar_aulas.model;

import java.util.HashSet;
import java.util.Set;

public class Disciplina extends Entidade {

	private static final long serialVersionUID = 3496962150746723949L;
	
	private int id;
	
	private String codigo;
	
	private String descricao;
	
	private Set<Aula> aulas = new HashSet<Aula>();
	
	private Set<PreferenciaDisciplinaProfessor> preferencias = new HashSet<PreferenciaDisciplinaProfessor>();
	
	private Set<DetalheDisciplina> detalhes = new HashSet<DetalheDisciplina>();
	
	private transient int cargaHoraria;
	
	private transient int creditos;
	
	public Disciplina() {
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Set<Aula> getAulas() {
		return aulas;
	}
	
	public void setAulas(Set<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public Set<PreferenciaDisciplinaProfessor> getPreferencias() {
		return preferencias;
	}
	
	public void setPreferencias(Set<PreferenciaDisciplinaProfessor> preferencias) {
		this.preferencias = preferencias;
	}
	
	public Set<DetalheDisciplina> getDetalhes() {
		return detalhes;
	}
	
	public void setDetalhes(Set<DetalheDisciplina> detalhes) {
		this.detalhes = detalhes;
	}
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public int getCreditos() {
		return creditos;
	}
	
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	@Override
	public String toString() {
		return this.getDescricao();
	}
	
	public void postLoad() {
		
	}
}