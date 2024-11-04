package sn.edu.isepd.tic.dbe.lisa.projet.entities.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthReponse {

    private String token;

    private String login;

    private Set<String> authorisations;

    public AuthReponse(Authentification authentification) {
        this.token = authentification.getToken();
        this.login = authentification.getUser().getLogin();
        this.authorisations=new HashSet<>();
        for(Role role:authentification.getUser().getRoles()){
            for (Autorisation auth:role.getAutorisations()){
                this.authorisations.add(auth.getCode());
            }
        }
    }
}
