
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
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "citas")
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c"),
    @NamedQuery(name = "Citas.findByCitaId", query = "SELECT c FROM Citas c WHERE c.citaId = :citaId"),
    @NamedQuery(name = "Citas.findByCitaFecha", query = "SELECT c FROM Citas c WHERE c.citaFecha = :citaFecha"),
    @NamedQuery(name = "Citas.findByCitaHora", query = "SELECT c FROM Citas c WHERE c.citaHora = :citaHora"),
    @NamedQuery(name = "Citas.findByCitaEstado", query = "SELECT c FROM Citas c WHERE c.citaEstado = :citaEstado")})
public class Citas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cita_id")
    private Integer citaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cita_fecha")
    @Temporal(TemporalType.DATE)
    private Date citaFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cita_hora")
    @Temporal(TemporalType.TIME)
    private LocalTime citaHora;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cita_estado")
    private String citaEstado;
    @JoinColumn(name = "cita_cedula_medico", referencedColumnName = "med_cedula_usuario")
    @ManyToOne
    private Medicos citaCedulaMedico;
    @JoinColumn(name = "cita_cedula_paciente", referencedColumnName = "pac_cedula_usuario")
    @ManyToOne
    private Pacientes citaCedulaPaciente;

    public Citas() {
    }

    public Citas(Integer citaId) {
        this.citaId = citaId;
    }

    public Citas(Integer citaId, Date citaFecha, LocalTime citaHora, String citaEstado) {
        this.citaId = citaId;
        this.citaFecha = citaFecha;
        this.citaHora = citaHora;
        this.citaEstado = citaEstado;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public Date getCitaFecha() {
        return citaFecha;
    }

    public void setCitaFecha(Date citaFecha) {
        this.citaFecha = citaFecha;
    }

    public LocalTime getCitaHora() {
        return citaHora;
    }

    public void setCitaHora(LocalTime citaHora) {
        this.citaHora = citaHora;
    }

    public String getCitaEstado() {
        return citaEstado;
    }

    public void setCitaEstado(String citaEstado) {
        this.citaEstado = citaEstado;
    }

    public Medicos getCitaCedulaMedico() {
        return citaCedulaMedico;
    }

    public void setCitaCedulaMedico(Medicos citaCedulaMedico) {
        this.citaCedulaMedico = citaCedulaMedico;
    }

    public Pacientes getCitaCedulaPaciente() {
        return citaCedulaPaciente;
    }

    public void setCitaCedulaPaciente(Pacientes citaCedulaPaciente) {
        this.citaCedulaPaciente = citaCedulaPaciente;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citaId != null ? citaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.citaId == null && other.citaId != null) || (this.citaId != null && !this.citaId.equals(other.citaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaclinica.entities.Citas[ citaId=" + citaId + " ]";
    }
    
}
