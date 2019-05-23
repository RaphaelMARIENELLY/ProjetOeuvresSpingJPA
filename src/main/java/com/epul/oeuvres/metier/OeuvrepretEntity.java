package com.epul.oeuvres.metier;

import javax.persistence.*;

/**
 * Created by christian on 19/02/2017.
 */
@Entity
@Table(name = "oeuvrepret", schema = "baseoeuvre", catalog = "")
public class OeuvrepretEntity {
    private int idOeuvrepret;
    private String titreOeuvrepret;
    private ProprietaireEntity proprietaireByIdProprietaire;

    public void setIdOeuvrepret(Integer idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    @Id
    @Column(name = "id_oeuvrepret", nullable = false)
    public int getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    @Basic
    @Column(name = "titre_oeuvrepret", nullable = false, length = 200)
    public String getTitreOeuvrepret() {
        return titreOeuvrepret;
    }

    public void setTitreOeuvrepret(String titreOeuvrepret) {
        this.titreOeuvrepret = titreOeuvrepret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OeuvrepretEntity that = (OeuvrepretEntity) o;

        if (idOeuvrepret != that.idOeuvrepret) return false;
        if (titreOeuvrepret != null ? !titreOeuvrepret.equals(that.titreOeuvrepret) : that.titreOeuvrepret != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOeuvrepret;
        result = 31 * result + (titreOeuvrepret != null ? titreOeuvrepret.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_proprietaire", referencedColumnName = "id_proprietaire")
    public ProprietaireEntity getProprietaireByIdProprietaire() {
        return proprietaireByIdProprietaire;
    }

    public void setProprietaireByIdProprietaire(ProprietaireEntity proprietaireByIdProprietaire) {
        this.proprietaireByIdProprietaire = proprietaireByIdProprietaire;
    }
}
