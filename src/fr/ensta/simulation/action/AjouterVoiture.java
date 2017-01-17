package fr.ensta.simulation.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.lerouxlu.simu.impl.SimEvent;
import fr.ensta.simulation.EnvironementEntity;

public class AjouterVoiture extends SimEvent {

	private EnvironementEntity env;
	private int entree;

	public AjouterVoiture(LogicalDateTime scheduledDate, EnvironementEntity env, int entree) {
		super(scheduledDate);
		this.env = env;
		this.entree = entree - 1;
	}

	@Override
	public void process() {
		env.creerVoiture(entree);
	}

}
