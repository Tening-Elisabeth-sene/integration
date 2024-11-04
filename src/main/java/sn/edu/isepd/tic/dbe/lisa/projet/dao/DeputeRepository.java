package sn.edu.isepd.tic.dbe.lisa.projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeputeRepository extends JpaRepository<Depute, Long> {
    Optional<Depute> findById(Long id);
    boolean existsById(Long id);
    Optional<Depute> findByNomAndPrenom(String nom, String prenom);
}


public class DeputeRepository {

}
