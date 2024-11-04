package sn.edu.isepd.tic.dbe.lisa.projet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByLoginAndPassword(String login, String password);
}