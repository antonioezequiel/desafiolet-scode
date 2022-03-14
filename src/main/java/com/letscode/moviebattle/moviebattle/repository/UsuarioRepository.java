package com.letscode.moviebattle.moviebattle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letscode.moviebattle.moviebattle.classes.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
