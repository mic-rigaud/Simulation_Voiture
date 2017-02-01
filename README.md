# Simulation trafic routier

## Description

Ce répertoire contient un programme java qui simule un trafic routier. Ce
programme réponds a un exercice donné à l'Ensta Bretagne durant l'UV 5.8. Le
projet consiste à donner des solutions d'améliorations pour un quartier du nom de
"Coruscant". Ces améliorations s'appuieront sur la simulation du trafic routier.

## Utilisation
Après telechargement de l archive il faut configurer le build path pour avoir le moteur de simulation.

Ensuite, il faut lancer le fichier Main.java pour executer le programme.

Si l on soutaite une simulation plus rapide avec moins de voitures, il faut changer dans la fonction _activate_ de la classe _EnvironementEntity_ la variable tab. Cette variable contient dans la premiere colonnes une tranche d'heure et dans les colonnes suivantes le nombre de voiture au points d entre P1,P2,P3,P4,P5,P6,et P7 dans cette tranche horaire.

Pour réactive le log des feux de circulation, il faut decommenter la ligne adéquate dans la classe _FeuEntity_.

## Notes
- Dans le fichier CONFIG.json qui contient l architecture de notre quartier, les routes doivent etre placées en premieres.


## Auteurs
- Neau Guillaume
- Rigaud Michaël

## Version
v 1.0
