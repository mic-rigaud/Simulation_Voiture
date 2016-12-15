package fr.ensta.element.noeud.pointEntreSortie;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.INoeud;
import fr.ensta.element.voiture.Voiture;

public class PointES implements INoeud {

	private IElement entre;
	private int direction;

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		this.direction = direction;
		this.entre = element;
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		voiture.setPosition(this);
		voiture.setVitesse(0);
		Logger.Information(this, "info", "Voiture est arrivee");
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		voiture.setDirection(direction);
		entre.entreVoiture(voiture);
		voiture.setVitesse(VITESSE_REGLEMENTAIRE);
		return;
	}

}
