package fr.ensta.simulation;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.base.utility.IRecordable;
import enstabretagne.base.utility.Logger;
import fr.ensta.element.noeud.intersection.FeuIntersection;
import fr.ensta.lerouxlu.simu.SimEngine;
import fr.ensta.lerouxlu.simu.impl.SimEntity;
import fr.ensta.simulation.action.ChangerCouleur;

public class FeuEntity extends SimEntity implements IRecordable {

	private FeuIntersection feu;

	public FeuEntity(SimEngine engine, String nom) {
		super(engine, "Feu");
		this.feu = new FeuIntersection(nom);
		this.start();
	}

	public void start() {
		this.addEvent(new ChangerCouleur(getEngine().SimulationDate().add(LogicalDuration.ofSeconds(35)), this));
	}

	public void changerCouleur() {
		feu.changerCouleurFeu();
		Logger.Data(this);
		this.addEvent(new ChangerCouleur(
				getEngine().SimulationDate().add(LogicalDuration.ofSeconds(feu.tempsAttente())), this));
	}

	public FeuIntersection getFeu() {
		return feu;
	}

	@Override
	public String[] getTitles() {
		String[] rep = { "Nom", "Token", "Couleur" };
		return rep;
	}

	@Override
	public String[] getRecords() {
		return feu.etatIntersection();
	}

	@Override
	public String getClassement() {
		return "Feu";
	}

}