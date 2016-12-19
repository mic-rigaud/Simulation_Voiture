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
			voiture.setVitesse(25);
			Logger.Information(this, "info", voiture.nom + " arrive sur le stop " + nom);
		} catch (ElementOccupeException e) {
			throw new ElementOccupeException(e.getMessage() + " dans le stop");
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
					if (position == direction) {
						connections.get(direction).entreVoiture(voiture);
						tr.deplacerVoiture(voiture);
					} else if (position == 0) {
						decoupe.get(direction).entreVoiture(voiture);
						voiture.setVitesse(VITESSE_REGLEMENTAIRE);
						tr.deplacerVoiture(voiture);
					} else {
						if (voiture.getVitesse() != 0) {
							voiture.setVitesse(0);
							return;
						}
						int newDirection = getNewDirection(this.nom, voiture.getArrive());
						IntersectionLibre(position, newDirection);
						tr.deplacerVoiture(voiture);
						voiture.setDirection(newDirection);
						voiture.setVitesseDemarage();
						decoupe.get(0).entreVoiture(voiture);
					}
				}
			}
		} catch (ElementOccupeException e) {
			// TODO:Il va falloir gerer la deceleration pour s arreter puisqu un
			// autre utilisateur est devant
			voiture.setVitesse(0);
			voiture.setPosition(this);
			Logger.Error(this, "info", e.toString());

		}
	}

	// TODO: lever des exceptions!
	// la gestion actuelle est simple. La voiture peut rentrer dans l
	// intersection si il y personne. C'est trop simplifie...
	private void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!decoupe.get(0).isEmpty())
			throw new ElementOccupeException("intersection non libre");
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO: gerer element pas route
		// TODO; gerer direction déjà prise
		connections.put(direction, (Route) element);
		decoupe.put(direction, new Troncon());
	}

}
