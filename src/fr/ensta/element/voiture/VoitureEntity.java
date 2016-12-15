package fr.ensta.element.voiture;

import enstabretagne.base.time.LogicalDuration;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.environement.action.DeplacerVoiture;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;

public class VoitureEntity extends SimEntity {

	Voiture voiture;

	public VoitureEntity(SimEngine engine, String type, PointES depart, PointES arrive) {
		super(engine, type);
		voiture = new Voiture(depart, arrive);
	}

	public void deplacerVoiture() {
		voiture.deplacerVoiture();
		if (!voiture.arrive())
			this.addEvent(new DeplacerVoiture(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(5)), this));
	}

}
