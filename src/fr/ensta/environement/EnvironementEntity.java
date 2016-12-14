package fr.ensta.environement;

import java.util.ArrayList;

import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.noeud.Noeud;
import fr.ensta.troncons.Troncon;
import fr.ensta.voiture.VoitureEntity;

public class EnvironementEntity extends SimEntity implements IRecordable {

	private ArrayList<VoitureEntity> voitures;
	private ArrayList<Noeud> noeuds;
	private ArrayList<Troncon> troncons;
	private SimEngine engine;
	
	public EnvironementEntity(SimEngine engine, String type) {
		super(engine, type);
		this.engine = engine;
	}

	
	@Override
	public void initialize() {
		super.initialize();
		initialiserPlateau();
		Logger.Information(this, "initialize", "Environnement initialise");
	}
	
	@Override
	public String toString() {
		return "Environement";
	}
	
	@Override
	public void activate() {
		super.activate();
		Logger.Information(this, "activate", "Environement est active... creation de voiture...");
		this.addEvent(new AjouterVoiture(getEngine().SimulationDate(), this));
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		Logger.Information(this, "info", "Environement desactive");
	}
	@Override
	public void terminate() {
		super.terminate();
		Logger.Information(this, "info","Environement termine");

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


	public void creerVoiture() {
		VoitureEntity voiture = new VoitureEntity(engine,"voiture");
		
		
	}

}
