package fr.ensta.element.noeud.intersection;

import java.util.HashMap;
import java.util.Map;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class Stop implements IIntersection {

	private HashMap<Integer, Route> connections;
	private HashMap<Integer, Troncon> decoupe;
	private String nom;

	public Stop(String nom) {
		connections = new HashMap<Integer, Route>();
		decoupe = new HashMap<Integer, Troncon>();
		decoupe.put(0, new Troncon());
		this.nom = nom;
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException {
		try {
			voiture.setPosition(this);
			decoupe.get(-voiture.getDirection()).entreVoiture(voiture);
			voiture.setVitesse(0);
			Logger.Information(this, "info", "Voiture arrive sur le stop " + nom);
		} catch (ElementOccupeException e) {
			Logger.Warning(this, "info", e.toString());
			e.printStackTrace();
			throw new ElementOccupeException();
		}
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			int direction = voiture.getDirection();
			for (Map.Entry<Integer, Troncon> e : decoupe.entrySet()) {
				int position = e.getKey();
				Troncon tr = e.getValue();
				if (tr.contientVoiture(voiture)) {
					// Logger.Information(this, "info", "Voiture est sur le
					// troncon " + String.valueOf(position));
					if (position == direction) {
						connections.get(direction).entreVoiture(voiture);
					} else if (position == 0) {
						decoupe.get(direction).entreVoiture(voiture);
					} else {
						// TODO: change la direction de la voiture
						int newDirection = getNewDirection(this.nom, voiture.getArrive());
						IntersectionLibre(position, newDirection);
						voiture.setDirection(newDirection);
						voiture.setVitesse(VITESSE_REGLEMENTAIRE);
						decoupe.get(0).entreVoiture(voiture);
					}
					tr.deplacerVoiture(voiture);
				}
			}
		} catch (ElementOccupeException e) {
			Logger.Warning(this, "info", e.toString());
			e.printStackTrace();
		}
	}

	// TODO: lever des exceptions!
	// la gestion actuelle est simple. La voiture peut rentrer dans l
	// intersection si il y personne. C'est trop simplifie...
	private void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!decoupe.get(0).isEmpty())
			throw new ElementOccupeException();
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO: gerer element pas route
		// TODO; gerer direction déjà prise
		connections.put(direction, (Route) element);
		decoupe.put(direction, new Troncon());
	}

}
