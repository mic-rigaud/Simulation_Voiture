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

		int tab[][] = { { 7, 40, 50, 30, 20, 30, 20, 30 }, //
				{ 2, 300, 200, 100, 100, 400, 50, 30 }, //
				{ 8, 20, 30, 20, 30, 20, 30, 10 }, //
				{ 2, 100, 150, 300, 200, 150, 100, 100 }, //
				{ 5, 20, 30, 15, 50, 20, 10, 10 } };

		// int tab[][] = { { 7, 4, 5, 0, 0, 0, 0, 0 } }; //
		// { 2, 300, 200, 100, 100, 400, 50, 30 }, //
		// { 8, 20, 30, 20, 30, 20, 30, 10 }, //
		// { 2, 100, 150, 300, 200, 150, 100, 100 }, //
		// { 5, 20, 30, 15, 50, 20, 10, 10 } };

		int time = 0;
		for (int ind1 = 0; ind1 < tab.length; ind1++) {
			for (int ind2 = 1; ind2 < tab[0].length; ind2++) {
				for (int ind3 = 1; ind3 <= tab[ind1][ind2]; ind3++) {
					this.addEvent(new AjouterVoiture(
							getEngine().SimulationDate().add(LogicalDuration.ofHours(time))
									.add(LogicalDuration.ofSeconds((3600 * ind3 * tab[ind1][0]) / tab[ind1][ind2])),
							this, ind2));
				}
			}
			time = time + tab[ind1][0];
		}
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

	public void creerVoiture(int entree) {
		int tab[][] = { { 5, 15, 25, 30, 65, 100 }, //
				{ 10, 15, 35, 55, 80, 100 }, //
				{ 15, 30, 50, 70, 90, 100 }, //
				{ 15, 25, 35, 55, 95, 100 }, //
				{ 10, 40, 50, 60, 70, 100 }, //
				{ 20, 30, 70, 80, 90, 100 }, //
				{ 20, 40, 60, 80, 90, 100 } };

		int sortie = 0;
		int a = rdm.nextInt(100);
		System.out.println(a);
		for (int ind = 0; ind < tab[0].length; ind++) {
			if (a > tab[entree][ind]) {
				sortie = ind + 1;
			} else
				break;
		}
		if (sortie >= entree)
			sortie++;
		Logger.Information(this, "info",
				"Voiture cree entre :" + String.valueOf(entree + 1) + " sortie : " + String.valueOf(sortie + 1));
		VoitureEntity voiture = new VoitureEntity(engine, "V" + String.valueOf(nbrVoiture++), pointESs.get(entree),
				pointESs.get(sortie));
		voitures.add(voiture);
		voiture.addEvent(new DeplacerVoiture(getEngine().SimulationDate(), voiture));
	}

}
