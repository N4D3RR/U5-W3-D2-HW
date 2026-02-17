package naderdeghaili.u5w3d2hw.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dipendenti")
public class Dipendente implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String avatar;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Dipendente() {
    }

    public Dipendente(String username, String nome, String cognome, String email, String password) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.avatar = "https://placecats.com/150/150";
        this.password = password;
        this.ruolo = Ruolo.UTENTE;
    }


    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }

    @Override
    public String toString() {
        return "Dipendente: " +
                "id: " + id +
                " | username" + username +
                " | nome: " + nome +
                " | cognome: " + cognome +
                " | email: " + email +
                " | avatar: " + avatar
                ;
    }
}
