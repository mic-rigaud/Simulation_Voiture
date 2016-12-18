package fr.ensta.element.noeud.intersection;

import java.util.HashMap;
import java.util.Map;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class Stop implements IIntersection {

	private HashMap<Integer, Route> connections;
	private HashMap<Integer, Troncon> decoupe;

	public Stop() {
		connections = new HashMap<Integer, Route>();
		decoupe = new HashMap<Integer, Troncon>();
		decoupe.put(0, new Troncon());
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		voiture.setPosition(this);
		decoupe.get(-voiture.getDirection()).entreVoiture(voiture);
		voiture.setVitesse(0);
		Logger.Information(this, "info", "Voiture arrive sur le stop");
	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		int direction = voiture.getDirection();
		for (Map.Entry<Integer, Troncon> e : decoupe.entrySet()) {
			int position = e.getKey();
			Troncon tr = e.getValue();
			if (tr.contientVoiture(voiture)) {
				Logger.Information(this, "info", "Voiture est sur le troncon " + String.valueOf(position));
				if (position == direction) {
					connections.get(direction).entreVoiture(voiture);
				} else if (position == 0) {
					decoupe.get(direction).entreVoiture(voiture);
				} else {
					// TODO: change la direction de la voiture
					int newDirection = direction;
					IntersectionLibre(position, newDirection);
					voiture.setDirection(newDirection);
					voiture.setVitesse(VITESSE_REGLEMENTAIRE);
					decoupe.get(0).entreVoiture(voiture);
				}
				tr.deplacerVoiture(voiture);
			}
		}
		return;
	}

	// TODO: lever des exceptions!
	private void IntersectionLibre(int position, int newDirection) {

	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO: gerer element pas route
		// TODO; gerer direction déjà prise
		connections.put(direction, (Route) element);
		decoupe.put(direction, new Troncon());
	}

}
