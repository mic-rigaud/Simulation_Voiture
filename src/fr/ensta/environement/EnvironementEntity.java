package fr.ensta.environement;

import java.util.ArrayList;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.INoeud;
import fr.ensta.element.noeud.intersection.Stop;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.Route;
import fr.ensta.element.voiture.VoitureEntity;
import fr.ensta.environement.action.AjouterVoiture;
import fr.ensta.environement.action.DeplacerVoiture;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;

public class EnvironementEntity extends SimEntity implements IRecordable {

	private ArrayList<VoitureEntity> voitures;
	private ArrayList<INoeud> noeuds;
	private ArrayList<Route> route;
	private SimEngine engine;

	public EnvironementEntity(SimEngine engine) {
		super(engine, "Environement");
		this.engine = engine;
		noeuds = new ArrayList<INoeud>();
		route = new ArrayList<Route>();
		voitures = new ArrayList<VoitureEntity>();
	}

	@Override
	public void initialize() {
		super.initialize();
		initialiserPlateau();
		Logger.Information(this, "initialize", "Environnement initialise");
	}

	private void initialiserPlateau() {
		PointES E1 = new PointES();
		Stop st = new Stop();
		PointES E2 = new PointES();
		Route rt1 = new Route(5);
		Route rt2 = new Route(5);
		E1.connecter(rt1, IElement.DROITE);
		st.connecter(rt1, IElement.GAUCHE);
		st.connecter(rt2, IElement.DROITE);
		E2.connecter(rt2, IElement.GAUCHE);
		noeuds.add(E1);
		noeuds.add(st);
		noeuds.add(E2);
		route.add(rt1);
		route.add(rt2);
	}

	@Override
	public String toString() {
		return "Environement";
	}

	@Override
	public void activate() {
		super.activate();
		Logger.Information(this, "activate", "Environement est active... creation de voiture...");
		this.addEvent(new AjouterVoiture(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(20)), this));
	}

	@Override
	public void deactivate() {
		super.deactivate();
		Logger.Information(this, "info", "Environement desactive");
	}

	@Override
	public void terminate() {
		super.terminate();
		Logger.Information(this, "info", "Environement termine");

	}

	@Override
	public String[] getTitles() {

		return null;
	}

	@Override
	public String[] getRecords() {

		return null;
	}

	@Override
	public String getClassement() {

		return null;
	}

	public void creerVoiture() {
		VoitureEntity voiture = new VoitureEntity(engine, "voiture", (PointES) noeuds.get(0), (PointES) noeuds.get(2));
		voitures.add(voiture);
		Logger.Information(this, "info", "Voiture cree");
		voiture.addEvent(new DeplacerVoiture(getEngine().SimulationDate(), voiture));
	}

}
