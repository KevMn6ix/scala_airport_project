# scala_airport_project

## Description
L'**scala_airport_project** est une application écrite en **Scala** qui permet de manipuler et d'extraire des informations à partir de trois fichiers CSV :
- **countries.csv** : Liste des pays
- **airports.csv** : Liste des aéroports
- **runways.csv** : Liste des pistes.

---

## Fonctionnalités
1. **Requêtes par pays ou code :**
   - Recherchez un pays par son nom ou son code ISO (par exemple, *France* ou *FR*).
   - Obtenez les détails des aéroports et des pistes associés.

2. **Rapports statistiques :**
   - Top 10 des pays avec le plus grand nombre d'aéroports.
   - Top 10 des pays avec le plus petit nombre d'aéroports.
   - Types de pistes disponibles par pays.
   - Les latitudes de pistes les plus fréquentes.

---

## Prérequis
- **Scala** (version 2.13.x recommandée)
- **SBT** (Scala Build Tool)
- **Java JDK** (version 8 ou supérieure)
- Les fichiers CSV suivants placés dans le répertoire racine du projet :
  - `countries.csv`
  - `airports.csv`
  - `runways.csv`

---

## Installation
1. **Clonez le dépôt :**
   ```bash
   git clone https://github.com/KevMn6ix/scala_airport_project.git
   cd airport
   sbt compile
   sbt run


# Have Fun ! 

