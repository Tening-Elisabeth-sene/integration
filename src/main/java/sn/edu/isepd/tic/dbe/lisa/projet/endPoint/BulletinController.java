package sn.edu.isepd.tic.dbe.lisa.projet.endPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.edu.isepd.tic.dbe.lisa.projet.service.BulletinService;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Bulletin;

@RestController
@RequestMapping("/api")
public class BulletinController {
    private final BulletinService bulletinService;

    @PostMapping("/vote/{idVote}/voter")
    public ResponseEntity<?> voter(@PathVariable Long idVote, @RequestParam TypeBulletin choix, Principal principal) {
        try {
            String message = bulletinService.enregistrerVote(idVote, choix, principal);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue, veuillez réessayer plus tard !");
        }
    }
}



