# Prudence

Application de gestion scolaire en Java : élèves, familles, classes, inscriptions, paiements et
reçus, enseignants, documents et journalisation.

## Architecture

Projet Maven multi-modules, découpé en couches :

- `model` : entités et objets métier
- `dao` : accès aux données
- `service` : règles de gestion
- `controller` : points d'entrée de l'application (élèves, familles, paiements, inscriptions,
  classes, enseignants, documents, utilisateurs, journalisation)
- `view` : interface de présentation
- `contract` : interfaces partagées
- `utils` : utilitaires communs
- `main` : point d'entrée (`main.Main`)
- `doc` : documentation du projet

## Dépendances

MySQL Connector pour la persistance, Log4j2 pour la journalisation, JUnit 5 et Mockito pour les
tests.

## Compiler et exécuter

```bash
mvn clean install
mvn -pl main exec:java -Dexec.mainClass=main.Main
```

La connexion à la base se configure dans le fichier de configuration du module `dao`.

## Contexte

Projet réalisé pendant ma formation d'ingénieur, repris ici comme vitrine de code Java. Le dépôt
`School` de mon compte ne contient que les artefacts compilés de ce projet, les sources sont ici.
