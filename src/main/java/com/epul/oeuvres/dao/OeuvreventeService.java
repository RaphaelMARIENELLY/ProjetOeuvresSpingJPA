package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class OeuvreventeService extends EntityService {
    /* Insertion d'une oeuvre
     * param OeuvreventeEntity uneOv
     * */

    public void insertOeuvrevente(OeuvreventeEntity uneOv) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(uneOv);
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



    /* Lister les oeuvres
     * */
    public List<OeuvreventeEntity> consulterListeOeuvrevente() throws MonException {
        List<OeuvreventeEntity> mesOeuvreventes = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesOeuvreventes = (List<OeuvreventeEntity>)
                    entitymanager.createQuery(
                            "SELECT a FROM OeuvreventeEntity a " +
                                    "ORDER BY a.titreOeuvrevente").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesOeuvreventes;
    }

    /* Consultation d'une oeuvre par son numéro
     */
    public OeuvreventeEntity oeuvreventeById(int numero) throws MonException {
        List<OeuvreventeEntity> oeuvreventes = null;
        OeuvreventeEntity oeuvrevente = new OeuvreventeEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            oeuvreventes = (List<OeuvreventeEntity>)entitymanager.createQuery("SELECT a FROM OeuvreventeEntity a WHERE a.idOeuvrevente="+numero).getResultList();
            oeuvrevente = oeuvreventes.get(0);

            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oeuvrevente;
    }


    public void updateOeuvrevente(OeuvreventeEntity uneOv) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(uneOv);
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public void supprimerOeuvrevente(OeuvreventeEntity uneOv) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(OeuvreventeEntity.class, uneOv.getIdOeuvrevente()));
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
