package com.reto.usuarios.rest.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.reto.usuarios.rest.dao.IUsuarioDao;
import com.reto.usuarios.rest.dao.entity.TelefonoEntity;
import com.reto.usuarios.rest.dao.entity.UsuarioEntity;
import com.reto.usuarios.rest.dao.entity.UsuarioTelefonoEntity;
import com.reto.usuarios.rest.facade.dto.TelefonoDTO;
import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

@Component(value = "usuarioDao")
public class UsuarioDao implements IUsuarioDao {
	
	private EntityManager manager;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceH2");

	@Override
	public UsuarioDTO crearUsuarioDa(UsuarioDTO usuarioDTO) {
		manager = emf.createEntityManager();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		String dateInfo = formateador.format(new Date());
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombre(usuarioDTO.getName());
		usuarioEntity.setCorreo(usuarioDTO.getEmail());
		usuarioEntity.setClave(usuarioDTO.getPassword());
	    usuarioEntity.setFechaCreacion(dateInfo);
	    usuarioEntity.setFechaLogin(dateInfo);
	    usuarioEntity.setEstado(true);
	    
		try {
			manager.getTransaction().begin();
			manager.persist(usuarioEntity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear usuario: ", e.getCause());
		}
		
		usuarioDTO.setCreated(dateInfo);
		usuarioDTO.setLast_login(dateInfo);
		usuarioDTO.setId_user(usuarioEntity.getId_usuario());
		
		return usuarioDTO;
	}
	
	@Override
	public int crearTelefono(TelefonoDTO dtoTelefono) {
		manager = emf.createEntityManager();
		int idTelefono;
		
		TelefonoEntity telefonoEntity = new TelefonoEntity();
		telefonoEntity.setCodigoCiudad(dtoTelefono.getCitycode());
		telefonoEntity.setCodigoPais(dtoTelefono.getContrycode());
		telefonoEntity.setNumero(dtoTelefono.getNumber());
		
		try {
			manager.getTransaction().begin();
			manager.persist(telefonoEntity);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear usuario: ", e.getCause());
		}
		
		idTelefono = telefonoEntity.getId_telefono();
		
		return idTelefono;
	}
	
	@Override
	public TelefonoEntity consultarTelefono(TelefonoEntity telefonoEntity) {
		manager = emf.createEntityManager();
		
		TelefonoEntity telefono = manager.find(TelefonoEntity.class,telefonoEntity);
		
		return telefono;
	}
	
	@Override
	public void crearUsuarioTelefono(int idUsuario, int idTelefono) {
		UsuarioTelefonoEntity dtoUsuarioTelefono = new UsuarioTelefonoEntity();
		manager = emf.createEntityManager();
		
		dtoUsuarioTelefono.setId_usuario(idUsuario);
		dtoUsuarioTelefono.setId_telefono(idTelefono);
		
		try {
			manager.getTransaction().begin();
			manager.persist(dtoUsuarioTelefono);
			manager.getTransaction().commit();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Error al crear usuario: ", e.getCause());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioDTO> listarUsuarioDao() {
		manager = emf.createEntityManager();
		List<UsuarioDTO> listUsuarioDTO = new ArrayList<>();
		
		List<UsuarioEntity> listUsuarios = (List<UsuarioEntity>) manager.createQuery("FROM UsuarioEntity").getResultList();
		
		List<UsuarioTelefonoEntity> listUsuarioTelefono = listarUsuariosTelefonos();
		List<TelefonoEntity> listTelefono = listarTelefonos();
		
		UsuarioDTO tempUsuarioDTO;
		for (UsuarioEntity usuarioEntity : listUsuarios) {
			tempUsuarioDTO = new UsuarioDTO();
			
			tempUsuarioDTO.setId_user(usuarioEntity.getId_usuario());
			tempUsuarioDTO.setName(usuarioEntity.getNombre());
			tempUsuarioDTO.setEmail(usuarioEntity.getCorreo());
			tempUsuarioDTO.setPassword(usuarioEntity.getClave());
			tempUsuarioDTO.setCreated(usuarioEntity.getFechaCreacion());
			tempUsuarioDTO.setLast_login(usuarioEntity.getFechaLogin());
			
			TelefonoDTO dtoTelefono;
			tempUsuarioDTO.setPhones(new ArrayList<>());
			
			for (UsuarioTelefonoEntity usuarioTelefono : listUsuarioTelefono) {
				if (usuarioEntity.getId_usuario() == usuarioTelefono.getId_usuario()) {
					for (TelefonoEntity telefonoEntity2 : listTelefono) {
						if (telefonoEntity2.getId_telefono() == usuarioTelefono.getId_telefono()) {
							dtoTelefono = new TelefonoDTO();
							dtoTelefono.setCitycode(telefonoEntity2.getCodigoCiudad());
							dtoTelefono.setContrycode(telefonoEntity2.getCodigoPais());
							dtoTelefono.setNumber(telefonoEntity2.getNumero());
							tempUsuarioDTO.getPhones().add(dtoTelefono);
						}
					}
				}
			}
			
			listUsuarioDTO.add(tempUsuarioDTO);
		}
		
		return listUsuarioDTO;
	}
	
	@Override
	public UsuarioDTO consultarUsuarioDao(int idUsuario) {
		manager = emf.createEntityManager();
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		
		UsuarioEntity usuarioEntity = manager.find(UsuarioEntity.class, idUsuario);
		
		List<UsuarioTelefonoEntity> listUsuarioTelefono = listarUsuariosTelefonos();
		List<TelefonoEntity> listTelefono = listarTelefonos();
		
		TelefonoDTO dtoTelefono;
		usuarioDTO.setPhones(new ArrayList<>());
		
		usuarioDTO.setId_user(usuarioEntity.getId_usuario());
		usuarioDTO.setName(usuarioEntity.getNombre());
		usuarioDTO.setEmail(usuarioEntity.getCorreo());
		usuarioDTO.setPassword(usuarioEntity.getClave());
		usuarioDTO.setCreated(usuarioEntity.getFechaCreacion());
		usuarioDTO.setLast_login(usuarioEntity.getFechaLogin());
		
		for (UsuarioTelefonoEntity usuarioTelefono : listUsuarioTelefono) {
			if (usuarioEntity.getId_usuario() == usuarioTelefono.getId_usuario()) {
				for (TelefonoEntity telefonoEntity2 : listTelefono) {
					if (telefonoEntity2.getId_telefono() == usuarioTelefono.getId_telefono()) {
						dtoTelefono = new TelefonoDTO();
						dtoTelefono.setCitycode(telefonoEntity2.getCodigoCiudad());
						dtoTelefono.setContrycode(telefonoEntity2.getCodigoPais());
						dtoTelefono.setNumber(telefonoEntity2.getNumero());
						usuarioDTO.getPhones().add(dtoTelefono);
					}
				}
			}
		}
		
		return usuarioDTO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TelefonoEntity> listarTelefonos() {
		manager = emf.createEntityManager();
		List<TelefonoEntity> usuario = (List<TelefonoEntity>) manager.createQuery("FROM TelefonoEntity").getResultList();
		
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioTelefonoEntity> listarUsuariosTelefonos() {
		manager = emf.createEntityManager();
		List<UsuarioTelefonoEntity> usuario = (List<UsuarioTelefonoEntity>) manager.createQuery("FROM UsuarioTelefonoEntity").getResultList();
		
		return usuario;
	}
}
