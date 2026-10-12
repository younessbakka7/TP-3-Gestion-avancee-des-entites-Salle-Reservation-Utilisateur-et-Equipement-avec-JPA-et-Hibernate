
package com.example.jpa;

import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("gestionPU");

        EntityManager em = emf.createEntityManager();

        try {
            // Création des données de test
            em.getTransaction().begin();

            Salle salle = new Salle("Salle EE", 25);
            em.persist(salle);

            Utilisateur utilisateur = new Utilisateur(
                    "youness",
                    "youness@gmail.com",
                    salle
            );
            em.persist(utilisateur);

            em.getTransaction().commit();

            System.out.println("Salle ajoutée avec succès !");
            System.out.println("ID : " + salle.getId());

            // 1. Rechercher les salles avec une capacité supérieure à 20
            List<Salle> grandesSalles = em.createQuery(
                            "SELECT s FROM Salle s WHERE s.capacite > :minCapacite",
                            Salle.class
                    )
                    .setParameter("minCapacite", 20)
                    .getResultList();

            System.out.println("\n--- Salles avec capacité > 20 ---");

            for (Salle s : grandesSalles) {
                System.out.println(
                        s.getNom() + " - Capacité : " + s.getCapacite()
                );
            }

            // 2. Récupérer les utilisateurs dont le nom contient "Jean"
            List<Utilisateur> utilisateurs = em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.nom LIKE :pattern",
                            Utilisateur.class
                    )
                    .setParameter("pattern", "%youness%")
                    .getResultList();

            System.out.println("\n--- Utilisateurs contenant youness ---");

            for (Utilisateur u : utilisateurs) {
                System.out.println(u.getNom());
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
