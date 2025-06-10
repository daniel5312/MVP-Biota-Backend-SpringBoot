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
import java.util.Optional;

@EqualsAndHashCode
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173") // frontend de Vite
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // ✅ Registrar nuevo usuario
    @PostMapping("/register")
    public Usuario registerUser(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // ✅ Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // ✅ Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔐 Intentando login para: " + loginRequest.getEmail());

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuario.getPassword().equals(loginRequest.getPassword())) {
                System.out.println("✅ Login exitoso para: " + usuario.getEmail());
                return ResponseEntity.ok(usuario); // Devuelve el usuario autenticado
            } else {
                System.out.println("❌ Contraseña incorrecta para: " + loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } else {
            System.out.println("❌ Usuario no encontrado: " + loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody Usuario datosActualizados) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(datosActualizados.getNombre());
            usuario.setEmail(datosActualizados.getEmail());
            usuario.setPassword(datosActualizados.getPassword());
            usuario.setRol(datosActualizados.getRol());
            usuario.setFinca(datosActualizados.getFinca());
            usuario.setMunicipio(datosActualizados.getMunicipio());
            usuario.setVereda(datosActualizados.getVereda());
            usuario.setProductos(datosActualizados.getProductos());
            usuario.setEtapa(datosActualizados.getEtapa());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuarioRepository.delete(usuario);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
