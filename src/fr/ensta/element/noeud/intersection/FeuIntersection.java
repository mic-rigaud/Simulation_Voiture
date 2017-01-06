package fr.ensta.element.noeud.intersection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class FeuIntersection implements IIntersection {
	private HashMap<Integer, Route> connections;
	private Troncon trCentral;
	private String nom;
	private int token;
	private TreeMap<Integer, Feu> mapFeu;

	public FeuIntersection(String nom) {
		connections = new HashMap<Integer, Route>();
		mapFeu = new TreeMap<Integer, Feu>(new TreeComparator());
		trCentral = new Troncon();
		this.nom = nom;
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException, ArretException {
		try {
			if (mapFeu.get(-voiture.direction).isRouge()) {
				throw new ArretException(nom, voiture.nom);
			}
			int newDirection = getNewDirection(this.nom, voiture.getArrive());
			IntersectionLibre(voiture.direction, newDirection);
			voiture.direction = newDirection;
			voiture.setVitesse(true, VITESSE_REGLEMENTAIRE);
			trCentral.entreVoiture(voiture);
			voiture.setPosition(this);
			Logger.Information(this, "info", voiture.nom + " entre sur le feu " + nom);
		} catch (ElementOccupeException e) {
			throw new ElementOccupeException(e.getMessage() + " dans le feu " + nom);
		}
	}

	private void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!trCentral.isEmpty())
			throw new ElementOccupeException("intersection non libre");
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

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		connections.put(direction, (Route) element);
		mapFeu.put(direction, new Feu());
		List<Integer> keys = new ArrayList<Integer>(mapFeu.keySet());
		token = keys.get(0);
	}

	public void changerCouleurFeu() {
		if (mapFeu.get(token).changerCouleur() == Feu.ROUGE)
			passerToken();
	}

	public int tempsAttente() {
		return mapFeu.get(token).tempsAttente();
	}

	private void passerToken() {
		List<Integer> keys = new ArrayList<Integer>(mapFeu.keySet());
		for (int id1 = 0; id1 < keys.size(); id1++) {
			if (token == keys.get(id1)) {
				token = keys.get((id1 + 1) % keys.size());
				return;
			}
		}
	}

	public String[] etatIntersection() {
		String[] rep = { nom, IElement.getDirection(token), mapFeu.get(token).getCouleur() };
		return rep;
	}

	@Override
	public String toString() {
		return nom;
	}
}

class TreeComparator implements Comparator<Integer> {
	// Ceci permet de ranger les feux dans l ordre des aiguilles d une montre en
	// partant de la gauche
	@Override
	public int compare(Integer arg0, Integer arg1) {
		if (arg0 == arg1)
			return 0;
		else if (arg0 * arg1 < 0)
			return arg1;
		else if (arg0 == IElement.GAUCHE || arg0 == IElement.DROITE)
			return -1;
		else
			return 1;
	}
}
