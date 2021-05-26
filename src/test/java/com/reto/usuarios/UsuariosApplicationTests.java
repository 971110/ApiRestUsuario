package com.reto.usuarios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.boot.test.context.SpringBootTest;

import com.reto.usuarios.rest.dao.entity.UsuarioEntity;
import com.reto.usuarios.rest.facade.dto.UsuarioDTO;

@SpringBootTest
class UsuariosApplicationTests {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("persistenceH2");
		manager = emf.createEntityManager();
		
		manager = emf.createEntityManager();
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombre("MArlon");
		usuarioEntity.setCorreo("temp");
		usuarioEntity.setClave("123");
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
	    usuarioEntity.setFechaCreacion(formateador.format(new Date()));
		
		manager.getTransaction().begin();
		manager.persist(usuarioEntity);
		manager.getTransaction().commit();
		
		List<UsuarioEntity> usuario = (List<UsuarioEntity>) manager.createQuery("FROM UsuarioEntity").getResultList();
		
		System.out.println(usuario.get(0).getClave());
		System.out.println(usuario.get(0).getCorreo());
		System.out.println(usuario.get(0).getFechaCreacion());
		System.out.println(usuario.get(0).getId_usuario());
		System.out.println(usuario.get(0).getNombre());
	}
	
	public UsuarioDTO listarUsuarioDao() {
		
		manager = emf.createEntityManager();
		
		List<UsuarioEntity> usuario = (List<UsuarioEntity>) manager.createQuery("FROM UsuarioEntity").getResultList();
		
		
		
		return null;
	}
	

}