package com.reto.usuarios.rest.dao.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UsuarioEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id_usuario;
	
	@Column(name = "Nombre")
	private String Nombre;
	
	@Column(name = "Correo")
	private String Correo;
	
	@Column(name = "Clave")
	private String Clave;
	
	@Column(name = "FechaCreacion")
	private String fechaCreacion;
	
	@Column(name = "FechaLogin")
	private String fechaLogin;
	
	@Column(name = "FechaModifi")
	private String fechaModifi;
	
	@Column(name = "Estado")
	private boolean estado;
	
	public UsuarioEntity() {
		
	}

	public int getId_usuario() {
		return Id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		Id_usuario = id_usuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getCorreo() {
		return Correo;
	}

	public void setCorreo(String correo) {
		Correo = correo;
	}

	public String getClave() {
		return Clave;
	}

	public void setClave(String clave) {
		Clave = clave;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFechaLogin() {
		return fechaLogin;
	}

	public void setFechaLogin(String fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public String getFechaModifi() {
		return fechaModifi;
	}

	public void setFechaModifi(String fechaModifi) {
		this.fechaModifi = fechaModifi;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
