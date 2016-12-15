package fr.ensta.element.voiture;

import fr.ensta.element.IElement;
import fr.ensta.element.noeud.pointEntreSortie.PointES;

public class Voiture {

	private PointES arrive;
	private int direction;
	private int vitesse;
	private IElement position;

	public Voiture(PointES depart, PointES arrive) {
		this.arrive = arrive;
		setPosition(depart);
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

}
