package fr.ensta.simulation.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.lerouxlu.simu.impl.SimEvent;
import fr.ensta.simulation.VoitureEntity;

public class DeplacerVoiture extends SimEvent {

	private VoitureEntity voitureE;

	public DeplacerVoiture(LogicalDateTime scheduledDate, VoitureEntity voitureE) {
		super(scheduledDate);
		this.voitureE = voitureE;
	}

	@Override
	public void process() {
		voitureE.deplacerVoiture();
	}

}