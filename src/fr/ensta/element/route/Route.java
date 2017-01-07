package fr.ensta.element.route;

import java.util.HashMap;
import java.util.LinkedList;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.intersection.ArretException;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.element.voiture.Voiture;

public class Route implements IElement {

	private LinkedList<Troncon> route;
	private HashMap<Integer, IElement> connexion;
	private String nom;

	public Route(int longeur, String nom) {
		int nbrTroncon = longeur / Troncon.longeur;
		route = new LinkedList<Troncon>();
		connexion = new HashMap<Integer, IElement>();
		ajouterTroncon(nbrTroncon);
		this.nom = nom;
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException {
		try {
			int direction = voiture.direction;
			voiture.setPosition(this);
			if (direction < DIAG_MONTANTE) {
				route.getFirst().entreVoiture(voiture);
				changerVitesse(0, voiture);
			} else if (direction > DIAG_MONTANTE) {
				route.getLast().entreVoiture(voiture);
				changerVitesse(route.size(), voiture);
			}
			Logger.Information(this, "info", voiture.nom + " entre dans la route " + nom);
		} catch (ElementOccupeException e) {
			throw new ElementOccupeException(e.toString() + " dans la route");
		}
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		if (direction > DIAG_MONTANTE) {
			connexion.put(ENTRE, element);
		} else if (direction < DIAG_MONTANTE) {
			connexion.put(SORTIE, element);
		}

	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		try {
			int index = 0;
			for (Troncon tr : route) {
				if (tr.contientVoiture(voiture)) {
					changerVitesse(index, voiture);
					if (index == (route.size() - 1) && (voiture.direction < DIAG_MONTANTE)) {
						connexion.get(SORTIE).entreVoiture(voiture);
					} else if (index == 0 && (voiture.direction > DIAG_MONTANTE)) {
						connexion.get(ENTRE).entreVoiture(voiture);
					} else {
						if (voiture.direction > DIAG_MONTANTE)
							route.get(--index).entreVoiture(voiture);
						else if (voiture.direction < DIAG_MONTANTE)
							route.get(++index).entreVoiture(voiture);
					}
					tr.deplacerVoiture(voiture);
					return;
				}
				index++;
			}
		} catch (ElementOccupeException e) {
			voiture.arreter(this);
			Logger.Error(this, "info", e.toString());
		} catch (ArretException e) {
			voiture.arreter(this);
			Logger.Information(this, "info", e.toString());
		}

	}

	private void changerVitesse(int index, Voiture voiture) {
		// TODO Auto-generated method stub
		double longeurArret = voiture.getLongeurArret();
		int tronconNecessaireArret = (int) ((longeurArret / Troncon.longeur)) + 2;
		if ((index > (route.size() - tronconNecessaireArret - 1) && (voiture.direction < DIAG_MONTANTE))
				|| (index < tronconNecessaireArret && (voiture.direction > DIAG_MONTANTE)))
			voiture.setVitesse(false, 10);
		else
			voiture.setVitesse(true, VITESSE_REGLEMENTAIRE);
	}

	public void ajouterTroncon(int nbrTroncon) {
		for (int i = 0; i < nbrTroncon; i++) {
			Troncon tr = new Troncon();
			route.add(tr);
		}
	}

	@Override
	public String toString() {
		return nom;
	}

}
