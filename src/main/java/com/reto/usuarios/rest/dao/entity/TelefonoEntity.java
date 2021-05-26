package com.reto.usuarios.rest.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Telefono")
public class TelefonoEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id_telefono;
	
	@Column(name = "Numero")
	private String Numero;
	
	@Column(name = "CodigoCiudad")
	private String CodigoCiudad;
	
	@Column(name = "CodigoPais")
	private String CodigoPais;
	
	public TelefonoEntity() {
		
	}

	public int getId_telefono() {
		return Id_telefono;
	}

	public void setId_telefono(int id_telefono) {
		Id_telefono = id_telefono;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getCodigoCiudad() {
		return CodigoCiudad;
	}

	public void setCodigoCiudad(String codigoCiudad) {
		CodigoCiudad = codigoCiudad;
	}

	public String getCodigoPais() {
		return CodigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		CodigoPais = codigoPais;
	}
}
