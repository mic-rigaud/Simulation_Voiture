package fr.ensta.element.noeud.pointEntreSortie;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.INoeud;
import fr.ensta.element.voiture.Voiture;

public class PointES implements INoeud {

	private IElement entre;
	private int direction;
	private String nom;

	public PointES(String nom) {
		this.nom = nom;
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		this.direction = direction;
		this.entre = element;
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		voiture.setPosition(this);
		voiture.setVitesse(0);
		Logger.Information(this, "info", "Voiture est arrivee au point " + nom);
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			Logger.Information(this, "info", "Voiture viens de partir du point " + nom);
			voiture.setDirection(direction);
			entre.entreVoiture(voiture);
			voiture.setVitesse(VITESSE_REGLEMENTAIRE);
		} catch (ElementOccupeException e) {
			Logger.Warning(this, "info", e.toString());
			e.printStackTrace();
		}
	}

	public String getNom() {
		return nom;
	}

}
