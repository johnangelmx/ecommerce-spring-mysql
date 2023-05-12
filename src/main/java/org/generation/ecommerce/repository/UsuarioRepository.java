package org.generation.ecommerce.repository;

import org.generation.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // TODO metodo para buscar usuarios repetidos
}//* interface Usuario
