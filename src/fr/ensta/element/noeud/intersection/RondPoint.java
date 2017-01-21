package fr.ensta.element.noeud.intersection;

import java.util.HashMap;
import java.util.LinkedList;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.voiture.Voiture;
import fr.ensta.simulation.EnvironementEntity;

public class RondPoint implements IIntersection {

	private HashMap<Integer, Route> connections;
	private HashMap<Integer, Boolean> signalisation;
	private LinkedList<Voiture> voitures;
	private String nom;
	private int nbVoiture;

	public RondPoint(String nom) {
		connections = new HashMap<Integer, Route>();
		voitures = new LinkedList<Voiture>();
		this.nom = nom;
		nbVoiture = 0;
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException, ArretException {
		int newDirection = getNewDirection(this.nom, voiture.getArrive());
		voiture.direction = newDirection;
		voiture.setVitesse(true, VITESSE_REGLEMENTAIRE);
		voitures.add(voiture);
		voiture.setPosition(this);
		nbVoiture++;
		Logger.Information(this, "info", voiture.nom + " arrive sur le rondPoint " + nom);
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			connections.get(voiture.direction).entreVoiture(voiture);
			voitures.remove(voiture);
			nbVoiture--;
			EnvironementEntity.INSTANCE.flash();
		} catch (ElementOccupeException e) {
			voiture.setPosition(this);
			voiture.setVitesse(true, voiture.getVitesse());
			Logger.Error(this, "info", e.toString());
		}
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		connections.put(direction, (Route) element);
		signalisation.put(direction, false);
	}

	@Override
	public String toString() {
		return nom;
	}

	@Override
	public int[] getNbVoiture() {
		int[] rep = { nbVoiture };
		return rep;
	}
}