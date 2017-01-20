package fr.ensta.element.noeud.intersection;

import fr.ensta.element.ElementOccupeException;

public class Stop_v1 extends Stop {

	public Stop_v1(String nom) {
		super(nom);
	}

	@Override
	public void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!super.trCentral.isEmpty())
			throw new ElementOccupeException("intersection non libre");

	}
}