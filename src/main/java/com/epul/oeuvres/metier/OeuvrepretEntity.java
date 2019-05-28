package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "oeuvrepret", schema = "baseoeuvre", catalog = "")
public class OeuvrepretEntity {

    private int idOeuvrepret;
    private String titreOeuvrepret;
    private String etatOeuvrepret;
    private ProprietaireEntity proprietaire;
    //private AdherentEntity adherent;

    @Id
    @Column(name = "id_oeuvrepret", nullable = false)
    public int getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    @Basic
    @Column(name = "titre_oeuvrepret", nullable = false)
    public String getTitreOeuvrepret() {
        return titreOeuvrepret;
    }

    public void setTitreOeuvrepret(String titreOeuvrepret) {
        this.titreOeuvrepret = titreOeuvrepret;
    }

    @Basic
    @Column(name = "etat_oeuvrepret", nullable = false)
    public String getEtatOeuvrepret() {
        return etatOeuvrepret;
    }

    public void setEtatOeuvrepret(String etatOeuvrepret) {
        this.etatOeuvrepret = etatOeuvrepret;
    }

    @ManyToOne
    @JoinColumn(name = "id_proprietaire", referencedColumnName = "id_proprietaire", nullable = false)
    public ProprietaireEntity getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(ProprietaireEntity proprietaire) {
        this.proprietaire = proprietaire;
    }

    /*@ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OeuvrepretEntity that = (OeuvrepretEntity) o;
        return idOeuvrepret == that.idOeuvrepret &&
                Objects.equals(titreOeuvrepret, that.titreOeuvrepret) &&
                Objects.equals(etatOeuvrepret, that.etatOeuvrepret) &&
                Objects.equals(proprietaire, that.proprietaire);
                //Objects.equals(adherent, that.adherent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOeuvrepret, titreOeuvrepret, etatOeuvrepret, proprietaire);
    }
}
