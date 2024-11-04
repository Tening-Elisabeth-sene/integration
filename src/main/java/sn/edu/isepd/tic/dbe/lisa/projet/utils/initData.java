package sn.edu.isepd.tic.dbe.lisa.projet.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.VoteRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.dao.BulletinRepository;
import sn.edu.isepd.tic.dbe.lisa.projet.endPoint.DeputeController;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Vote;
import sn.edu.isepd.tic.dbe.lisa.projet.entities.Bulletin;

@Component
public class initData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }
}



