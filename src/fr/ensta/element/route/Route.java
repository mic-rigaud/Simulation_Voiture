package fr.ensta.element.route;

import java.util.HashMap;
import java.util.LinkedList;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class Route implements IElement {

	LinkedList<Troncon> route;
	HashMap<Integer, IElement> connexion;

	public Route(int nbrTroncon) {
		route = new LinkedList<Troncon>();
		connexion = new HashMap<Integer, IElement>();
		ajouterTroncon(nbrTroncon);
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		int direction = voiture.getDirection();
		voiture.setPosition(this);
		if (direction == GAUCHE || direction == HAUT) {
			route.getFirst().entreVoiture(voiture);
		} else if (direction == DROITE || direction == BAS) {
			route.getLast().entreVoiture(voiture);
		}
		Logger.Information(this, "info", "Voiture entre dans la route");
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		if (direction == GAUCHE || direction == HAUT) {
			connexion.put(ENTRE, element);
		} else if (direction == DROITE || direction == BAS) {
			connexion.put(SORTIE, element);
		}

	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		Logger.Information(this, "info",
				"Voiture va etre deplace, direction " + String.valueOf(voiture.getDirection()));
		int index = 0;
		for (Troncon tr : route) {
			if (tr.contientVoiture(voiture)) {
				Logger.Information(this, "info", "Voiture est contenu par l indice " + String.valueOf(index));
				if (index == (route.size() - 1)
						&& (voiture.getDirection() == GAUCHE || voiture.getDirection() == HAUT)) {
					connexion.get(SORTIE).entreVoiture(voiture);
				} else if (index == 0 && (voiture.getDirection() == DROITE || voiture.getDirection() == BAS)) {
					connexion.get(ENTRE).entreVoiture(voiture);
				} else {
					// TODO: gerer le cas ou le troncon est occupe
					if (voiture.getDirection() == DROITE || voiture.getDirection() == BAS)
						route.get(--index).entreVoiture(voiture);
					else if (voiture.getDirection() == GAUCHE || voiture.getDirection() == HAUT)
						route.get(++index).entreVoiture(voiture);
					Logger.Information(this, "info", "Voiture change de troncon");
				}
				tr.deplacerVoiture(voiture);
				return;
			}
			index++;
		}
		return;
	}

	public void ajouterTroncon(int nbrTroncon) {
		for (int i = 0; i < nbrTroncon; i++) {
			Troncon tr = new Troncon();
			route.add(tr);
		}
	}

}
