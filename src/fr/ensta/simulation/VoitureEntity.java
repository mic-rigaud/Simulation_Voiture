package fr.ensta.simulation;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.voiture.Voiture;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.simulation.action.DeplacerVoiture;

public class VoitureEntity extends SimEntity implements IRecordable {

	Voiture voiture;

	public VoitureEntity(SimEngine engine, String nom, PointES depart, PointES arrive) {
		super(engine, "Voiture");
		voiture = new Voiture(nom, depart, arrive);
	}

	public void deplacerVoiture() {
		voiture.deplacerVoiture();
		Logger.Data(this);
		if (!voiture.isArrive()) {
			int duree = voiture.getDuree();
			this.addEvent(new DeplacerVoiture(getEngine().SimulationDate().add(LogicalDuration.ofMillis(duree)), this));
		}
	}

	@Override
	public String[] getTitles() {
		String[] rep = { "Nom", "Heure", "Situation", "Vitesse", "Direction de deplacement", "Arrive", "Feux Stop" };
		return rep;
	}

	@Override
	public String[] getRecords() {
		String heure = getEngine().SimulationDate().toString();
		String[] rep = { voiture.nom, heure, voiture.getPosition().toString(), String.valueOf(voiture.getVitesse()),
				IElement.getDirection(voiture.direction), voiture.getArrive(), String.valueOf(voiture.getFeuxStop()) };
		return rep;
	}

	@Override
	public String getClassement() {
		return "Voiture";
	}

}
