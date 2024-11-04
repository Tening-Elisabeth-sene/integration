package sn.edu.isepd.tic.dbe.lisa.projet.entities;

@Entity
@Getter @Setter
public class Depute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prenom;
    private String nom;
    private boolean estPresident = false;

    @OneToMany(mappedBy = "depute")
    private List<Bulletin> bulletins;

  
}


