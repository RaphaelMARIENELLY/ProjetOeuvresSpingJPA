package com.epul.oeuvres.dao;

import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.EmpruntEntity;

import javax.persistence.EntityTransaction;
import java.util.List;

public class EmpruntService extends EntityService {
    /* Insertion d'un pret
     * param EmpruntEntity unEmp
     * */

    public void insertEmprunt(EmpruntEntity unEmp) throws MonException {
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.persist(unEmp);
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

    public int nextIdEmprunt() throws MonException {
        List<EmpruntEntity> mesEmprunts = null;
        int nextId = 0;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesEmprunts = (List<EmpruntEntity>)
                    entitymanager.createQuery(
                            "SELECT e FROM EmpruntEntity e ").getResultList();
            entitymanager.close();

            for (EmpruntEntity emps : mesEmprunts) {
                if (emps.getIdEmprunt() > nextId){
                    nextId = emps.getIdEmprunt();
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

    public List<EmpruntEntity> consulterListeEmprunts() throws MonException {
        List<EmpruntEntity> mesPrets = null;
        try
        {
            EntityTransaction transac = startTransaction();
            transac.begin();
            mesPrets = (List<EmpruntEntity>)
                    entitymanager.createQuery(
                            "SELECT e FROM EmpruntEntity e ").getResultList();
            entitymanager.close();
        }
        catch (RuntimeException e)
        {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mesPrets;
    }

    public EmpruntEntity empruntByPretId(int numero) throws MonException {
        List<EmpruntEntity> emprunts = null;
        EmpruntEntity emprunt = new EmpruntEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            emprunts = (List<EmpruntEntity>) entitymanager.createQuery("SELECT a FROM EmpruntEntity a WHERE a.oeuvrepret.idOeuvrepret=" + numero).getResultList();
            emprunt = emprunts.get(0);

            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprunt;
    }

    public void updateEmprunt(EmpruntEntity emp) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.merge(emp);
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        } catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public void supprimerEmprunt(EmpruntEntity emp) throws MonException {
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();
            entitymanager.remove(entitymanager.find(EmpruntEntity.class, emp.getIdEmprunt()));
            transac.commit();
            entitymanager.close();
        } catch (MonException e) {
            throw e;
        }
        catch (Exception exc) {
            throw new MonException(exc.getMessage(), "systeme");
        }
    }

    public List<EmpruntEntity> empruntsByAdherentId(int numero) throws MonException {
        List<EmpruntEntity> emprunts = null;
        EmpruntEntity emprunt = new EmpruntEntity();
        try {
            EntityTransaction transac = startTransaction();
            transac.begin();

            emprunts = (List<EmpruntEntity>) entitymanager.createQuery("SELECT a FROM EmpruntEntity a WHERE a.adherentPret.idAdherent=" + numero).getResultList();

            entitymanager.close();
        } catch (RuntimeException e) {
            new MonException("Erreur de lecture", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emprunts;
    }
}
