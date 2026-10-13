package com.example.jpa.dao;

import com.example.jpa.entities.Reservation;
import javax.persistence.EntityManager;
import java.util.List;

public class ReservationDao extends GenericDao<Reservation> {

    public ReservationDao(EntityManager em) {
        super(Reservation.class, em);
    }

    public List<Reservation> findByUtilisateurEmail(String email) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.utilisateur.email = :email", Reservation.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<Reservation> findBySalleNom(String nomSalle) {
        return em.createQuery("SELECT r FROM Reservation r WHERE r.salle.nom = :nomSalle", Reservation.class)
                .setParameter("nomSalle", nomSalle)
                .getResultList();
    }
}
