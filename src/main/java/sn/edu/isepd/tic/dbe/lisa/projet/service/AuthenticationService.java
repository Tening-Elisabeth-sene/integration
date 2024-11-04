package sn.edu.isepd.tic.dbe.lisa.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.edu.isep.tic.dbe.lisa.projet.dao.AuthentificationRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.UserRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Authentification;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {

    String tokenChar="QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    int tokenLength=32;
    private int delaiExpiration = 30 * 60;

    @Autowired
    private AuthentificationRepository  authentificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String login, String password) {
        Optional<User> res = userRepository.findByLoginAndPassword(login, password);
        System.out.println("### User :" + res);
        return userRepository.findByLoginAndPassword(login, password);
    }

    public Authentification genToken(User user){
        while (true){
            String newtoken= generateToKen();
            Optional<Authentification> auth = authentificationRepository.findById(newtoken);
            if(auth.isEmpty()){
                Authentification authentification = new Authentification(newtoken,user);
                Date now = new Date();
                authentification.setCreationDate(now);
                authentification.setLastUpdate(now);
                Date expirationDate = new Date(now.getTime()+delaiExpiration+1000);
                authentification.setExpiritionDate(expirationDate);
                authentificationRepository.save(authentification);
                return authentification;
            }
        }
    }

    private String generateToKen(){
        String res ="";
        for(int i=0;i<tokenLength;i++){
            int postisin = (int)(Math.random() * tokenChar.length());
            res += tokenChar.charAt(postisin);
        }
        return res;
    }

    public Optional<Authentification> findByToken(String token){
        return authentificationRepository.findById(token);
    }

    public void disconnect(Authentification authentification){
        authentification.setDeconnexion(new Date());
        authentification.setLastUpdate(new Date());
        authentificationRepository.save(authentification);
    }

    public String hashPassword(String pwd){
        //les messages Digest = MD5 , GHA-512
        String prefix = "$2a$10$";
        String suffix = "@3u$15$";
        String newPwd = prefix+pwd+suffix;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hash = md.digest(newPwd.getBytes());
            String b64Pwd = Base64.getEncoder().encodeToString(hash);
            return b64Pwd;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
