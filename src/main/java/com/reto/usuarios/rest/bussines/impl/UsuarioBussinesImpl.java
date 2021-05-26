package com.reto.usuarios.rest.bussines.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.reto.usuarios.rest.bussines.IUsuarioBussines;
import com.reto.usuarios.rest.dao.IUsuarioDao;
import com.reto.usuarios.rest.facade.dto.TelefonoDTO;
import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

@Component(value = "usuarioBussines")
public class UsuarioBussinesImpl implements IUsuarioBussines{
	
	@Resource(name = "usuarioDao")
	private IUsuarioDao usuarioDao;

	@Override
	public UsuarioDTO crearUsuarioBu(UsuarioDTO usuarioDTO) {
		validacionesBussines(usuarioDTO);
		validacionesDao(usuarioDTO);

		usuarioDTO = usuarioDao.crearUsuarioDa(usuarioDTO);
		
		for (TelefonoDTO telefono : usuarioDTO.getPhones()) {
			int idTelefono = usuarioDao.crearTelefono(telefono);
			usuarioDao.crearUsuarioTelefono(usuarioDTO.getId_user(), idTelefono);
		}
		
		return usuarioDTO;
	}
	
	@Override
	public List<UsuarioDTO> listarUsuarios() {
		return usuarioDao.listarUsuarioDao();
	}

	@Override
	public UsuarioDTO consultarUsuario(int idUsuario) {
		return usuarioDao.consultarUsuarioDao(idUsuario);
	}
	
	private void validacionesDao(UsuarioDTO usuarioDTO) {
		List<UsuarioDTO> listUsuario = usuarioDao.listarUsuarioDao();
		
		for (UsuarioDTO usuarioEntity : listUsuario) {
			if (usuarioEntity.getEmail().equalsIgnoreCase(usuarioDTO.getEmail())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo ya registrado");
			}
		}
	}
	
	private void validacionesBussines(UsuarioDTO usuarioDTO) {
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(usuarioDTO.getEmail());
		
		if (mather.find()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Correo incorrecto");
		}
		
		pattern = Pattern.compile("^[0-9]{2}$");
		mather = pattern.matcher(usuarioDTO.getEmail());
	}
}
