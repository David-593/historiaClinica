
package com.historiaclinicabackend.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "historia_clinica")
@NamedQueries({
    @NamedQuery(name = "HistoriaClinica.findAll", query = "SELECT h FROM HistoriaClinica h"),
    @NamedQuery(name = "HistoriaClinica.findByHistId", query = "SELECT h FROM HistoriaClinica h WHERE h.histId = :histId"),
    @NamedQuery(name = "HistoriaClinica.findByHistFecha", query = "SELECT h FROM HistoriaClinica h WHERE h.histFecha = :histFecha"),
    @NamedQuery(name = "HistoriaClinica.findByHistDiagnostico", query = "SELECT h FROM HistoriaClinica h WHERE h.histDiagnostico = :histDiagnostico"),
    @NamedQuery(name = "HistoriaClinica.findByHistTratamiento", query = "SELECT h FROM HistoriaClinica h WHERE h.histTratamiento = :histTratamiento"),
    @NamedQuery(name = "HistoriaClinica.findByHistReceta", query = "SELECT h FROM HistoriaClinica h WHERE h.histReceta = :histReceta"),
    @NamedQuery(name = "HistoriaClinica.findByHistObservaciones", query = "SELECT h FROM HistoriaClinica h WHERE h.histObservaciones = :histObservaciones")})
public class HistoriaClinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hist_id")
    private Integer histId;
    @Column(name = "hist_fecha")
    @Temporal(TemporalType.DATE)
    private Date histFecha;
    @Size(max = 2147483647)
    @Column(name = "hist_diagnostico")
    private String histDiagnostico;
    @Size(max = 2147483647)
    @Column(name = "hist_tratamiento")
    private String histTratamiento;
    @Size(max = 2147483647)
    @Column(name = "hist_receta")
    private String histReceta;
    @Size(max = 2147483647)
    @Column(name = "hist_observaciones")
    private String histObservaciones;
    @JoinColumn(name = "hist_cedula_usuario", referencedColumnName = "usu_cedula")
    @ManyToOne
    private Usuarios histCedulaUsuario;

    public HistoriaClinica() {
    }

    public HistoriaClinica(Integer histId) {
        this.histId = histId;
    }

    public Integer getHistId() {
        return histId;
    }

    public void setHistId(Integer histId) {
        this.histId = histId;
    }

    public Date getHistFecha() {
        return histFecha;
    }

    public void setHistFecha(Date histFecha) {
        this.histFecha = histFecha;
    }

    public String getHistDiagnostico() {
        return histDiagnostico;
    }

    public void setHistDiagnostico(String histDiagnostico) {
        this.histDiagnostico = histDiagnostico;
    }

    public String getHistTratamiento() {
        return histTratamiento;
    }

    public void setHistTratamiento(String histTratamiento) {
        this.histTratamiento = histTratamiento;
    }

    public String getHistReceta() {
        return histReceta;
    }

    public void setHistReceta(String histReceta) {
        this.histReceta = histReceta;
    }

    public String getHistObservaciones() {
        return histObservaciones;
    }

    public void setHistObservaciones(String histObservaciones) {
        this.histObservaciones = histObservaciones;
    }

    public Usuarios getHistCedulaUsuario() {
        return histCedulaUsuario;
    }

    public void setHistCedulaUsuario(Usuarios histCedulaUsuario) {
        this.histCedulaUsuario = histCedulaUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histId != null ? histId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistoriaClinica)) {
            return false;
        }
        HistoriaClinica other = (HistoriaClinica) object;
        if ((this.histId == null && other.histId != null) || (this.histId != null && !this.histId.equals(other.histId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaclinica.entities.HistoriaClinica[ histId=" + histId + " ]";
    }
    
}
