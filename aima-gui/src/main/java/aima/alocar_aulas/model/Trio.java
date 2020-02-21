package aima.alocar_aulas.model;

public class Trio<X, Y, Z> {

	private final X a;
	private final Y b;
	private final Z c;

	public Trio(X x, Y y, Z z) {

		this.a = x;
		this.b = y;
		this.c = z;
	}

	public X getFirst() {
		return a;
	}

	public Y getSecond() {
		return b;
	}

	public Z getThird() {
		return c;
	}

	public boolean equals(Trio<X, Y, Z> trio) {
		return a.equals(trio.a) && b.equals(trio.b) && c.equals(trio.c);
	}
}
