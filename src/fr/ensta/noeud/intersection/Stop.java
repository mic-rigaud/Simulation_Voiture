package fr.ensta.noeud.intersection;

import java.util.HashMap;

import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.troncons.Troncon;
import fr.ensta.voiture.Voiture;

public class Stop implements Intersection {
	
	private HashMap<Integer,Troncon> connection;


	public void addTroncon(int key, Troncon tr){
		connection.put(key, tr);
	}

	@Override
	public void entreVoiture(Voiture voiture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sortieVoiture(Voiture voiture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getTitles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClassement() {
		// TODO Auto-generated method stub
		return null;
	}

}
