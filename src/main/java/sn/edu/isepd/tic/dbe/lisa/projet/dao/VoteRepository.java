package sn.edu.isepd.tic.dbe.lisa.projet.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByEtat(String etat);

    
    boolean existsById(Long id);
}


