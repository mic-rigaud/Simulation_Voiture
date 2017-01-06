package fr.ensta.element.noeud.intersection;

public class Feu {

	public static final int ROUGE = 2;
	private static final int ORANGE = 1;
	private static final int VERT = 0;

	private int couleur;

	public Feu() {
		couleur = ROUGE;
	}

	public int changerCouleur() {
		if (couleur == ROUGE) {
			this.couleur = VERT;
		} else
			this.couleur = this.couleur + 1;
		return this.couleur;
	}

	public int tempsAttente() {
		switch (couleur) {
		case ROUGE:
			return 3;
		case ORANGE:
			return 5;
		case VERT:
			return 30;
		default:
			return 0;
		}
	}

	public boolean isRouge() {
		return couleur == ROUGE;
	}

	public String getCouleur() {
		switch (couleur) {
		case ROUGE:
			return "rouge";
		case VERT:
			return "vert";
		case ORANGE:
			return "orange";
		default:
			return "couleur invalide";
		}
	}
}
