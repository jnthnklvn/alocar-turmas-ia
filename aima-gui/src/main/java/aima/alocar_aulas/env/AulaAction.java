package aima.alocar_aulas.env;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 * Aulas can be placed, removed, and moved. For movements, a vertical direction
 * is assumed. Therefore, only the end point needs to be specified.
 * 
 * @author Ravi Mohan
 * @author Ruediger Lunde
 */
public class AulaAction extends DynamicAction {
	public static final String PLACE_AULA = "placeAulaAt";
	public static final String REMOVE_AULA = "removeAulaAt";
	public static final String MOVE_AULA = "moveAulaTo";

	public static final String ATTRIBUTE_AULA_LOC = "location";
	public static final String ATTRIBUTE_AULA_NAME = "aula";

	/**
	 * Creates a aula action. Supported values of type are {@link #PLACE_AULA}
	 * , {@link #REMOVE_AULA}, or {@link #MOVE_AULA}.
	 */
	public AulaAction(String type,  XYLocation loc, String aula) {
		super(type);
		setAttribute(ATTRIBUTE_AULA_LOC, loc);
		setAttribute(ATTRIBUTE_AULA_NAME, aula);
	}

	public XYLocation getLocation() {
		return (XYLocation) getAttribute(ATTRIBUTE_AULA_LOC);
	}
	
	public String getAula() {
		return (String) getAttribute(ATTRIBUTE_AULA_NAME);
	}

	public int getX() {
		return getLocation().getX();
	}

	public int getY() {
		return getLocation().getY();
	}
}
