package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ReservationOeuvreventeEntity;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ReservationOeuvreventeService extends EntityService {
    /* Insertion d'une reservation
     * param ReservationEntity uneRes
     * */


    public void insertReservation(ReservationOeuvreventeEntity uneRes) throws MonException {
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
    public List<ReservationOeuvreventeEntity> consulterListeReservation() throws MonException {
        List<ReservationOeuvreventeEntity> mesReservations = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesReservations = (List<ReservationOeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationOeuvreventeEntity r ").getResultList();
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

    /* Renvoie l'id de la prochaine reservation
     * */
    public int nextIdReservation() throws MonException {
        List<ReservationOeuvreventeEntity> mesReservations = null;
        int nextId = 0;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesReservations = (List<ReservationOeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT r FROM ReservationOeuvreventeEntity r ").getResultList();
            entitymanager.close();

            for (ReservationOeuvreventeEntity res : mesReservations) {
                if (res.getIdReservationOeuvrevente() > nextId){
                    nextId = res.getIdReservationOeuvrevente();
                }
            }

        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nextId+1;
    }

    /* Consultation d'une reservation par son numéro
     */
    public ReservationOeuvreventeEntity reservationById(int numero) throws MonException {
        List<ReservationOeuvreventeEntity> reservations = null;
        ReservationOeuvreventeEntity reservation = new ReservationOeuvreventeEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservations = (List<ReservationOeuvreventeEntity>)entitymanager.createQuery("SELECT r FROM ReservationOeuvreventeEntity r WHERE r.idReservationOeuvrevente="+numero).getResultList();
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


    public void updateReservation(ReservationOeuvreventeEntity uneReservation) throws MonException {
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

    public void supprimerReservation(ReservationOeuvreventeEntity uneReservation) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(ReservationOeuvreventeEntity.class, uneReservation.getIdReservationOeuvrevente()));
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    /* Consultation des reservations par son numéro d oeuvrevente
     */
    public List<ReservationOeuvreventeEntity> reservationByOeuvreventeId(int numero) throws MonException {
        // to commit

        List<ReservationOeuvreventeEntity> reservations = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservations = (List<ReservationOeuvreventeEntity>)entitymanager.createQuery("SELECT r FROM ReservationOeuvreventeEntity r WHERE r.oeuvrevente.idOeuvrevente="+numero).getResultList();

            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }


    /* Consultation des reservations par son numéro d adherent
     */
    public List<ReservationOeuvreventeEntity> reservationByAdherentId(int numero) throws MonException {
        // to commit

        List<ReservationOeuvreventeEntity> reservations = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            reservations = (List<ReservationOeuvreventeEntity>)entitymanager.createQuery("SELECT r FROM ReservationOeuvreventeEntity r WHERE r.adherent.idAdherent="+numero).getResultList();

            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
