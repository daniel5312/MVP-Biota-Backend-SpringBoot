package com.biota.backend.models;

import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String password;
    private String rol;
    private String finca;
    private String municipio;
    private String vereda;
    private String productos;
    private String etapa;

    // Constructor vacío
    public Usuario() {}

    // Constructor completo
    public Usuario(String nombre, String email, String password, String rol,
                   String finca, String municipio, String vereda, String productos, String etapa) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.rol = rol;
        this.finca = finca;
        this.municipio = municipio;
        this.vereda = vereda;
        this.productos = productos;
        this.etapa = etapa;
    }

    // Getters y setters manuales (puedes omitir Lombok si esto funciona)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getFinca() { return finca; }
    public void setFinca(String finca) { this.finca = finca; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getVereda() { return vereda; }
    public void setVereda(String vereda) { this.vereda = vereda; }

    public String getProductos() { return productos; }
    public void setProductos(String productos) { this.productos = productos; }

    public String getEtapa() { return etapa; }
    public void setEtapa(String etapa) { this.etapa = etapa; }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", finca='" + finca + '\'' +
                ", municipio='" + municipio + '\'' +
                ", vereda='" + vereda + '\'' +
                ", productos='" + productos + '\'' +
                ", etapa='" + etapa + '\'' +
                '}';
    }
}
