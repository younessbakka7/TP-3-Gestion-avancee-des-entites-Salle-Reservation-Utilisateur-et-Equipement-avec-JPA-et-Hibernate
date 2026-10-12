# Gestion des salles – JPA et Hibernate

## 1. Présentation du projet

Ce projet est une application Java développée avec Maven, JPA et Hibernate. Il permet de gérer des salles et des utilisateurs en utilisant une base de données H2 en mémoire.

<img width="562" height="384" alt="image" src="https://github.com/user-attachments/assets/1a28778f-f066-43d0-a849-5d4d16d6f185" />

**Création des tables :** Cette capture montre la création automatique des tables `Salle` et `Utilisateur` par Hibernate à partir des entités JPA.

<img width="598" height="491" alt="image" src="https://github.com/user-attachments/assets/f5ac0d0f-b2cd-4a2a-9e5f-7f60c7a46070" />

**Configuration des contraintes :** Cette capture montre la création des contraintes d’unicité sur le nom de la salle et l’e-mail de l’utilisateur, ainsi que la clé étrangère reliant `Utilisateur` à `Salle`.

<img width="434" height="356" alt="image" src="https://github.com/user-attachments/assets/13f34b81-48af-47db-9be4-8898cd5af7b1" />

**Insertion des données :** Cette capture montre l’ajout d’une salle et d’un utilisateur dans la base de données H2 à l’aide de Hibernate. L’opération est réussie et l’identifiant de la salle créée est 1.

<img width="516" height="273" alt="image" src="https://github.com/user-attachments/assets/302ff6af-740c-4ee8-ab7e-d6628e6fcc8b" />

**Recherche des salles avec JPQL :** Cette capture montre l’exécution d’une requête JPQL permettant de rechercher les salles ayant une capacité supérieure à 20 personnes. Le résultat affiche la salle « Salle EE », avec une capacité de 25 personnes.

<img width="1024" height="415" alt="image" src="https://github.com/user-attachments/assets/d9f94b87-995c-4cef-ad4f-5efb40fb9f28" />

**Recherche des utilisateurs avec JPQL :** Cette capture montre l’exécution d’une requête JPQL utilisant `LIKE` pour rechercher les utilisateurs dont le nom contient « youness ». Le résultat affiche l’utilisateur « youness ». L’exécution du programme s’est terminée avec succès (`exit code 0`).

<img width="1278" height="590" alt="image" src="https://github.com/user-attachments/assets/69712897-e336-4054-b370-3af979e6c1cf" />

**Exécution des tests unitaires :** Cette capture présente les trois tests JUnit de la classe `ServiceGestionTest`, qui vérifient la création et la recherche des salles, la création d’un utilisateur associé à une salle et la recherche des utilisateurs par salle.
