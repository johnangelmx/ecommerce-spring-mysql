package org.generation.ecommerce.service;

import org.generation.ecommerce.model.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El usuario " + id + " no existe"));
    }

    public Usuario deleteUsuario(Long id) {
        Usuario tmpUser = null;
        if (usuarioRepository.existsById(id)) {
            tmpUser = usuarioRepository.findById(id).get();
            usuarioRepository.deleteById(id);
        }
        return tmpUser;
    }

    public Usuario addUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, ChangePassword changePassword) {
        Usuario tmp = null;
        if (usuarioRepository.existsById(id)) {
            if (changePassword.getPassword() != null && changePassword.getNewPassword() != null) {
                tmp = usuarioRepository.findById(id).get();
                if (tmp.getPassword().equals(changePassword.getPassword())) {
                    tmp.setPassword(changePassword.getNewPassword());
                    usuarioRepository.save(tmp);
                } else {
                    tmp = null;
                }
            }
        } else {
            System.out.println("Update - El usuario con el id " + id + " no existe");
        }
        return tmp;
    }
}
