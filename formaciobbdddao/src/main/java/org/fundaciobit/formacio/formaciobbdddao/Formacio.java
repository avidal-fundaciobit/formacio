package org.fundaciobit.formacio.formaciobbdddao;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author avidal
 */
public class Formacio {
    private Long formacioBbddId;
    private String nom;
    private int edat;
    private Boolean genere;
    private Timestamp data;

    // Constructor
    public Formacio(Long formacioBbddId, String nom, int edat, Boolean genere, Timestamp data) {
        this.formacioBbddId = formacioBbddId;
        this.nom = nom;
        this.edat = edat;
        this.genere = genere;
        this.data = data;
    }
    
    public Formacio() {}

    // Getters y setters
    public Boolean getGenere() {
        return genere;
    }

    public void setGenere(Boolean genere) {
        this.genere = genere;
    }


    public Long getFormacioBbddId() {
        return formacioBbddId;
    }

    public void setFormacioBbddId(Long formacioBbddId) {
        this.formacioBbddId = formacioBbddId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Formacio formacio = (Formacio) obj;
        return Objects.equals(formacioBbddId, formacio.formacioBbddId) &&
                Objects.equals(nom, formacio.nom) &&
                edat == formacio.edat &&
                Objects.equals(genere, formacio.genere) &&
                Objects.equals(data, formacio.data);
    }

}
