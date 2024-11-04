package sn.edu.isepd.tic.dbe.lisa.projet.endPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Depute;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.VoteRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.BulletinRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.DeputeRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeputeController {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private DeputeRepository deputeRepository;
    @GetMapping("/votes")
    @PreAuthorize("hasRole('DEPUTE')")
    public ResponseEntity<List<Vote>> getOpenVotes() {
        List<Vote> openVotes = voteRepository.findByEtat("ouvert");
        return ResponseEntity.ok(openVotes);
    }

    
    @PostMapping("/vote/{voteId}/bulletin")
    @PreAuthorize("hasRole('DEPUTE')")
    public ResponseEntity<?> castVote(
            @PathVariable Long voteId,
            @RequestParam Long deputeId,
            @RequestParam String bulletinType) {
        
        // Vérifie si le vote existe et s'il est ouvert
        Optional<Vote> voteOptional = voteRepository.findById(voteId);
        if (voteOptional.isEmpty()) {
            return new ResponseEntity<>("Identifiant de vote introuvable!", HttpStatus.NOT_FOUND);
        }
        
        Vote vote = voteOptional.get();
        if (!"ouvert".equals(vote.getEtat())) {
            return new ResponseEntity<>("Désolé, Honorable, le vote est déjà clos!", HttpStatus.FORBIDDEN);
        }
        
        // Vérifie si le député existe
        Optional<Depute> deputeOptional = deputeRepository.findById(deputeId);
        if (deputeOptional.isEmpty()) {
            return new ResponseEntity<>("Désolé, vous n'êtes pas autorisé à voter!", HttpStatus.UNAUTHORIZED);
        }
        
        // Vérifie si le député a déjà voté pour ce vote
        if (bulletinRepository.existsByDeputeIdAndVoteId(deputeId, voteId)) {
            return new ResponseEntity<>("Honorable, vous avez déjà voté!", HttpStatus.FORBIDDEN);
        }

        // Vérifie si le bulletin est valide (OUI, NON, ABSTENTION)
        if (!bulletinType.equals("OUI") && !bulletinType.equals("NON") && !bulletinType.equals("ABSTENTION")) {
            return new ResponseEntity<>("Bulletin invalide. Veuillez choisir entre OUI, NON ou ABSTENTION.", HttpStatus.BAD_REQUEST);
        }

        // Enregistre le bulletin de vote
        Bulletin bulletin = new Bulletin();
        bulletin.setDeputeId(deputeId);
        bulletin.setVoteId(voteId);
        bulletin.setBulletin(bulletinType);
        bulletinRepository.save(bulletin);

        // Réponse de succès
        Depute depute = deputeOptional.get();
        String message = "Honorable " + depute.getPrenom() + " " + depute.getNom() + ", votre vote est bien pris en compte !";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}



