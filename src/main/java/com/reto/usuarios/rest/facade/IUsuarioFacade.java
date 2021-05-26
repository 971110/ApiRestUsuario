package com.reto.usuarios.rest.facade;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

public interface IUsuarioFacade {

	ResponseEntity<UsuarioDTO> crearUsuario(UsuarioDTO usuarioDTO);
	ResponseEntity<List<UsuarioDTO>> listarUsuarios();
	ResponseEntity<UsuarioDTO> consultarUsuario(int idUsuario);
	
}
