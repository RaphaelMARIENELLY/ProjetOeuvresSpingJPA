package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.ProprietaireEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class ProprietaireService extends EntityService{

    /* Lister les oeuvres
     * */
    public List<ProprietaireEntity> consulterListeProprietaire() throws MonException {
        List<ProprietaireEntity> mesProprietaires = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesProprietaires = (List<ProprietaireEntity>)
                    entitymanager.createQuery(
                            "SELECT p FROM ProprietaireEntity p " +
                                    "ORDER BY p.nomProprietaire").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesProprietaires;
    }


    /* Consultation d'un propriétaire par son numéro
     */
    public ProprietaireEntity proprietaireById(int numero) throws MonException {
        List<ProprietaireEntity> proprietaires = null;
        ProprietaireEntity proprietaire = new ProprietaireEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            proprietaires = (List<ProprietaireEntity>)entitymanager.createQuery("SELECT p FROM ProprietaireEntity p WHERE a.idProprietaire="+numero).getResultList();
            proprietaire = proprietaires.get(0);

            entitymanager.close();
        }catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proprietaire;
    }
}
