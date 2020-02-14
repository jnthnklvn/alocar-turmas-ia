package aima.alocar_aulas.env;

import java.util.ArrayList;
import java.util.List;

import aima.core.util.datastructure.Pair;
import aima.core.util.datastructure.XYLocation;

/**
 * Represents a quadratic board with a matrix of squares on which Aulas can be
 * placed (only one per square) and moved.
 * 
 * @author Ravi Mohan
 * @author Ruediger Lunde
 */
public class AulasBoard {

	/**
	 * X (first index, col) increases left to right with zero based index, Y (second
	 * index, row) increases top to bottom with zero based index. A Aula at position
	 * (x, y) is indicated by value true.
	 */
	private String[][] squares;

	private List<String> aulasPendentes;

	/**
	 * Creates a board with <code>size</code> rows and size columns. Column and row
	 * indices start with 0.
	 */
	public AulasBoard(int rows, int columns) {
		squares = new String[rows][columns];
		for (int col = 0; col < columns; col++) {
			for (int row = 0; row < rows; row++) {
				squares[col][row] = "---";
			}
		}
	}

	/**
	 * Creates a board with <code>size</code> rows and size columns. Column and row
	 * indices start with 0.
	 * 
	 * @param config Controls whether the board is initially empty or contains some
	 *               Aulas.
	 */
	public AulasBoard(int rows, int columns, Pair<String, XYLocation> l) {
		this(rows, columns);
		addAula(l);
	}

	public int getRows() {
		return squares.length;
	}

	public int getColumns() {
		return squares[0].length;
	}

	public void clear() {
		for (int col = 0; col < getRows(); col++) {
			for (int row = 0; row < getColumns(); row++) {
				squares[col][row] = "---";
			}
		}
	}

	public void removeAulaPendente(String aula) {
		for (int i = aulasPendentes.size() - 1; i >= 0; i--) {
			if (aulasPendentes.get(i).equals(aula)) {
				aulasPendentes.remove(i);
				break;
			}
		}
	}

	public List<String> getAulasPendentes() {
		return aulasPendentes;
	}

	public void setAulasPendentes(List<String> aulasPendentes) {
		this.aulasPendentes = aulasPendentes;
	}

	public void setAulasAt(List<Pair<String, XYLocation>> locations) {
		clear();
		locations.forEach(this::addAula);
	}

	/** Column and row indices start with 0! */
	public void addAula(String aula, XYLocation l) {
		//removeAulaPendente(aula);
		squares[l.getX()][l.getY()] = aula;
	}

	public void addAula(Pair<String, XYLocation> l) {
		removeAulaPendente(l.getFirst());
		squares[l.getSecond().getX()][l.getSecond().getY()] = l.getFirst();
	}

	public void removeAulaFrom(Pair<String, XYLocation> l) {
		aulasPendentes.add(l.getFirst());
		squares[l.getSecond().getX()][l.getSecond().getY()] = "---";
	}

	public void removeAulaFrom(String aula, XYLocation from) {
		aulasPendentes.add(aula);
		squares[from.getX()][from.getY()] = "---";
	}

	public void moveAula(String aula, XYLocation from, XYLocation to) {
		if ((AulaExistsAt(aula, from)) && (!(AulaExistsAt(aula, to)))) {
			removeAulaFrom(aula, from);
			addAula(aula, to);
		}
	}

	public boolean AulaExistsAt(String aula, XYLocation l) {
		return (AulaExistsAt(aula, l.getX(), l.getY()));
	}

	public boolean AulaExistsAt(XYLocation l) {
		return (AulaExistsAt(l.getX(), l.getY()));
	}

	public boolean AulaExistsAt(int x, int y) {
		return !squares[x][y].equals("---");
	}

	private boolean AulaExistsAt(String aula, int x, int y) {
		return squares[x][y].equals(aula);
	}

	public int getNumberOfAulasOnBoard() {
		int count = 0;
		for (int col = 0; col < getRows(); col++) {
			for (int row = 0; row < getColumns(); row++) {
				if (!squares[col][row].equals("---"))
					count++;
			}
		}
		return count;
	}

	public List<Pair<String, XYLocation>> getAulasPositions() {
		ArrayList<Pair<String, XYLocation>> result = new ArrayList<>();
		for (int col = 0; col < getRows(); col++) {
			for (int row = 0; row < getColumns(); row++) {
				if (AulaExistsAt(col, row))
					result.add(new Pair<String, XYLocation>(squares[col][row], new XYLocation(col, row)));
			}
		}
		return result;

	}

	@Override
	public int hashCode() {
		int result = 0;
		for (int col = 0; col < getRows(); col++) {
			for (int row = 0; row < getColumns(); row++) {
				if (AulaExistsAt(col, row))
					result = 17 * result + 7 * col + row;
			}
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && getClass() == o.getClass()) {
			AulasBoard aBoard = (AulasBoard) o;
			for (int col = 0; col < getRows(); col++) {
				for (int row = 0; row < getColumns(); row++) {
					if (AulaExistsAt(col, row) != aBoard.AulaExistsAt(col, row))
						return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getColumns(); col++) {
				if (AulaExistsAt(col, row))
					builder.append('Q');
				else
					builder.append('-');
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}