/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.historiaclinicabackend.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "medicos")
@NamedQueries({
    @NamedQuery(name = "Medicos.findAll", query = "SELECT m FROM Medicos m"),
    @NamedQuery(name = "Medicos.findByMedCedulaUsuario", query = "SELECT m FROM Medicos m WHERE m.medCedulaUsuario = :medCedulaUsuario"),
    @NamedQuery(name = "Medicos.findByMedEspecialidad", query = "SELECT m FROM Medicos m WHERE m.medEspecialidad = :medEspecialidad"),
    @NamedQuery(name = "Medicos.findByMedTelefono", query = "SELECT m FROM Medicos m WHERE m.medTelefono = :medTelefono")})
public class Medicos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "med_cedula_usuario")
    private String medCedulaUsuario;
    @Size(max = 100)
    @Column(name = "med_especialidad")
    private String medEspecialidad;
    @Size(max = 15)
    @Column(name = "med_telefono")
    private String medTelefono;
    @OneToMany(mappedBy = "citaCedulaMedico")
    private List<Citas> citasList;
    @JoinColumn(name = "med_cedula_usuario", referencedColumnName = "usu_cedula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuarios usuarios;

    public Medicos() {
    }

    public Medicos(String medCedulaUsuario) {
        this.medCedulaUsuario = medCedulaUsuario;
    }

    public String getMedCedulaUsuario() {
        return medCedulaUsuario;
    }

    public void setMedCedulaUsuario(String medCedulaUsuario) {
        this.medCedulaUsuario = medCedulaUsuario;
    }

    public String getMedEspecialidad() {
        return medEspecialidad;
    }

    public void setMedEspecialidad(String medEspecialidad) {
        this.medEspecialidad = medEspecialidad;
    }

    public String getMedTelefono() {
        return medTelefono;
    }

    public void setMedTelefono(String medTelefono) {
        this.medTelefono = medTelefono;
    }

    public List<Citas> getCitasList() {
        return citasList;
    }

    public void setCitasList(List<Citas> citasList) {
        this.citasList = citasList;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medCedulaUsuario != null ? medCedulaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicos)) {
            return false;
        }
        Medicos other = (Medicos) object;
        if ((this.medCedulaUsuario == null && other.medCedulaUsuario != null) || (this.medCedulaUsuario != null && !this.medCedulaUsuario.equals(other.medCedulaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaclinica.entities.Medicos[ medCedulaUsuario=" + medCedulaUsuario + " ]";
    }
    
}
