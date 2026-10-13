package com.example.jpa.test;

import com.example.jpa.entities.Equipement;
import com.example.jpa.entities.Reservation;
import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;
import com.example.jpa.service.ServiceGestion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceGestionTest {

    private ServiceGestion service;

    @Before
    public void setUp() {
        service = new ServiceGestion();
    }

    @After
    public void tearDown() {
        service.fermer();
    }

    @Test
    public void testCreationEtRechercheSalle() {
        Salle salle = new Salle("Salle Test", 20);
        service.ajouterSalle(salle);

        List<Salle> salles = service.listerSalles();
        assertFalse(salles.isEmpty());
        assertEquals("Salle Test", salles.get(0).getNom());
    }

    @Test
    public void testCreationUtilisateurAvecSalle() {
        Salle salle = new Salle("Salle B", 15);
        service.ajouterSalle(salle);

        Utilisateur utilisateur = new Utilisateur("youness", "younessbakka@gmail.com", salle);
        service.ajouterUtilisateur(utilisateur);

        List<Utilisateur> utilisateurs = service.listerUtilisateurs();
        assertFalse(utilisateurs.isEmpty());
        assertEquals("youness", utilisateurs.get(0).getNom());
        assertEquals("Salle B", utilisateurs.get(0).getSalle().getNom());
    }

    @Test
    public void testRechercheUtilisateursParSalle() {
        Salle salle = new Salle("Salle C", 30);
        service.ajouterSalle(salle);

        Utilisateur u1 = new Utilisateur("youness1", "younessbakka5@gmail.com", salle);
        Utilisateur u2 = new Utilisateur("youness2", "younessbakka88@gmail.com.com", salle);
        service.ajouterUtilisateur(u1);
        service.ajouterUtilisateur(u2);

        List<Utilisateur> utilisateurs = service.utilisateursParSalle("Salle C");
        assertEquals(2, utilisateurs.size());
    }

    @Test
    public void testCreationEquipement() {
        Equipement eq = new Equipement("Microphone sans fil", "Micro UHF de conférence");
        service.ajouterEquipement(eq);

        List<Equipement> equipements = service.listerEquipements();
        assertFalse(equipements.isEmpty());
        assertTrue(equipements.stream().anyMatch(e -> e.getNom().equals("Microphone sans fil")));
    }

    @Test
    public void testCreationReservation() {
        Salle salle = new Salle("Salle D", 40);
        service.ajouterSalle(salle);

        Utilisateur user = new Utilisateur("younessRes", "younessres@gmail.com", salle);
        service.ajouterUtilisateur(user);

        Reservation res = new Reservation(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                "Réunion Projet TP 3",
                user,
                salle
        );
        service.ajouterReservation(res);

        List<Reservation> reservations = service.reservationsParUtilisateur("younessres@gmail.com");
        assertFalse(reservations.isEmpty());
        assertEquals("Réunion Projet TP 3", reservations.get(0).getMotif());
    }
}