package fr.ensta.simulation.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.lerouxlu.simu.impl.SimEvent;
import fr.ensta.simulation.FeuEntity;

public class ChangerCouleurFeu extends SimEvent {

	private FeuEntity feu;

	public ChangerCouleurFeu(LogicalDateTime scheduledDate, FeuEntity feu) {
		super(scheduledDate);
		this.feu = feu;
	}

	@Override
	public void process() {
		feu.changerCouleur();
	}

}
