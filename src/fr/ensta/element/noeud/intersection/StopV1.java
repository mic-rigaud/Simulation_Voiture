package fr.ensta.element.noeud.intersection;

import fr.ensta.element.ElementOccupeException;

public class StopV1 extends Stop {

	public StopV1(String nom) {
		super(nom);
	}

	@Override
	public void IntersectionLibre(int position, int newDirection) throws ElementOccupeException {
		if (!super.trCentral.isEmpty())
			throw new ElementOccupeException("intersection non libre");

	}
}