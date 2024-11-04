package sn.edu.isepd.tic.dbe.lisa.projet.service;

@Service
public class BulletinService {
    private final BulletinRepository bulletinRepository;
    private final DeputeRepository deputeRepository;

    public String enregistrerVote(Long idVote, TypeBulletin choix, Principal principal) throws Exception {
        Depute depute = deputeRepository.findByNomUtilisateur(principal.getName())
                .orElseThrow(() -> new AccessDeniedException("Désolé, vous n’êtes pas autorisé à voter !"));

        if (bulletinRepository.existsByVoteIdAndDeputeId(idVote, depute.getId())) {
            return "Honorable " + depute.getPrenom() + " " + depute.getNom() + ", vous avez déjà voté !";
        }
        // Traitement pour enregistrer le vote en base
    }
}



