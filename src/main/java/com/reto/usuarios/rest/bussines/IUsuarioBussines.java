package com.reto.usuarios.rest.bussines;

import java.util.List;

import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

public interface IUsuarioBussines {

	UsuarioDTO crearUsuarioBu(UsuarioDTO usuarioDTO);
	List<UsuarioDTO> listarUsuarios();
	UsuarioDTO consultarUsuario(int idUsuario);
	
}
