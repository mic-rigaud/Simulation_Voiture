package fr.ensta.noeud;

import java.util.HashSet;
import java.util.Set;

import enstabretagne.base.utility.IRecordable;
import fr.ensta.lerouxlu.simu.EntityState;
import fr.ensta.lerouxlu.simu.ISimEntity;
import fr.ensta.lerouxlu.simu.ISimEvent;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.troncons.Troncon;
import fr.ensta.voiture.Voiture;

public interface Noeud extends IRecordable{
	
	public void addTroncon(int key, Troncon tr);
	
	public void entreVoiture(Voiture voiture);
	
	public void sortieVoiture(Voiture voiture);
	

	
}
