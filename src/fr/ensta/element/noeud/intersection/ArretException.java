package fr.ensta.element.noeud.intersection;

public class ArretException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArretException() {
		super("Troncon occupe");
	}

	public ArretException(String intersection, String voiture) {
		super("Voiture " + voiture + " arreter au niveau de l'intersection " + intersection);

	}

	public ArretException(String message) {
		super(message);
	}
}
