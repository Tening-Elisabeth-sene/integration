package sn.edu.isepd.tic.dbe.lisa.projet.endPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import javax.persistence.EntityNotFoundException;
import sn.edu.isepd.tic.dbe.lisa.projet.service.VoteService;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Vote;

@RestController
@RequestMapping("/api")
public class VoteController {
    private final VoteService voteService;

    @PutMapping("/vote/{idVote}/status")
    @PreAuthorize("hasRole('PRESIDENT')")
    public ResponseEntity<String> changerEtatVote(@PathVariable Long idVote) {
        try {
            voteService.changerEtat(idVote);
            return ResponseEntity.ok("L'état du vote a été modifié.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Identifiant de vote introuvable!");
        }
    }

    @GetMapping("/votes")
    public ResponseEntity<List<Vote>> listerVotesOuverts() {
        return ResponseEntity.ok(voteService.obtenirVotesOuverts());
    }

    @GetMapping("/vote/{id}/results")
    @PreAuthorize("hasRole('PRESIDENT')")
    public ResponseEntity<Map<String, Long>> obtenirResultats(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(voteService.obtenirResultats(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}


