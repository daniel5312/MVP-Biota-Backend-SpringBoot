package com.biota.backend.controllers;

import com.biota.backend.models.Usuario;
import com.biota.backend.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.biota.backend.dto.LoginRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173") // frontend de Vite
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Registrar nuevo usuario
    @PostMapping("/register")
    public Usuario registerUser(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    // Obtener todos (solo para pruebas)
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
    // Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> datos) {
        String email = datos.get("email");
        String password = datos.get("password");

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            } else {
                return ResponseEntity.ok(usuario); // Devuelve el usuario completo
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

}
