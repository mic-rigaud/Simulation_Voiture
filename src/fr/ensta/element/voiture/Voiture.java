package fr.ensta.element.voiture;

import fr.ensta.element.IElement;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.troncon.Troncon;

public class Voiture {

	// a besoin de 49m pour s arreter
	private static final double COEF_ACC = (100.0 * 1000.0) / (14.0 * 3600.0);
	private static final double COEF_DECC = (100.0 * 1000.0) / (10.0 * 3600.0);
	private PointES arrive;
	private int direction;
	private double vitesse;
	private boolean arreter;
	private IElement position;
	public String nom;

	public Voiture(String nom, PointES depart, PointES arrive) {
		this.arrive = arrive;
		setPosition(depart);
		this.nom = nom;
		vitesse = IElement.VITESSE_REGLEMENTAIRE;
		arreter = false;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void deplacerVoiture() {
		position.deplacerVoiture(this);
	}

	public void setPosition(IElement position) {
		this.position = position;
	}

	public boolean arrive() {
		return arrive.equals(position);
	}

	public String getArrive() {
		return arrive.toString();
	}

	public void setVitesse(boolean accelerer, double vitesseLimit) {
		if (accelerer && this.vitesse < vitesseLimit) {
			this.accelerer(vitesseLimit);
		} else if (!accelerer && this.vitesse > vitesseLimit) {
			this.deccelerer(vitesseLimit);
		}
	}

	private void accelerer(double vitesseLimit) {
		double newVitesse;
		if (this.vitesse == 0)
			newVitesse = Math.sqrt(Math.pow(khTOms(this.vitesse), 2) + 2 * COEF_ACC * (Troncon.longeur / 2));
		else
			newVitesse = Math.sqrt(Math.pow(khTOms(this.vitesse), 2) + 2 * COEF_ACC * Troncon.longeur);
		newVitesse = msTOkh(newVitesse);
		if (newVitesse > vitesseLimit) {
			this.vitesse = vitesseLimit;
		} else {
			this.vitesse = newVitesse;
		}

	}

	private void deccelerer(double vitesseLimit) {
		double newVitesse = Math.pow(khTOms(this.vitesse), 2) - 2 * COEF_DECC * Troncon.longeur;
		if (newVitesse < 0) {
			this.vitesse = vitesseLimit;
			return;
		}
		newVitesse = msTOkh(Math.sqrt(newVitesse));
		if (newVitesse < vitesseLimit) {
			this.vitesse = vitesseLimit;
		} else {
			this.vitesse = newVitesse;
		}
	}

	private double khTOms(double vitesse2) {
		return vitesse2 * 1000 / 3600;
	}

	private double msTOkh(double vitesse2) {
		return vitesse2 * 3600 / 1000;
	}

	public int getDuree() {
		int duree = 0;
		if (arreter)
			duree += 2000;
		if (!(this.vitesse == 0)) {
			duree += (Troncon.longeur / khTOms(this.vitesse)) * 1000;
			arreter = false;
		}
		return duree;
	}

	public void arreter(IElement position) {
		this.position = position;
		this.vitesse = 0;
		arreter = true;
	}

	public double getLongeurArret() {
		return Math.pow(khTOms(IElement.VITESSE_REGLEMENTAIRE), 2) / (2 * COEF_DECC);
	}

	public String getPosition() {
		return this.position.toString();
	}
}
