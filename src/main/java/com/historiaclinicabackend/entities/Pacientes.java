
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
@Table(name = "pacientes")
@NamedQueries({
    @NamedQuery(name = "Pacientes.findAll", query = "SELECT p FROM Pacientes p"),
    @NamedQuery(name = "Pacientes.findByPacCedulaUsuario", query = "SELECT p FROM Pacientes p WHERE p.pacCedulaUsuario = :pacCedulaUsuario"),
    @NamedQuery(name = "Pacientes.findByPacTipoSangre", query = "SELECT p FROM Pacientes p WHERE p.pacTipoSangre = :pacTipoSangre"),
    @NamedQuery(name = "Pacientes.findByPacDireccion", query = "SELECT p FROM Pacientes p WHERE p.pacDireccion = :pacDireccion"),
    @NamedQuery(name = "Pacientes.findByPacTelefono", query = "SELECT p FROM Pacientes p WHERE p.pacTelefono = :pacTelefono"),
    @NamedQuery(name = "Pacientes.findByPacFechaRegistro", query = "SELECT p FROM Pacientes p WHERE p.pacFechaRegistro = :pacFechaRegistro"),
    @NamedQuery(name = "Pacientes.findByPacFechaNacimiento", query = "SELECT p FROM Pacientes p WHERE p.pacFechaNacimiento = :pacFechaNacimiento"),
    @NamedQuery(name = "Pacientes.findByPacSexo", query = "SELECT p FROM Pacientes p WHERE p.pacSexo = :pacSexo")})
public class Pacientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pac_cedula_usuario")
    private String pacCedulaUsuario;
    @Size(max = 5)
    @Column(name = "pac_tipo_sangre")
    private String pacTipoSangre;
    @Size(max = 2147483647)
    @Column(name = "pac_direccion")
    private String pacDireccion;
    @Size(max = 15)
    @Column(name = "pac_telefono")
    private String pacTelefono;
    @Column(name = "pac_fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date pacFechaRegistro;
    @Column(name = "pac_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date pacFechaNacimiento;
    @Size(max = 10)
    @Column(name = "pac_sexo")
    private String pacSexo;
    @OneToMany(mappedBy = "citaCedulaPaciente")
    private List<Citas> citasList;
    @JoinColumn(name = "pac_cedula_usuario", referencedColumnName = "usu_cedula", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuarios usuarios;

    public Pacientes() {
    }

    public Pacientes(String pacCedulaUsuario) {
        this.pacCedulaUsuario = pacCedulaUsuario;
    }

    public String getPacCedulaUsuario() {
        return pacCedulaUsuario;
    }

    public void setPacCedulaUsuario(String pacCedulaUsuario) {
        this.pacCedulaUsuario = pacCedulaUsuario;
    }

    public String getPacTipoSangre() {
        return pacTipoSangre;
    }

    public void setPacTipoSangre(String pacTipoSangre) {
        this.pacTipoSangre = pacTipoSangre;
    }

    public String getPacDireccion() {
        return pacDireccion;
    }

    public void setPacDireccion(String pacDireccion) {
        this.pacDireccion = pacDireccion;
    }

    public String getPacTelefono() {
        return pacTelefono;
    }

    public void setPacTelefono(String pacTelefono) {
        this.pacTelefono = pacTelefono;
    }

    public Date getPacFechaRegistro() {
        return pacFechaRegistro;
    }

    public void setPacFechaRegistro(Date pacFechaRegistro) {
        this.pacFechaRegistro = pacFechaRegistro;
    }

    public Date getPacFechaNacimiento() {
        return pacFechaNacimiento;
    }

    public void setPacFechaNacimiento(Date pacFechaNacimiento) {
        this.pacFechaNacimiento = pacFechaNacimiento;
    }

    public String getPacSexo() {
        return pacSexo;
    }

    public void setPacSexo(String pacSexo) {
        this.pacSexo = pacSexo;
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
        hash += (pacCedulaUsuario != null ? pacCedulaUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacientes)) {
            return false;
        }
        Pacientes other = (Pacientes) object;
        if ((this.pacCedulaUsuario == null && other.pacCedulaUsuario != null) || (this.pacCedulaUsuario != null && !this.pacCedulaUsuario.equals(other.pacCedulaUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.historiaclinica.entities.Pacientes[ pacCedulaUsuario=" + pacCedulaUsuario + " ]";
    }
    
}
