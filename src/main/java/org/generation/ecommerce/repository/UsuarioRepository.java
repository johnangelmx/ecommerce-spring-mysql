package org.generation.ecommerce.repository;

import org.generation.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // TODO metodo para buscar usuarios repetidos
    Optional<Usuario> findByEmail(String email);
}//* interface Usuario
