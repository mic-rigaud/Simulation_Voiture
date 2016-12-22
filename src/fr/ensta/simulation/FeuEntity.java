package fr.ensta.simulation;

import enstabretagne.base.time.LogicalDuration;
import fr.ensta.element.noeud.intersection.Feu;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.simulation.action.ChangerCouleurFeu;

public class FeuEntity extends SimEntity {

	Feu feu;

	public FeuEntity(SimEngine engine, String nom) {
		super(engine, "Voiture");
		feu = new Feu(nom);
		this.addEvent(new ChangerCouleurFeu(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(20)), this));
	}

	public void changerCouleur() {
		feu.changerCouleur();
		int duree = feu.tempsAttente();
		this.addEvent(new ChangerCouleurFeu(getEngine().SimulationDate().add(LogicalDuration.ofSeconds(duree)), this));
	}

	public Feu getFeu() {
		return feu;
	}

}