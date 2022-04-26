package com.oslomet.webprogrammering.eksamener.vaar2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PakkeRepository {

    @Autowired
    private JdbcTemplate db;

    private Logger logger = LoggerFactory.getLogger(PakkeRepository.class);

    @Transactional
    public boolean lagre(PakkeInformasjon p){

        String queryKunde = "INSERT INTO KundeP (fornavn, etternavn, adresse, postnr, telefonnr, epost) VALUES (?,?,?,?,?,?);";
        String queryPakke = "INSERT INTO Pakke (kid, volum, vekt) VALUES (?,?,?);";

        KeyHolder id = new GeneratedKeyHolder();

        try{
            db.update(con -> {
                PreparedStatement preparedStatement = con.prepareStatement(queryKunde, new String[]{"kid"});
                preparedStatement.setString(1, p.getFornavn());
                preparedStatement.setString(2, p.getEtternavn());
                preparedStatement.setString(3, p.getAdresse());
                preparedStatement.setString(4, p.getPostnr());
                preparedStatement.setString(5, p.getTelefonnr());
                preparedStatement.setString(6, p.getEpost());
                return preparedStatement;
            }, id);
            db.update(queryPakke, id.getKey().intValue(), p.getVolum(), p.getVekt());
            return true;
        }catch (Exception e){
            logger.error("Feil i db: klarte ikke å registere pakke");
            return false;
        }
    }

    public boolean sjekkPostnr(String postnr){

        List<Object> poststeder = db.query("SELECT * FROM Poststed WHERE Postnr="+postnr+";", new BeanPropertyRowMapper<>(Object.class));
        return poststeder.size() > 0;
    }

}
