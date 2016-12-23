package fr.ensta.element.noeud.pointEntreSortie;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.INoeud;
import fr.ensta.element.noeud.intersection.ArretException;
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
		voiture.arreter(this);
		Logger.Information(this, "info", voiture.nom + " est arrivee au point " + nom);
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			Logger.Information(this, "info", voiture.nom + " viens de partir du point " + nom);
			voiture.setDirection(direction);
			entre.entreVoiture(voiture);
		} catch (ElementOccupeException | ArretException e) {
			voiture.arreter(this);
			Logger.Error(this, "info", e.toString());
		}
	}

	public String getNom() {
		return nom;
	}

}
