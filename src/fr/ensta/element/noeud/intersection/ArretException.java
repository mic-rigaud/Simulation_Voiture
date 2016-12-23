package fr.ensta.element.noeud.intersection;

public class ArretException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArretException() {
		super("Troncon occupe");
	}

	public ArretException(String voiture1, String voiture2) {
		super("Troncon occupe par " + voiture2 + " et " + voiture1 + " essaye d y entrer");

	}

	public ArretException(String message) {
		super(message);
	}
}
