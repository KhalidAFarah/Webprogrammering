package com.oslomet.webprogrammering.uke6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrukerRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(BrukerRepository.class);

    public boolean lagre(Bruker bruker){
        String query = "INSERT INTO Bruker (brukernavn, passord, admin) VALUES (?,?,?)";
        try{
            db.update(query, bruker.getBrukernavn(), bruker.getPassord(), false);
            return true;
        }catch (Exception e){
            logger.error("Feil ved lagring i BrukerReposistory");
            return false;
        }
    }
    public Bruker get(int id){
        return  db.queryForObject("SELECT * FROM Bruker WHERE id=?;", Bruker.class, id);
    }
    public List<Bruker> getAll(){
        return  db.query("SELECT * FROM Bruker;", new BeanPropertyRowMapper<>(Bruker.class));
    }

    public boolean update(int id, Bruker bruker){
        String query = "UPDATE Motorvogn " +
                "SET brukernavn=?, passord=? " +
                "WHERE id=?;";

        try{
            db.update(query, bruker.getBrukernavn(), bruker.getPassord(), id);
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke oppdatere en bruker i BrukerReposistory");
            return false;
        }

    }

    public boolean remove(int id){
        try{
            db.update("DELETE FROM Bruker WHERE id=?", id);
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke fjerne en bruker i BrukerReposistory");
            return false;
        }
    }

    public boolean clear(){
        try{
            db.update("DELETE FROM Bruker");
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke tomme bruker tabellen i BrukerReposistory");
            return false;
        }
    }

    public boolean sjekkBrukernavnOgPassord(Bruker bruker){
        Bruker funnetbruker = db.queryForObject("SELECT * FROM Bruker WHERE brukernavn=? AND passord=?;", Bruker.class,
                bruker.getBrukernavn(), bruker.getPassord());
        return funnetbruker != null;
    }

}
