package sn.edu.isepd.tic.dbe.lisa.projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isepdiamniadio.Ecommerce.entities.Authentification;

@Repository
public interface AuthentificationRepository extends JpaRepository<Authentification, String> {
}

