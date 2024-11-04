package sn.edu.isepd.tic.dbe.lisa.projet.service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public void changerEtat(Long idVote) {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new EntityNotFoundException("Identifiant de vote introuvable!"));
        vote.setEtat(vote.getEtat().equals("clos") ? "ouvert" : "clos");
        voteRepository.save(vote);
    }

    public List<Vote> obtenirVotesOuverts() {
        return voteRepository.findByEtat("ouvert");
    }

    public Map<String, Long> obtenirResultats(Long idVote) {
        Vote vote = voteRepository.findById(idVote)
                .orElseThrow(() -> new EntityNotFoundException("Identifiant de vote introuvable!"));
       
    }
}


