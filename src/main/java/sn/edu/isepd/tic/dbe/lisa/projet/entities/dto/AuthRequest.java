package sn.edu.isepd.tic.dbe.lisa.projet.entities.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String login;

    private String password;

}
