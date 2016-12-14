package fr.ensta.noeud.pointEntreSortie;

import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.noeud.Noeud;
import fr.ensta.troncons.Troncon;
import fr.ensta.voiture.Voiture;

public class PointES implements Noeud {

	private Troncon entre;

	
	public void addTroncon(int key, Troncon tr){
		this.entre = tr;
	}
	

	
	public void entreVoiture(Voiture voiture) {
		// TODO Auto-generated method stub
		
	}


	
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
