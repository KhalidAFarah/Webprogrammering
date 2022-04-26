package com.oslomet.webprogrammering.uke6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorvognRepository {
    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(MotorvognRepository.class);

    public List<Motorvogn> getAll(){
        return  db.query("SELECT * FROM Motorvogn;", new BeanPropertyRowMapper<>(Motorvogn.class));
    }
    public Motorvogn get(int id){
        return  db.queryForObject("SELECT * FROM Motorvogn WHERE id=?;", Motorvogn.class, id);
    }
    public boolean clear(){
        try{
            db.update("DELETE FROM Motorvogn");
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke tomme motorvogn tabellen i MotorvognReposistory");
            return false;
        }
    }

    public boolean lagre(Motorvogn motorvogn){
        String query = "INSERT INTO Motorvogn (personnummer, navn, adresse, kjennetegn, bilmerke, biltype) VALUES (?,?,?,?,?,?)";
        try{
            db.update(query,
                    motorvogn.getPersonnummer(), motorvogn.getNavn(), motorvogn.getAdresse(),
                    motorvogn.getKjennetegn(), motorvogn.getBilmerke(), motorvogn.getBiltype());
            return true;
        }catch (Exception e){
            logger.error("Feil ved Lagring i MotorvognReposistory");
            return false;
        }

    }

    public boolean remove(int id){
        try{
            db.update("DELETE FROM Motorvogn WHERE id=?", id);
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke fjerne en motorvogn i MotorvognReposistory");
            return false;
        }
    }

    public boolean update(int id, Motorvogn motorvogn){
        String query = "UPDATE Motorvogn " +
                "SET personnummer=?, navn=?, adresse=?, kjennetegn=?, bilmerke=?, biltype=? " +
                "WHERE id=?;";

        try{
            db.update(query,
                    motorvogn.getPersonnummer(), motorvogn.getNavn(), motorvogn.getAdresse(),
                    motorvogn.getKjennetegn(), motorvogn.getBilmerke(), motorvogn.getBiltype(), id);
            return true;
        }catch (Exception e){
            logger.error("Kunne ikke oppdatere en motorvogn i MotorvognReposistory");
            return false;
        }

    }
}
