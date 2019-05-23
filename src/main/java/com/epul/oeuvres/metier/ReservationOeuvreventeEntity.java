package com.epul.oeuvres.metier;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation_oeuvrevente", schema = "baseoeuvre")
public class ReservationOeuvreventeEntity {
    private Integer idReservationOeuvrevente;
    private Date dateReservation;
    private String statut;
    private OeuvreventeEntity oeuvrevente;
    private AdherentEntity adherent;

    @Id
    @Column(name = "id_reservation_oeuvrevente", nullable = false)
    public Integer getIdReservationOeuvrevente() {
        return idReservationOeuvrevente;
    }

    public void setIdReservationOeuvrevente(Integer idReservationOeuvrevente) {
        this.idReservationOeuvrevente = idReservationOeuvrevente;
    }

    @Basic
    @Column(name = "date_reservation", nullable = false)
    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    @Basic
    @Column(name = "statut", nullable = false, length = 20)
    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationOeuvreventeEntity that = (ReservationOeuvreventeEntity) o;
        return Objects.equals(idReservationOeuvrevente, that.idReservationOeuvrevente) &&
                Objects.equals(dateReservation, that.dateReservation) &&
                Objects.equals(statut, that.statut);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReservationOeuvrevente, dateReservation, statut);
    }

    @ManyToOne
    @JoinColumn(name = "id_adherent", referencedColumnName = "id_adherent", nullable = false)
    public AdherentEntity getAdherent() {
        return adherent;
    }

    public void setAdherent(AdherentEntity adherent) {
        this.adherent = adherent;
    }

    @ManyToOne
    @JoinColumn(name = "id_oeuvrevente", referencedColumnName = "id_oeuvrevente", nullable = false)
    public OeuvreventeEntity getOeuvrevente() {
        return oeuvrevente;
    }

    public void setOeuvrevente(OeuvreventeEntity oeuvrevente) {
        this.oeuvrevente = oeuvrevente;
    }
}
