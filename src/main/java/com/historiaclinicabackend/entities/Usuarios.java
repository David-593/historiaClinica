
package com.historiaclinicabackend.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsuCedula", query = "SELECT u FROM Usuarios u WHERE u.usuCedula = :usuCedula"),
    @NamedQuery(name = "Usuarios.findByUsuNombres", query = "SELECT u FROM Usuarios u WHERE u.usuNombres = :usuNombres"),
    @NamedQuery(name = "Usuarios.findByUsuApellidos", query = "SELECT u FROM Usuarios u WHERE u.usuApellidos = :usuApellidos"),
    @NamedQuery(name = "Usuarios.findByUsuEmail", query = "SELECT u FROM Usuarios u WHERE u.usuEmail = :usuEmail"),
    @NamedQuery(name = "Usuarios.findByUsuNacimiento", query = "SELECT u FROM Usuarios u WHERE u.usuNacimiento = :usuNacimiento"),
    @NamedQuery(name = "Usuarios.findByUsuSexo", query = "SELECT u FROM Usuarios u WHERE u.usuSexo = :usuSexo"),
    @NamedQuery(name = "Usuarios.findByUsuClave", query = "SELECT u FROM Usuarios u WHERE u.usuClave = :usuClave"),
    @NamedQuery(name = "Usuarios.findByUsuRol", query = "SELECT u FROM Usuarios u WHERE u.usuRol = :usuRol"),
    @NamedQuery(name = "Usuarios.findByUsuEstado", query = "SELECT u FROM Usuarios u WHERE u.usuEstado = :usuEstado")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usu_cedula")
    private String usuCedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usu_nombres")
    private String usuNombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usu_apellidos")
    private String usuApellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "usu_email")
    private String usuEmail;
    @Column(name = "usu_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date usuNacimiento;
    @Size(max = 10)
    @Column(name = "usu_sexo")
    private String usuSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usu_clave")
    private String usuClave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "usu_rol")
    private String usuRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "usu_estado")
    private String usuEstado;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Medicos medicos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuarios")
    private Pacientes pacientes;
    @OneToMany(mappedBy = "histCedulaUsuario")
    private List<HistoriaClinica> historiaClinicaList;

    public Usuarios() {
    }

    public Usuarios(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public Usuarios(String usuCedula, String usuNombres, String usuApellidos, String usuEmail, String usuClave, String usuRol, String usuEstado) {
        this.usuCedula = usuCedula;
        this.usuNombres = usuNombres;
        this.usuApellidos = usuApellidos;
        this.usuEmail = usuEmail;
        this.usuClave = usuClave;
        this.usuRol = usuRol;
        this.usuEstado = usuEstado;
    }

    public String getUsuCedula() {
        return usuCedula;
    }

    public void setUsuCedula(String usuCedula) {
        this.usuCedula = usuCedula;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public Date getUsuNacimiento() {
        return usuNacimiento;
    }

    public void setUsuNacimiento(Date usuNacimiento) {
        this.usuNacimiento = usuNacimiento;
    }

    public String getUsuSexo() {
        return usuSexo;
    }

    public void setUsuSexo(String usuSexo) {
        this.usuSexo = usuSexo;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuRol() {
        return usuRol;
    }

    public void setUsuRol(String usuRol) {
        this.usuRol = usuRol;
    }

    public String getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(String usuEstado) {
        this.usuEstado = usuEstado;
    }

    public Medicos getMedicos() {
        return medicos;
    }

    public void setMedicos(Medicos medicos) {
        this.medicos = medicos;
    }

    public Pacientes getPacientes() {
        return pacientes;
    }

    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    public List<HistoriaClinica> getHistoriaClinicaList() {
        return historiaClinicaList;
    }

    public void setHistoriaClinicaList(List<HistoriaClinica> historiaClinicaList) {
        this.historiaClinicaList = historiaClinicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuCedula != null ? usuCedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuCedula == null && other.usuCedula != null) || (this.usuCedula != null && !this.usuCedula.equals(other.usuCedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaclinica.entities.Usuarios[ usuCedula=" + usuCedula + " ]";
    }

}
