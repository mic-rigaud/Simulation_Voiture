package fr.ensta.element.noeud.intersection;

import enstabretagne.base.utility.Logger;
import fr.ensta.element.noeud.INoeud;

public interface IIntersection extends INoeud {

	/* La fonction la plus degeulasse du monde!! */
	default int getNewDirection(String position, String but) {
		String chemin = position + "," + but;
		switch (chemin) {
		case "I1,P1":
			return GAUCHE;
		case "I1,P2":
			return DROITE;
		case "I1,P3":
			return DROITE;
		case "I1,P4":
			return HAUT;
		case "I1,P5":
			return HAUT;
		case "I1,P6":
			return HAUT;
		case "I1,P7":
			return HAUT;

		case "I2,P1":
			return GAUCHE;
		case "I2,P2":
			return BAS;
		case "I2,P3":
			return DROITE;
		case "I2,P4":
			return GAUCHE;
		case "I2,P5":
			return GAUCHE;
		case "I2,P6":
			return GAUCHE;
		case "I2,P7":
			return GAUCHE;

		case "I3,P1":
			return GAUCHE;
		case "I3,P2":
			return GAUCHE;
		case "I3,P3":
			return GAUCHE;
		case "I3,P4":
			return DROITE;
		case "I3,P5":
			return GAUCHE;
		case "I3,P6":
			return GAUCHE;
		case "I3,P7":
			return HAUT;

		case "I4,P1":
			return BAS;
		case "I4,P2":
			return BAS;
		case "I4,P3":
			return BAS;
		case "I4,P4":
			return DROITE;
		case "I4,P5":
			return GAUCHE;
		case "I4,P6":
			return HAUT;
		case "I4,P7":
			return DROITE;
		default:
			Logger.Information(this, "info", "ERROR!!!");
		}
		return 0;
	}
}
