package fr.ensta.environement.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.element.voiture.VoitureEntity;
import fr.ensta.lerouxlu.simu.impl.SimEvent;

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