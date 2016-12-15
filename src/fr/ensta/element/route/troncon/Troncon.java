package fr.ensta.element.route.troncon;

import java.util.HashMap;

import fr.ensta.element.IElement;
import fr.ensta.element.voiture.Voiture;

public class Troncon implements IElement {

	private int longueur;
	private HashMap<Integer, Voiture> voitures;

	public Troncon() {
		voitures = new HashMap<Integer, Voiture>();
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		int direction = voiture.getDirection();
		if (!voitures.containsKey(direction)) {
			voitures.put(direction, voiture);
		} else {
			// TODO: lever une exception!!
		}

	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		voitures.remove(voiture.getDirection());
		return;
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO Auto-generated method stub
	}

	public boolean contientVoiture(Voiture voiture) {
		return voitures.containsValue(voiture);
	}

}
