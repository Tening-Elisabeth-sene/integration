package sn.edu.isepd.tic.dbe.lisa.projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {
    boolean existsByDeputeIdAndVoteId(Long deputeId, Long voteId);
    
    
    List<Bulletin> findByVoteId(Long voteId);
}


