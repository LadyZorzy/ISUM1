/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metropolitan.isum.entiteti;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Lenovo
 */
@Entity
@Table(name = "predmet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Predmet.findAll", query = "SELECT p FROM Predmet p")
    , @NamedQuery(name = "Predmet.findBySifraPredmeta", query = "SELECT p FROM Predmet p WHERE p.sifraPredmeta = :sifraPredmeta")
    , @NamedQuery(name = "Predmet.findByNazivPredmeta", query = "SELECT p FROM Predmet p WHERE p.nazivPredmeta = :nazivPredmeta")
    , @NamedQuery(name = "Predmet.findByPredavac", query = "SELECT p FROM Predmet p WHERE p.predavac = :predavac")
    , @NamedQuery(name = "Predmet.findByEspb", query = "SELECT p FROM Predmet p WHERE p.espb = :espb")})
public class Predmet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "sifra_predmeta")
    private Integer sifraPredmeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "naziv_predmeta")
    private String nazivPredmeta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "predavac")
    private String predavac;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESPB")
    private int espb;

    public Predmet() {
    }

    public Predmet(Integer sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public Predmet(Integer sifraPredmeta, String nazivPredmeta, String predavac, int espb) {
        this.sifraPredmeta = sifraPredmeta;
        this.nazivPredmeta = nazivPredmeta;
        this.predavac = predavac;
        this.espb = espb;
    }

    public Integer getSifraPredmeta() {
        return sifraPredmeta;
    }

    public void setSifraPredmeta(Integer sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getPredavac() {
        return predavac;
    }

    public void setPredavac(String predavac) {
        this.predavac = predavac;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(int espb) {
        this.espb = espb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraPredmeta != null ? sifraPredmeta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predmet)) {
            return false;
        }
        Predmet other = (Predmet) object;
        if ((this.sifraPredmeta == null && other.sifraPredmeta != null) || (this.sifraPredmeta != null && !this.sifraPredmeta.equals(other.sifraPredmeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metropolitan.isum.entiteti.Predmet[ sifraPredmeta=" + sifraPredmeta + " ]";
    }
    
}
