package fr.ensta.voiture;

import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;

public class VoitureEntity extends SimEntity {
	
	Voiture voiture;

	public VoitureEntity(SimEngine engine, String type) {
		super(engine, type);
		voiture = new Voiture();
	}

}
