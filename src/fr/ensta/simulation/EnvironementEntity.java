package fr.ensta.simulation;

import java.util.ArrayList;
import java.util.Random;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.intersection.FeuIntersection;
import fr.ensta.element.noeud.intersection.Stop;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.Route;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.simulation.action.AjouterVoiture;
import fr.ensta.simulation.action.DeplacerVoiture;

public class EnvironementEntity extends SimEntity {

	private ArrayList<VoitureEntity> voitures;
	private ArrayList<PointES> pointESs;
	private SimEngine engine;
	private Random rdm;
	private static int nbrVoiture = 0;

	public EnvironementEntity(SimEngine engine) {
		super(engine, "Environement");
		this.engine = engine;
		pointESs = new ArrayList<PointES>();
		voitures = new ArrayList<VoitureEntity>();
		rdm = new Random();
	}

	@Override
	public void initialize() {
		super.initialize();
		initialiserPlateau();
		Logger.Information(this, "initialize", "Environnement initialise");
	}

	private void initialiserPlateau() {
		// TODO: faire depuis un fichier conf
		PointES E1 = new PointES("P1");
		PointES E2 = new PointES("P2");
		PointES E3 = new PointES("P3");
		PointES E4 = new PointES("P4");
		PointES E5 = new PointES("P5");
		PointES E6 = new PointES("P6");
		PointES E7 = new PointES("P7");
		Stop st1 = new Stop("I1");
		Stop st2 = new Stop("I2");
		FeuIntersection st3 = (new FeuEntity(engine, "I3")).getFeu();
		Stop st4 = new Stop("I4");
		Route rt11 = new Route(3000, "R1.1");
		Route rt12 = new Route(1300, "R1.2");
		Route rt13 = new Route(2000, "R1.3");
		Route rt2 = new Route(4500, "R2");
		Route rt21 = new Route(4500, "R2.1");
		Route rt22 = new Route(800, "R2.2");
		Route rt23 = new Route(1400, "R2.3");
		Route rt31 = new Route(3500, "R3.1");
		Route rt32 = new Route(1000, "R3.2");
		Route rt4 = new Route(3000, "R4");

		E1.connecter(rt11, IElement.DROITE);
		E2.connecter(rt2, IElement.HAUT);
		E3.connecter(rt13, IElement.GAUCHE);
		E4.connecter(rt23, IElement.GAUCHE);
		E5.connecter(rt21, IElement.DROITE);
		E6.connecter(rt32, IElement.BAS);
		E7.connecter(rt4, IElement.BAS);

		st1.connecter(rt11, IElement.GAUCHE);
		st1.connecter(rt12, IElement.DROITE);
		st1.connecter(rt31, IElement.HAUT);
		st2.connecter(rt12, IElement.GAUCHE);
		st2.connecter(rt13, IElement.DROITE);
		st2.connecter(rt2, IElement.BAS);
		st3.connecter(rt22, IElement.GAUCHE);
		st3.connecter(rt23, IElement.DROITE);
		st3.connecter(rt4, IElement.HAUT);
		st4.connecter(rt21, IElement.GAUCHE);
		st4.connecter(rt22, IElement.DROITE);
		st4.connecter(rt32, IElement.HAUT);
		st4.connecter(rt31, IElement.BAS);

		st4.ajouterSignalisation(IElement.BAS);
		st4.ajouterSignalisation(IElement.HAUT);
		st1.ajouterSignalisation(IElement.HAUT);
		st2.ajouterSignalisation(IElement.BAS);
		// st3.ajouterSignalisation(IElement.HAUT);

		pointESs.add(E1);
		pointESs.add(E2);
		pointESs.add(E3);
		pointESs.add(E4);
		pointESs.add(E5);
		pointESs.add(E6);
		pointESs.add(E7);
	}

	@Override
	public String toString() {
		return "Environement";
	}

	@Override
	public void activate() {
		super.activate();
		Logger.Information(this, "activate", "Environement est active... creation de voiture...");
		this.addEvent(new AjouterVoiture(getEngine().SimulationDate().add(LogicalDuration.ofHours(6)), this));
		this.addEvent(new AjouterVoiture(
				getEngine().SimulationDate().add(LogicalDuration.ofHours(6).add(LogicalDuration.ofSeconds(1))), this));
		// this.addEvent(new
		// AjouterVoiture(getEngine().SimulationDate().add(LogicalDuration.ofHours(6)),
		// this));
		// this.addEvent(new
		// AjouterVoiture(getEngine().SimulationDate().add(LogicalDuration.ofHours(6)),
		// this));

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

	public void creerVoiture() {
		int entre = rdm.nextInt(pointESs.size());
		int sortie = entre;
		// while (sortie == entre) {
		// sortie = rdm.nextInt(pointESs.size());
		// }
		if (nbrVoiture < 2) {
			entre = 6;
			sortie = 3;
		} else {
			entre = 3;
			sortie = 6;
		}
		Logger.Information(this, "info",
				"Voiture cree entre :" + String.valueOf(entre + 1) + " sortie : " + String.valueOf(sortie + 1));
		VoitureEntity voiture = new VoitureEntity(engine, "V" + String.valueOf(nbrVoiture++), pointESs.get(entre),
				pointESs.get(sortie));
		voitures.add(voiture);

		voiture.addEvent(new DeplacerVoiture(getEngine().SimulationDate(), voiture));
	}

}
