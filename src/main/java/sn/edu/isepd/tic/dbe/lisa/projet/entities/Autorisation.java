package sn.edu.isepd.tic.dbe.lisa.projet.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "autorisation")
public class Autorisation {

    @Id
    private String code;

    @Column(unique = true, nullable = false)
    private String nom;

    private String description;

    public Autorisation(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Autorisation(String code, String nom, String description) {
        this.code = code;
        this.nom = nom;
        this.description = description;
    }
}