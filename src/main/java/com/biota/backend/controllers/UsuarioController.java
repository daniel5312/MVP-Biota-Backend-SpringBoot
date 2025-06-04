package com.biota.backend.controllers;

import com.biota.backend.dto.LoginRequest;
import com.biota.backend.models.Usuario;
import com.biota.backend.repositories.UsuarioRepository;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@EqualsAndHashCode
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
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔐 Login recibido: " + loginRequest.getEmail());

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuarioOpt.get().equals(loginRequest.getPassword())) {
                System.out.println("✅ Login correcto");
                return ResponseEntity.ok(usuario);
            } else {
                System.out.println("❌ Contraseña incorrecta");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } else {
            System.out.println("❌ Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    /*@PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(usuario); // ✅ Login exitoso
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }*/
    }

}