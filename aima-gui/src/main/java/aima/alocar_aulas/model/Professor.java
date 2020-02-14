package aima.alocar_aulas.model;

import java.util.HashSet;
import java.util.Set;

public class Professor extends Entidade {

	private static final long serialVersionUID = -6331342046688981121L;
	
	private int id;
	
	private String nome;
	
	private String endereco;
	
	private Set<Aula> aulas = new HashSet<Aula>();
	
	private Set<PreferenciaDisciplinaProfessor> preferencias = new HashSet<PreferenciaDisciplinaProfessor>();
	
	public Professor() {
	
	}

	@Override
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
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
	
	@Override
	public String toString() {
		return this.getNome();
	}
}