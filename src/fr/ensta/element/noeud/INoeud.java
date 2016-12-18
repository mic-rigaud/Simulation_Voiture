package fr.ensta.element.noeud;

import fr.ensta.element.IElement;
import fr.ensta.element.route.Route;

public interface INoeud extends IElement {

	@SuppressWarnings("deprecation")
	default void connecter(Route element, int direction) {
		element.ajouterConnexion(this, -direction);
		this.ajouterConnexion(element, direction);
	}

}
