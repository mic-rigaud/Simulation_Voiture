package fr.ensta.environement.action;

import enstabretagne.base.time.LogicalDateTime;
import fr.ensta.environement.EnvironementEntity;
import fr.ensta.lerouxlu.simu.impl.SimEvent;

public class AjouterVoiture extends SimEvent {

	private EnvironementEntity env;
	
	public AjouterVoiture(LogicalDateTime scheduledDate, EnvironementEntity env){
		super(scheduledDate);
		this.env = env;
	}
	
	@Override
	public void process() {
		env.creerVoiture();
	}

}