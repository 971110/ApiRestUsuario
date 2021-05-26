package com.reto.usuarios.rest.dao;

import java.util.List;

import com.reto.usuarios.rest.dao.entity.TelefonoEntity;
import com.reto.usuarios.rest.dao.entity.UsuarioEntity;
import com.reto.usuarios.rest.dao.entity.UsuarioTelefonoEntity;
import com.reto.usuarios.rest.facade.dto.TelefonoDTO;
import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

public interface IUsuarioDao {
	
	UsuarioDTO crearUsuarioDa(UsuarioDTO usuarioDTO);
	List<UsuarioDTO> listarUsuarioDao();
	void crearUsuarioTelefono(int idUsuario, int idTelefono);
	int crearTelefono(TelefonoDTO dtoTelefono);
	public List<TelefonoEntity> listarTelefonos();
	public List<UsuarioTelefonoEntity> listarUsuariosTelefonos();
	public UsuarioDTO consultarUsuarioDao(int idUsuario);
	TelefonoEntity consultarTelefono(TelefonoEntity telefonoEntity);
}
