package org.generation.ecommerce.controller;

import org.generation.ecommerce.model.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/usuarios/")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    private UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }// GET all Usuarios

    @GetMapping(path = "{userId}")
    public Usuario getUsuario(@PathVariable("userId") Long id) {
        return usuarioService.getUsuario(id);
    }

    @DeleteMapping(path = "{userID}")
    public Usuario deleteUsuario(@PathVariable("userID") Long id) {
        return usuarioService.deleteUsuario(id);
    }

    @PostMapping
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        return usuarioService.addUsuario(usuario);
    }

    @PutMapping(path = "{userId}")
    public Usuario updateUsuario(@PathVariable("userId") Long id, @RequestBody ChangePassword changePassword) {
        return usuarioService.updateUsuario(id, changePassword);
    }

}
