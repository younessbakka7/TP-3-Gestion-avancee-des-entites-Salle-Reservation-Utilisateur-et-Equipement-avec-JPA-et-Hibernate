package com.example.jpa;

import com.example.jpa.entities.Equipement;
import com.example.jpa.entities.Reservation;
import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("gestionPU");

        EntityManager em = emf.createEntityManager();

        try {
            // Création des données de test
            em.getTransaction().begin();

            // 1. Création et persistance des équipements
            Equipement eq1 = new Equipement("Vidéoprojecteur", "Projecteur Full HD");
            Equipement eq2 = new Equipement("Tableau Blanc Interactif", "Écran tactile");
            em.persist(eq1);
            em.persist(eq2);

            // 2. Création et persistance de la salle avec équipements
            Salle salle = new Salle("Salle EE", 25);
            salle.addEquipement(eq1);
            salle.addEquipement(eq2);
            em.persist(salle);

            // 3. Création et persistance de l'utilisateur
            Utilisateur utilisateur = new Utilisateur(
                    "youness",
                    "youness@gmail.com",
                    salle
            );
            em.persist(utilisateur);

            // 4. Création et persistance d'une réservation
            Reservation reservation = new Reservation(
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(2),
                    "Séance de TP 3 - JPA Hibernate",
                    utilisateur,
                    salle
            );
            em.persist(reservation);

            em.getTransaction().commit();

            System.out.println("Salle ajoutée avec succès !");
            System.out.println("ID : " + salle.getId());
            System.out.println("Équipements associés : " + salle.getEquipements().size());
            System.out.println("Réservation créée avec succès !");

            // 1. Rechercher les salles avec une capacité supérieure à 20
            List<Salle> grandesSalles = em.createQuery(
                            "SELECT s FROM Salle s WHERE s.capacite > :minCapacite",
                            Salle.class
                    )
                    .setParameter("minCapacite", 20)
                    .getResultList();

            System.out.println("\n--- Salles avec capacité > 20 ---");
            for (Salle s : grandesSalles) {
                System.out.println(s.getNom() + " - Capacité : " + s.getCapacite());
            }

            // 2. Récupérer les utilisateurs dont le nom contient "youness"
            List<Utilisateur> utilisateurs = em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.nom LIKE :pattern",
                            Utilisateur.class
                    )
                    .setParameter("pattern", "%youness%")
                    .getResultList();

            System.out.println("\n--- Utilisateurs contenant youness ---");
            for (Utilisateur u : utilisateurs) {
                System.out.println(u.getNom() + " (" + u.getEmail() + ")");
            }

            // 3. Rechercher les salles disposant d'un Vidéoprojecteur
            List<Salle> sallesAvecVp = em.createQuery(
                            "SELECT DISTINCT s FROM Salle s JOIN s.equipements eq WHERE eq.nom LIKE :nomEq",
                            Salle.class
                    )
                    .setParameter("nomEq", "%Vidéoprojecteur%")
                    .getResultList();

            System.out.println("\n--- Salles équipées d'un Vidéoprojecteur ---");
            for (Salle s : sallesAvecVp) {
                System.out.println(s.getNom() + " (Capacité : " + s.getCapacite() + ")");
            }

            // 4. Récupérer les réservations pour la salle
            List<Reservation> reservationsSalle = em.createQuery(
                            "SELECT r FROM Reservation r WHERE r.salle.nom = :nomSalle",
                            Reservation.class
                    )
                    .setParameter("nomSalle", "Salle EE")
                    .getResultList();

            System.out.println("\n--- Réservations pour la Salle EE ---");
            for (Reservation r : reservationsSalle) {
                System.out.println("Motif : " + r.getMotif() + " | Réservé par : " + r.getUtilisateur().getNom());
            }

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            e.printStackTrace();

        } finally {
            em.close();
            emf.close();
        }
    }
}
