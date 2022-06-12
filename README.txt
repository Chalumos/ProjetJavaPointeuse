  _______ _                  _______             _             
 |__   __(_)                |__   __|           | |            
    | |   _ _ __ ___   ___     | |_ __ __ _  ___| | _____ _ __ 
    | |  | | '_ ` _ \ / _ \    | | '__/ _` |/ __| |/ / _ \ '__|
    | |  | | | | | | |  __/    | | | | (_| | (__|   <  __/ |   
    |_|  |_|_| |_| |_|\___|    |_|_|  \__,_|\___|_|\_\___|_|   


MEMBRES DU PROJET :

	- Charles CAILLON
	- Aurélien DAFONSECA
	- Théo MENDES
	- Jules PASCOT

ORGANISATION DES SOURCES :

	Ce projet a été construit autour d'un pattern MVC, lui-même basé sur un pattern Observer. Chaque contrôleur est un observable 
	et chaque vue est un observateur. Ainsi, les vues observent donc des contrôleurs et se mettent à jour dès que les contrôleurs 
	qu'elles observent les notifient. Cette pratique nous permet de bien séparer la partie interface et la partie logique du projet.

	En plus des vues et des contrôleurs, nous avons ajouté des classes dites "view controller" qui sont les contrôleurs par défaut 
	des vues JavaFX et dont le rôle est simplement de fournir les composants et méthodes d'une vue. Ces classes sont placées dans 
	le package view du MVC puisqu'elles ne servent que d'intermédiaire entre la vue et JavaFX.

	La pointeuse et l'application de monitoring appartiennent au même projet. Les deux utilisent les mêmes classes du package model, 
	mais ont chacune leur répertoire respectif dans les packages view et controller.

	Les fichiers de stockage de l'application et de la pointeuse sont placés dans un package data dans les ressources du projet.

	Pour finir, il n'y a qu'une seule méthode main(), placée dans la classe Main à la racine du projet.

INFORMATIONS IMPORTANTES :

	Lors de l'import de pointages depuis un fichier texte, seuls les pointages correspondant à des employés déjà présents dans 
	l'entreprise sont importés, les autres sont ignorés.

	Si la liste des employés des l'entreprise est mise à jour, il est nécessaire de relancer la pointeuse pour que celle-ci 
	récupère la dernière liste des employés.
	

COMMENT LANCER LES PROGRAMES :

	Pour pouvoir lancer la pointeuse ou l'application de monitoring, il faut appeler la méthode main() du projet avec 2 arguments.

	- Le 1er paramètre est le numéro de l'application qu'on souhaite lancer :
		1 : L'application de monitoring.
		2 : La pointeuse.

	- Le 2nd paramètre est un booléen et indique si les données doivent être régénérées à partir de la méthode Stub ou non.
		true : Les données sont régénérées à partir de la méthode Stub.
		false : Les données ne sont pas regénérées et on utilise celles précédemment stockées dans les fichiers de stockage.

	Exemples :
		1 true  => Lance l'application en générant les données via la méthode stub.
		2 false => Lance la pointeuse en utilisant les données déja existantes.