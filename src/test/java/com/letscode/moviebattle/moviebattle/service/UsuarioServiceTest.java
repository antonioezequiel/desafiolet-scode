package com.letscode.moviebattle.moviebattle.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.letscode.moviebattle.moviebattle.classes.Usuario;
import com.letscode.moviebattle.moviebattle.repository.RoleRepository;
import com.letscode.moviebattle.moviebattle.repository.UsuarioRepository;

class UsuarioServiceTest {
	@Mock
	UsuarioRepository usuarioRepositoryMock;
	@Mock
	RoleRepository roleRepositoryMock;
	UsuarioService usuarioService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);		
		this.usuarioService = new UsuarioService(usuarioRepositoryMock, roleRepositoryMock);
	}

	@Test
	void testLoadUserByUsername() {
		Usuario usuario = new Usuario();
		usuario.setNome("antonio");
		Optional<Usuario> usuarioOptinal = Optional.of(usuario);
		Mockito.when(usuarioRepositoryMock.findById("antonio")).thenReturn(usuarioOptinal);
		usuario = usuarioService.loadUserByUsername("antonio");
		assertEquals("antonio", usuario.getNome());
	}

}
