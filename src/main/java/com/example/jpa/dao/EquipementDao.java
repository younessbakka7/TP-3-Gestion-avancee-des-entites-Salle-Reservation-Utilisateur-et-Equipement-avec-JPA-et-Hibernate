package com.example.jpa.dao;

import com.example.jpa.entities.Equipement;
import javax.persistence.EntityManager;
import java.util.List;

public class EquipementDao extends GenericDao<Equipement> {

    public EquipementDao(EntityManager em) {
        super(Equipement.class, em);
    }

    public List<Equipement> findByNom(String nom) {
        return em.createQuery("SELECT e FROM Equipement e WHERE e.nom LIKE :nom", Equipement.class)
                .setParameter("nom", "%" + nom + "%")
                .getResultList();
    }
}
