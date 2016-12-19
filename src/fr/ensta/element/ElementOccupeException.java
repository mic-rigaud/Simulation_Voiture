package fr.ensta.element;

public class ElementOccupeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementOccupeException() {
		super("Troncon occupe");
	}

	public ElementOccupeException(String voiture1, String voiture2) {
		super("Troncon occupe par " + voiture2 + " et " + voiture1 + " essaye d y entrer");

	}

	public ElementOccupeException(String message) {
		super(message);
	}
}
