package com.epul.oeuvres.metier;

import java.io.Serializable;
import java.util.Objects;

public class EmpruntEntityPK implements Serializable {

    private int idOeuvrepret;
    private int idAdherent;

    public int getIdOeuvrepret() {
        return idOeuvrepret;
    }

    public void setIdOeuvrepret(int idOeuvrepret) {
        this.idOeuvrepret = idOeuvrepret;
    }

    public int getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(int idAdherent) {
        this.idAdherent = idAdherent;
    }

    private EmpruntEntityPK() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmpruntEntityPK that = (EmpruntEntityPK) o;
        return idOeuvrepret == that.idOeuvrepret &&
                idAdherent == that.idAdherent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOeuvrepret, idAdherent);
    }
}
