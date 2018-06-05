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
@Table(name = "predmetne_obaveze")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PredmetneObaveze.findAll", query = "SELECT p FROM PredmetneObaveze p")
    , @NamedQuery(name = "PredmetneObaveze.findBySifraPredmeta", query = "SELECT p FROM PredmetneObaveze p WHERE p.sifraPredmeta = :sifraPredmeta")
    , @NamedQuery(name = "PredmetneObaveze.findByNazivPredmeta", query = "SELECT p FROM PredmetneObaveze p WHERE p.nazivPredmeta = :nazivPredmeta")
    , @NamedQuery(name = "PredmetneObaveze.findByPredavac", query = "SELECT p FROM PredmetneObaveze p WHERE p.predavac = :predavac")
    , @NamedQuery(name = "PredmetneObaveze.findByBrojPoenaZadaci", query = "SELECT p FROM PredmetneObaveze p WHERE p.brojPoenaZadaci = :brojPoenaZadaci")
    , @NamedQuery(name = "PredmetneObaveze.findByBrojPoenaTestovi", query = "SELECT p FROM PredmetneObaveze p WHERE p.brojPoenaTestovi = :brojPoenaTestovi")
    , @NamedQuery(name = "PredmetneObaveze.findByBrojPoenaProjekat", query = "SELECT p FROM PredmetneObaveze p WHERE p.brojPoenaProjekat = :brojPoenaProjekat")
    , @NamedQuery(name = "PredmetneObaveze.findByPrisustvo", query = "SELECT p FROM PredmetneObaveze p WHERE p.prisustvo = :prisustvo")
    , @NamedQuery(name = "PredmetneObaveze.findByOcena", query = "SELECT p FROM PredmetneObaveze p WHERE p.ocena = :ocena")})
public class PredmetneObaveze implements Serializable {

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
    @Column(name = "broj_poena_zadaci")
    private int brojPoenaZadaci;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_poena_testovi")
    private int brojPoenaTestovi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "broj_poena_projekat")
    private int brojPoenaProjekat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prisustvo")
    private int prisustvo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ocena")
    private int ocena;

    public PredmetneObaveze() {
    }

    public PredmetneObaveze(Integer sifraPredmeta) {
        this.sifraPredmeta = sifraPredmeta;
    }

    public PredmetneObaveze(Integer sifraPredmeta, String nazivPredmeta, String predavac, int brojPoenaZadaci, int brojPoenaTestovi, int brojPoenaProjekat, int prisustvo, int ocena) {
        this.sifraPredmeta = sifraPredmeta;
        this.nazivPredmeta = nazivPredmeta;
        this.predavac = predavac;
        this.brojPoenaZadaci = brojPoenaZadaci;
        this.brojPoenaTestovi = brojPoenaTestovi;
        this.brojPoenaProjekat = brojPoenaProjekat;
        this.prisustvo = prisustvo;
        this.ocena = ocena;
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

    public int getBrojPoenaZadaci() {
        return brojPoenaZadaci;
    }

    public void setBrojPoenaZadaci(int brojPoenaZadaci) {
        this.brojPoenaZadaci = brojPoenaZadaci;
    }

    public int getBrojPoenaTestovi() {
        return brojPoenaTestovi;
    }

    public void setBrojPoenaTestovi(int brojPoenaTestovi) {
        this.brojPoenaTestovi = brojPoenaTestovi;
    }

    public int getBrojPoenaProjekat() {
        return brojPoenaProjekat;
    }

    public void setBrojPoenaProjekat(int brojPoenaProjekat) {
        this.brojPoenaProjekat = brojPoenaProjekat;
    }

    public int getPrisustvo() {
        return prisustvo;
    }

    public void setPrisustvo(int prisustvo) {
        this.prisustvo = prisustvo;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
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
        if (!(object instanceof PredmetneObaveze)) {
            return false;
        }
        PredmetneObaveze other = (PredmetneObaveze) object;
        if ((this.sifraPredmeta == null && other.sifraPredmeta != null) || (this.sifraPredmeta != null && !this.sifraPredmeta.equals(other.sifraPredmeta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.metropolitan.isum.entiteti.PredmetneObaveze[ sifraPredmeta=" + sifraPredmeta + " ]";
    }
    
}
