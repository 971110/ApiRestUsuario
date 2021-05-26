package com.reto.usuarios.rest.facade.imp;

import javax.annotation.Resource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.reto.usuarios.rest.bussines.IUsuarioBussines;
import com.reto.usuarios.rest.facade.IUsuarioFacade;
import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

import io.jsonwebtoken.*;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.List;



@RestController
@RequestMapping("/usuario")
public class UsuarioFacade implements IUsuarioFacade{
	
	@Resource(name = "usuarioBussines")
	private IUsuarioBussines usuarioBussines;

	@Override
	@PostMapping
	public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("JWT", jwtGenerate(usuarioDTO.getName()));
		
		usuarioDTO = usuarioBussines.crearUsuarioBu(usuarioDTO);
		
		return ResponseEntity.ok().headers(responseHeaders).body(usuarioDTO);
	}
	
	@Override
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("JWT", jwtGenerate("listarUsuarios"));
		
		List<UsuarioDTO> listUsuarioDto = usuarioBussines.listarUsuarios();
		
		return ResponseEntity.ok().headers(responseHeaders).body(listUsuarioDto);
	}

	@Override
	@RequestMapping(value = "{idUsuario}")
	public ResponseEntity<UsuarioDTO> consultarUsuario(@PathVariable("idUsuario") int idUsuario) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("JWT", jwtGenerate("consultarUsuario"));
		
		UsuarioDTO usuarioDTO = usuarioBussines.consultarUsuario(idUsuario);
		
		return ResponseEntity.ok().headers(responseHeaders).body(usuarioDTO);
	}

	private String jwtGenerate(String key) {
		long tiempo = System.currentTimeMillis();
		String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, key).setIssuedAt(new Date(tiempo)).setExpiration(new Date(tiempo + 500)).compact();
		
		return jwt;
	}
}
