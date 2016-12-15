package fr.ensta.element.noeud.intersection;

import java.util.HashMap;

import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;
import fr.ensta.element.voiture.Voiture;

public class Stop implements IIntersection {

	private HashMap<Integer, Route> connection;

	@Override
	public void entreVoiture(Voiture voiture) {
		voiture.setPosition(this);
		// TODO Auto-generated method stub

	}

	@Override
	public void deplacerVoiture(Voiture voiture) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void ajouterConnexion(IElement element, int direction) {
		// TODO Auto-generated method stub

	}

}
