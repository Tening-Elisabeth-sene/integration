package sn.edu.isepd.tic.dbe.lisa.projet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    private String code;

    @Column(unique = true, nullable = false)
    private String nom;

    @ManyToMany
    private List<Autorisation> autorisations;

    public Role(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Role(String code, String nom, List<Autorisation> autorisations) {
        this.code = code;
        this.nom = nom;
        this.autorisations = autorisations;
    }

}