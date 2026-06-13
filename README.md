# FIND MY WORD

## À propos
Ce projet a été réalisé par Karl et Iyad dans le cadre de notre SAÉ (Situation d'Apprentissage et d'Évaluation) en 1ère année de BUT Informatique à l'IUT de Villetaneuse. Il s'agit d'un jeu de devinette de mots en console inspiré de Wordle, jouable en solo ou à deux, avec un mode classique et un mode contre-la-montre.

## Structure du projet
Le code respecte l'architecture MVC (Modèle-Vue-Contrôleur) :

* **main/** : Le point d'entrée de l'application et la boucle principale (`Main.java`, `App.java`).
* **controller/** : La logique métier et les différents modes de jeu (`Game`, `GameChrono`, `GamePoints`).
* **model/** : Les objets manipulés par le jeu (`Joueur`, `Word`, `ResultatPartie`).
* **view/** : L'interface utilisateur en console et la gestion sécurisée des saisies (`GameUI`).
* **repository/** : Le chargement des mots depuis le fichier de données (`JsonWordRepository`, `WordRepository`).
* **jar/** : Contient la librairie externe Gson nécessaire pour lire le JSON.
* **data/** : Contient le fichier `mots.json` listant les mots secrets.

## Commandes pour lancer le jeu

Placez-vous à la racine du projet dans votre terminal.

1. Pour compiler les fichiers (en incluant la librairie Gson) :
```bash
javac -cp "jar/*" main/*.java model/*.java controller/*.java view/*.java repository/*.java
```

2. Pour exécuter le jeu :
```bash
java -cp ".;jar/*" main.Main
```

---
**Auteurs :** Karl & Iyad