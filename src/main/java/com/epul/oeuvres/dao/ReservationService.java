package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ReservationEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class ReservationService extends EntityService {
    /* Insertion d'une reservation
     * param ReservationEntity uneRes
     * */

    public void insertReservation(ReservationEntity uneRes) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneRes);
            transac.commit();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /* Lister les reservations
     * */
    public List<ReservationEntity> consulterListeOeuvrevente() throws MonException {
        List<ReservationEntity> mesReservations = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesReservations = (List<ReservationEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationEntity r " +
                                    "ORDER BY r.idAdherent").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesReservations;
    }

    /* Consultation d'une reservation par son numéro
     */
    public ReservationEntity reservationById(int numero) throws MonException {
        List<ReservationEntity> reservations = null;
        ReservationEntity reservation = new ReservationEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservations = (List<ReservationEntity>)entitymanager.createQuery("SELECT r FROM ReservationEntity r WHERE r.idOeuvrevente="+numero).getResultList();
            reservation = reservations.get(0);

            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservation;
    }


    public void updateReservation(ReservationEntity uneReservation) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(uneReservation);
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public void supprimerReervation(ReservationEntity uneReservation) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(ReservationEntity.class, uneReservation.getIdOeuvrevente()));
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }
}
