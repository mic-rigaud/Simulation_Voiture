package fr.ensta.element.voiture;

import enstabretagne.base.time.LogicalDuration;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.troncon.Troncon;
import fr.ensta.environement.action.DeplacerVoiture;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;

public class VoitureEntity extends SimEntity {

	Voiture voiture;

	public VoitureEntity(SimEngine engine, String nom, PointES depart, PointES arrive) {
		super(engine, "Voiture");
		voiture = new Voiture(nom, depart, arrive);
	}

	public void deplacerVoiture() {
		voiture.deplacerVoiture();
		int duree = 0;
		if (!voiture.arrive()) {
			if (voiture.getVitesse() == 0)
				duree = 2000;
			else
				duree = (Troncon.longeur * 3600) / (voiture.getVitesse());
			this.addEvent(new DeplacerVoiture(getEngine().SimulationDate().add(LogicalDuration.ofMillis(duree)), this));
		}
	}

}
