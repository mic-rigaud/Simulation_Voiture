package fr.ensta.element;

public class ElementOccupeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementOccupeException() {
		System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants négatif !");
	}
}
