package sn.edu.isepd.tic.dbe.lisa.projet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.JOINED)
public class President implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false,length = 64)
    private String libelle;

    @Column(nullable = false,length = 64)
    private String etat;

    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "vote")
    private List<Bulletin> bulletins;

   

  

}


