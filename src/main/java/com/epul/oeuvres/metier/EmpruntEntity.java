package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="emprunt", schema = "baseoeuvre")
public class EmpruntEntity {

    private Integer idEmprunt;
    private Date datePret;
    private Date dateRetour;
    private OeuvrepretEntity oeuvrepret;
    private AdherentEntity adherentPret;

    @Id
    @Column(name = "id_emprunt", nullable = false)
    public Integer getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(Integer idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    @Basic
    @Column(name = "date_pret", nullable = false)
    public Date getDatePret() {
        return datePret;
    }

    public void setDatePret(Date datePret) {
        this.datePret = datePret;
    }

    @Basic
    @Column(name = "date_retour", nullable = false)
    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrepret", referencedColumnName = "id_oeuvrepret", nullable = false)
    public OeuvrepretEntity getOeuvrepret() {
        return oeuvrepret;
    }

    public void setOeuvrepret(OeuvrepretEntity oeuvrepret) {
        this.oeuvrepret = oeuvrepret;
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherentPret() {
        return adherentPret;
    }

    public void setAdherentPret(AdherentEntity adherentPret) {
        this.adherentPret = adherentPret;
    }
}
