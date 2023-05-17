package org.generation.ecommerce.service;

import org.generation.ecommerce.model.ChangePassword;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        Usuario tmp = null;
        if (usuarioRepository.findByEmail(usuario.getEmail()).isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword())); //Codifica la password
            tmp = usuarioRepository.save(usuario);
        }
        return tmp;
    }

    public Usuario updateUsuario(Long id, ChangePassword changePassword) {
        Usuario tmp = null;
        if (usuarioRepository.existsById(id)) {
            if (changePassword.getPassword() != null && changePassword.getNewPassword() != null) {
                tmp = usuarioRepository.findById(id).get();
//                if (tmp.getPassword().equals(changePassword.getPassword())) {
                if (passwordEncoder.matches(changePassword.getPassword(), tmp.getPassword())) { // nuevo encoder
                    tmp.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
                    usuarioRepository.save(tmp);
                } else {
                    tmp = null;
                }
            }
        } else {
            System.out.println("Update - El usuario con el id " + id + " no existe");
        }
        return tmp;
    }// Update User

    public boolean validateUsuario(Usuario usuario) {
        Optional<Usuario> userByEmail = usuarioRepository.findByEmail(usuario.getEmail());
        if (userByEmail.isPresent()) {
            Usuario user = userByEmail.get();
//            if (user.getPassword().equals(usuario.getPassword())) {
            if (passwordEncoder.matches(usuario.getPassword(), user.getPassword())) { //nuevo encoder
                return true;
            }

        }
        return false;
    }


}
