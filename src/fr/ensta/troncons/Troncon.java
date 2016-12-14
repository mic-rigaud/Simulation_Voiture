package fr.ensta.troncons;

import java.util.ArrayList;

import enstabretagne.base.utility.IRecordable;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.noeud.Noeud;
import fr.ensta.voiture.Voiture;

public class Troncon implements IRecordable {

	private int longueur;
	private ArrayList<Voiture> voitureAlle;
	private ArrayList<Voiture> voitureRetour;
	private Noeud entre;
	private Noeud sortie;
	

	public void initialiser(Noeud entre, Noeud sortie){
		this.entre = entre;
		this.sortie = sortie;
	}

	public void addVoitureAlle(Voiture voiture){
		this.voitureAlle.add(voiture);
	}
	
	public void removeVoitureAlle(Voiture voiture){
		this.voitureAlle.remove(voiture);
	}
	
	public void addVoitureRetour(Voiture voiture){
		this.voitureRetour.add(voiture);
	}
	
	public void removeVoitureRetour(Voiture voiture){
		this.voitureRetour.remove(voiture);
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
