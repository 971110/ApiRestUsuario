package com.reto.usuarios.rest.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UsuarioTelefonoEntity")
public class UsuarioTelefonoEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Usuario_telefono;

	@Column(name = "Id_usuario")
	private int Id_usuario;
	
	@Column(name = "Id_telefono")
	private int Id_telefono;
	
	public UsuarioTelefonoEntity() {
		
	}

	public int getUsuario_telefono() {
		return Usuario_telefono;
	}

	public void setUsuario_telefono(int usuario_telefono) {
		Usuario_telefono = usuario_telefono;
	}

	public int getId_usuario() {
		return Id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		Id_usuario = id_usuario;
	}

	public int getId_telefono() {
		return Id_telefono;
	}

	public void setId_telefono(int id_telefono) {
		Id_telefono = id_telefono;
	}
}
