package fr.ensta.element.route.troncon;

import java.util.HashMap;

import fr.ensta.element.ElementOccupeException;
import fr.ensta.element.IElement;
import fr.ensta.element.voiture.Voiture;

public class Troncon implements IElement {

	// private int longueur;
	private HashMap<Integer, Voiture> voitures;

	public Troncon() {
		voitures = new HashMap<Integer, Voiture>();
	}

	@Override
	public void entreVoiture(Voiture voiture) throws ElementOccupeException {
		int direction = voiture.getDirection();
		if (!voitures.containsKey(direction)) {
			voitures.put(direction, voiture);
			voiture.setVitesse(VITESSE_REGLEMENTAIRE);
		} else {
			throw new ElementOccupeException();
		}

	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		voitures.remove(voiture.getDirection());
		return;
	}

	public boolean contientVoiture(Voiture voiture) {
		return voitures.containsValue(voiture);
	}

	public boolean contientVoiture(int direction) {
		return voitures.containsKey(direction);
	}

	public boolean isEmpty() {
		return voitures.isEmpty();
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// Les troncon n'ont pas de connexion donc pas besoin d implementer
	}

}
