package sn.edu.isepd.tic.dbe.lisa.projet.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "authentification")
public class Authentification {
    @Id
    private String token;

    @ManyToOne
    private User user;

    private Date expiritionDate;

    private Date creationDate;

    private Date deconnexion;

    private Date lastUpdate;

    public Authentification(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public Authentification(String token, User user, Date expiritionDate, Date creationDate, Date deconnexion, Date lastUpdate) {
        this.token = token;
        this.user = user;
        this.expiritionDate = expiritionDate;
        this.creationDate = creationDate;
        this.deconnexion = deconnexion;
        this.lastUpdate = lastUpdate;
    }

    public boolean isValide(){
        Date now = new Date();
        if(deconnexion != null){
            return false;
        }
        if(expiritionDate.before(now)){
            return false;
        }
        return true;
    }
}