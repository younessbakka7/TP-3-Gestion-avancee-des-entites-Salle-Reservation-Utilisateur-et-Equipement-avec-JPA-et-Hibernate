package com.example.jpa.service;

import com.example.jpa.dao.EquipementDao;
import com.example.jpa.dao.ReservationDao;
import com.example.jpa.dao.SalleDao;
import com.example.jpa.dao.UtilisateurDao;
import com.example.jpa.entities.Equipement;
import com.example.jpa.entities.Reservation;
import com.example.jpa.entities.Salle;
import com.example.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ServiceGestion {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SalleDao salleDao;
    private UtilisateurDao utilisateurDao;
    private EquipementDao equipementDao;
    private ReservationDao reservationDao;

    public ServiceGestion() {
        emf = Persistence.createEntityManagerFactory("gestionPU");
        em = emf.createEntityManager();
        salleDao = new SalleDao(em);
        utilisateurDao = new UtilisateurDao(em);
        equipementDao = new EquipementDao(em);
        reservationDao = new ReservationDao(em);
    }

    public void fermer() {
        try {
            if (em != null && em.isOpen()) {
                em.close();
            }
        } finally {
            if (emf != null && emf.isOpen()) {
                emf.close();
            }
        }
    }

    public void ajouterSalle(Salle salle) {
        salleDao.create(salle);
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurDao.create(utilisateur);
    }

    public void ajouterEquipement(Equipement equipement) {
        equipementDao.create(equipement);
    }

    public void ajouterReservation(Reservation reservation) {
        reservationDao.create(reservation);
    }

    public List<Salle> listerSalles() {
        return salleDao.findAll();
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurDao.findAll();
    }

    public List<Equipement> listerEquipements() {
        return equipementDao.findAll();
    }

    public List<Reservation> listerReservations() {
        return reservationDao.findAll();
    }

    public List<Utilisateur> utilisateursParSalle(String nomSalle) {
        return utilisateurDao.findBySalleNom(nomSalle);
    }

    public List<Reservation> reservationsParUtilisateur(String email) {
        return reservationDao.findByUtilisateurEmail(email);
    }

    public List<Reservation> reservationsParSalle(String nomSalle) {
        return reservationDao.findBySalleNom(nomSalle);
    }
}