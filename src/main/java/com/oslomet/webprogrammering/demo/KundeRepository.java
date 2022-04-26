package com.oslomet.webprogrammering.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(KundeRepository.class);

    public boolean lagreKunde(Kunde kunde){
        String sql = "INSERT INTO Kunde (navn, adresse) VALUES (?,?)";
        try{
            db.update(sql, kunde.getNavn(), kunde.getAdresse());
            return true;
        }catch (Exception e){
            logger.error("Feil i lagreKunde: " + e);
            return false;
        }

    }

    public List<Kunde> hentAlleKunder(){
        String sql = "SELECT * FROM Kunde";
        List<Kunde> kunder = db.query(sql, new BeanPropertyRowMapper(Kunde.class));
        return kunder;
    }


    public void slettKunde(Kunde kunde){
        String sql = "DELETE FROM Kunde WHERE navn=? AND adresse=?";
        db.update(sql, kunde.getNavn(), kunde.getAdresse());
    }

    public void slettAlleKunder(){
        String sql = "DELETE FROM Kunde;";
        db.update(sql);
    }
}
