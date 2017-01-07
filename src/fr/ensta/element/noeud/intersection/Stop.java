package fr.ensta.element.noeud.intersection;

import java.util.HashMap;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class Stop implements IIntersection {

	private HashMap<Integer, Route> connections;
	private HashMap<Integer, Boolean> signalisation;
	private Troncon trCentral;
	private String nom;

	public Stop(String nom) {
		connections = new HashMap<Integer, Route>();
		signalisation = new HashMap<Integer, Boolean>();
		trCentral = new Troncon();
		this.nom = nom;
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException, ArretException {
		try {
			if (voiture.getVitesse() != 0 && stop(-voiture.direction)) {
				throw new ArretException(nom, voiture.nom);
			}
			int newDirection = getNewDirection(this.nom, voiture.getArrive());
			IntersectionLibre(voiture.direction, newDirection);
			voiture.direction = newDirection;
			voiture.setVitesse(true, VITESSE_REGLEMENTAIRE);
			trCentral.entreVoiture(voiture);
			voiture.setPosition(this);
			Logger.Information(this, "info", voiture.nom + " arrive sur le stop " + nom);
		} catch (ElementOccupeException e) {
			throw new ElementOccupeException(e.getMessage() + " dans le stop " + nom);
		}
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			connections.get(voiture.direction).entreVoiture(voiture);
			trCentral.deplacerVoiture(voiture);
		} catch (ElementOccupeException e) {
			voiture.arreter(this);
			Logger.Error(this, "info", e.toString());
		}
	}

	private boolean stop(int direction) {
		return signalisation.get(direction);
	}

	// TODO: lever des exceptions!
	// la gestion actuelle est simple. La voiture peut rentrer dans l
	// intersection si il y personne. C'est trop simplifie...
	private void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!trCentral.isEmpty())
			throw new ElementOccupeException("intersection non libre");
	}

	public void ajouterSignalisation(int direction) {
		signalisation.put(direction, true);
		System.out.println(nom + " " + signalisation);
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO: gerer element pas route
		// TODO; gerer direction déjà prise
		connections.put(direction, (Route) element);
		signalisation.put(direction, false);
	}

	@Override
	public String toString() {
		return nom;
	}
}
