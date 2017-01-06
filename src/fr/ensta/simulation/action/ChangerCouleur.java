package fr.ensta.simulation.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.lerouxlu.simu.impl.SimEvent;
import fr.ensta.simulation.FeuEntity;

public class ChangerCouleur extends SimEvent {

	private FeuEntity feu;

	public ChangerCouleur(LogicalDateTime scheduledDate, FeuEntity feu) {
		super(scheduledDate);
		this.feu = feu;
	}

	@Override
	public void process() {
		feu.changerCouleur();
	}

}