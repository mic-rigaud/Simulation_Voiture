package fr.ensta.element;

import fr.ensta.element.noeud.intersection.ArretException;
import fr.ensta.element.voiture.Voiture;

public interface IElement {

	public static final int HAUT = 2;
	public static final int GAUCHE = 1;
	public static final int DIAG_MONTANTE = 0;
	public static final int DROITE = -1;
	public static final int BAS = -2;

	public static final int ENTRE = 4;
	public static final int SORTIE = 5;
	public static final int VITESSE_REGLEMENTAIRE = 50;

	void entreVoiture(Voiture voiture) throws ElementOccupeException, ArretException;

	void deplacerVoiture(Voiture voiture);

	@Deprecated
	void ajouterConnexion(IElement element, int direction);
	// void changerVitesse(Voiture voiture, int vitesse);

	public static String getDirection(int direction) {
		switch (direction) {
		case HAUT:
			return "haut";
		case BAS:
			return "bas";
		case GAUCHE:
			return "gauche";
		case DROITE:
			return "droite";
		default:
			return "Direction invalide";
		}
	}

}
