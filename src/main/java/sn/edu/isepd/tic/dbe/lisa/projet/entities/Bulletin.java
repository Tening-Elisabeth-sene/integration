package sn.edu.isepd.tic.dbe.lisa.projet.entities;

@Entity
@Data @Getter @Setter
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeBulletin choix; 

    @ManyToOne
    private Vote vote;

    @ManyToOne
    private Depute depute;


}

public enum TypeBulletin {
    OUI, NON, ABSTENTION
}

