package aima.alocar_aulas.model;

import java.io.Serializable;

public abstract class Entidade implements Serializable {

	private static final long serialVersionUID = 109632090320375714L;

	public abstract int getId();

	public Entidade() {

	}
}