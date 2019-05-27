package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvrepretEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class OeuvrepretService extends EntityService {


    public void insertOeuvrepret(OeuvrepretEntity uneOp) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneOp);
            transac.commit();
            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<OeuvrepretEntity> consulterListeOeuvrepret() throws MonException {
        List<OeuvrepretEntity> mesOeuvreprets = null;
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvreprets = (List<OeuvrepretEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM OeuvrepretEntity a " +
                                    "ORDER BY a.titreOeuvrepret").getResultList();
            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvreprets;
    }


    public OeuvrepretEntity oeuvrepretById(int numero) throws MonException {
        List<OeuvrepretEntity> oeuvreprets = null;
        OeuvrepretEntity oeuvrepret = new OeuvrepretEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvreprets = (List<OeuvrepretEntity>) entitymanager.createQuery("SELECT a FROM OeuvrepretEntity a WHERE a.idOeuvrepret=" + numero).getResultList();
            oeuvrepret = oeuvreprets.get(0);

            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrepret;
    }

    public void updateOeuvrepret(OeuvrepretEntity uneOp) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(uneOp);
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public void supprimerOeuvrepret(OeuvrepretEntity uneOp) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(OeuvrepretEntity.class, uneOp.getIdOeuvrepret()));
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
