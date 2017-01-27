package fr.ensta.simulation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import fr.ensta.element.IElement;
import fr.ensta.element.noeud.INoeud;
import fr.ensta.element.noeud.intersection.FeuIntersection;
import fr.ensta.element.noeud.intersection.RondPoint;
import fr.ensta.element.noeud.intersection.Stop;
import fr.ensta.element.noeud.intersection.StopV1;
import fr.ensta.element.noeud.pointEntreSortie.PointES;
import fr.ensta.element.route.Route;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.simulation.action.AjouterVoiture;
import fr.ensta.simulation.action.DeplacerVoiture;
import json.JSONArray;
import json.JSONObject;

public class EnvironementEntity extends SimEntity implements IRecordable {

	private ArrayList<VoitureEntity> voitures;
	private ArrayList<PointES> pointESs;
	private ArrayList<IElement> elements;
	private SimEngine engine;
	private Random rdm;
	private static int nbrVoiture = 0;

	public static EnvironementEntity INSTANCE;

	public EnvironementEntity(SimEngine engine) {
		super(engine, "Environement");
		this.engine = engine;
		pointESs = new ArrayList<PointES>();
		voitures = new ArrayList<VoitureEntity>();
		elements = new ArrayList<IElement>();
		rdm = new Random();
		INSTANCE = this;
	}

	@Override
	public void initialize() {
		super.initialize();
		initialiserPlateau();
		Logger.Information(this, "initialize", "Environnement initialise");
	}

	private void initialiserPlateau() {

		try {
			BufferedReader buff = new BufferedReader(new FileReader("CONFIG.json"));
			String str = null;
			String lignes = "";
			while ((str = buff.readLine()) != null) {
				// System.out.println(str);
				lignes += str;
			}
			JSONArray json = new JSONArray(lignes);
			for (int i = 0; i < json.length(); i++) {
				JSONObject element = json.getJSONObject(i);
				switch (element.get("id").toString()) {
				case "route":
					Route rt = new Route(element.getInt("distance"), element.getString("name"));
					elements.add(rt);
					break;
				case "pointes":
					PointES E = new PointES(element.getString("name"));
					ajoutConnection(E, element.getJSONObject("connections"));
					pointESs.add(E);
					break;
				case "stop":
					Stop st = new StopV1(element.getString("name"));
					JSONObject signalisation = element.getJSONObject("signalisation");
					ajoutSignalisation(st, element.getJSONObject("signalisation"));
					ajoutConnection(st, element.getJSONObject("connections"));
					elements.add(st);
					break;
				case "feu":
					FeuIntersection feu = (new FeuEntity(engine, element.getString("name"), 1)).getFeu();
					ajoutConnection(feu, element.getJSONObject("connections"));
					elements.add(feu);
					break;
				case "rondpoint":
					RondPoint rp = new RondPoint(element.getString("name"));
					ajoutConnection(rp, element.getJSONObject("connections"));
					elements.add(rp);
					break;
				default:
					System.out.println("prob");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ajoutSignalisation(Stop st, JSONObject signalisation) {
		if (signalisation.has("bas"))
			st.ajouterSignalisation(IElement.BAS);
		if (signalisation.has("haut"))
			st.ajouterSignalisation(IElement.HAUT);
		if (signalisation.has("gauche"))
			st.ajouterSignalisation(IElement.GAUCHE);
		if (signalisation.has("droite"))
			st.ajouterSignalisation(IElement.DROITE);
	}

	private void ajoutConnection(INoeud el, JSONObject connection) {
		if (connection.has("bas"))
			for (IElement e : elements) {
				if (e.toString().equals(connection.get("bas"))) {
					el.connecter((Route) e, IElement.BAS);
				}
			}
		if (connection.has("haut"))
			for (IElement e : elements) {
				if (e.toString().equals(connection.get("haut"))) {
					el.connecter((Route) e, IElement.HAUT);
				}
			}
		if (connection.has("gauche"))
			for (IElement e : elements) {
				if (e.toString().equals(connection.get("gauche"))) {
					el.connecter((Route) e, IElement.GAUCHE);
				}
			}
		if (connection.has("droite"))
			for (IElement e : elements) {
				if (e.toString().equals(connection.get("droite"))) {
					el.connecter((Route) e, IElement.DROITE);
				}
			}
	}

	@Override
	public String toString() {
		return "Environement";
	}

	@Override
	public void activate() {
		super.activate();
		Logger.Information(this, "activate", "Environement est active... creation de voiture...");

		// int tab[][] = { { 7, 40, 50, 30, 20, 30, 20, 30 }, //
		// { 2, 300, 200, 100, 100, 400, 50, 30 }, //
		// { 8, 20, 30, 20, 30, 20, 30, 10 }, //
		// { 2, 100, 150, 300, 200, 150, 100, 100 }, //
		// { 5, 20, 30, 15, 50, 20, 10, 10 } };

		int tab[][] = { { 7, 280, 350, 140, 140, 210, 140, 210 }, //
				{ 2, 600, 400, 200, 200, 800, 100, 60 }, //
				{ 8, 160, 240, 160, 240, 160, 240, 80 }, //
				{ 2, 200, 300, 600, 400, 300, 200, 200 }, //
				{ 5, 100, 150, 75, 250, 100, 50, 50 } };

		// int tab[][] = { { 1, 1, 0, 0, 0, 0, 0, 0 } }; //

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

	public void flash() {
		Logger.Data(this);
	}

	@Override
	public String[] getTitles() {
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).toString().startsWith("R")) {
				liste.add(elements.get(i).toString().concat(" Sens Postif"));
				liste.add(elements.get(i).toString().concat(" Sens Negatif"));
			} else {
				liste.add(elements.get(i).toString());
			}

		}
		String[] rep = new String[liste.size()];
		rep = liste.toArray(rep);
		return rep;
	}

	@Override
	public String[] getRecords() {
		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < elements.size(); i++) {
			int[] rep = elements.get(i).getNbVoiture();
			for (int ind : rep) {
				liste.add(String.valueOf(ind));
			}
		}
		String[] rep = new String[liste.size()];
		rep = liste.toArray(rep);
		return rep;
	}

	@Override
	public String getClassement() {
		return "Graphe";
	}

}
