package fr.ensta.element;

import fr.ensta.element.voiture.Voiture;

public interface IElement {

	// logique horaire
	public static final int HAUT = 0;
	public static final int DROITE = 1;
	public static final int BAS = 2;
	public static final int GAUCHE = 3;
	public static final int ENTRE = 4;
	public static final int SORTIE = 5;
	public static final int VITESSE_REGLEMENTAIRE = 50;

	void ajouterConnexion(IElement element, int direction);

	void entreVoiture(Voiture voiture);

	void deplacerVoiture(Voiture voiture);
	// void changerVitesse(Voiture voiture, int vitesse);
}
