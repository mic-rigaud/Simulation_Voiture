package fr.ensta.element.voiture;

import fr.ensta.element.IElement;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.troncon.Troncon;

public class Voiture {

	// a besoin de 49m pour s arreter
	private PointES arrive;
	private int direction;
	private int vitesse;
	private IElement position;
	public String nom;

	public Voiture(String nom, PointES depart, PointES arrive) {
		this.arrive = arrive;
		setPosition(depart);
		this.nom = nom;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public void deplacerVoiture() {
		position.deplacerVoiture(this);
	}

	public IElement getPosition() {
		return position;
	}

	public void setPosition(IElement position) {
		this.position = position;
	}

	public boolean arrive() {
		return arrive.equals(position);
	}

	public String getArrive() {
		return arrive.getNom();
	}

	public void setVitesseDemarage() {
		int vitesse = (Troncon.longeur * IElement.VITESSE_REGLEMENTAIRE * 5 / 36)
				/ (Troncon.longeur + 2 * IElement.VITESSE_REGLEMENTAIRE * 5 / 36);
		vitesse = vitesse * 36 / 10;
		this.setVitesse(vitesse);
	}

}
