package org.generation.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;

    public Usuario(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + '}';
    }
}
