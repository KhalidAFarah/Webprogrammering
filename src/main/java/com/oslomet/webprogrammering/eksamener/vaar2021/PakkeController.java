package com.oslomet.webprogrammering.eksamener.vaar2021;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PakkeController {

    @Autowired
    private PakkeRepository repository;

    private Logger logger = LoggerFactory.getLogger(PakkeController.class);

    @PostMapping("/api/eksamen/var2021/")
    public void RegistrerPakke(PakkeInformasjon p, HttpServletResponse response){

        String regexp_navn = "[A-ZØÆÅa-zøæå]{3,50}";
        String regexp_adresse = "[A-ZØÆÅa-zøæå0-9\\,\\.\\s]{2,50}";
        String regexp_postnr = "[A-ZØÆÅa-zøæå\\s\\,\\.]{4}";
        String regexp_telefonnr = "[0-9]{8}";
        String regexp_epost = "[A-ZØÆÅa-zøæå._%+-]+@[A-ZØÆÅa-zøæå._%+-]+\\.[A-Za-z]{2,}";

        boolean fornavnOK = p.getFornavn().matches(regexp_navn);
        boolean etternavnOK = p.getEtternavn().matches(regexp_navn);

        boolean adresseOK = p.getAdresse().matches(regexp_adresse);
        boolean postnrOK = p.getPostnr().matches(regexp_postnr);
        boolean telefonnrOK = p.getTelefonnr().matches(regexp_telefonnr);
        boolean epostOK = p.getEpost().matches(regexp_epost);

        boolean volumOK = p.getVolum() > 0;
        boolean vektOK = p.getVekt() > 0;

        if(!fornavnOK && !etternavnOK && !adresseOK && !postnrOK && !telefonnrOK && !epostOK && !volumOK && vektOK){
            logger.error("Validerings feil i registrering av en pakke");

            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Feil i input gitt.");
            } catch (IOException e) {
                logger.error("Klarte ikke å gi responsen om feil i validering i registreringen av en pakke.");
            }
            return;
        }

        if(!repository.sjekkPostnr(p.getPostnr())){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Postnr eksisterer ikke");
            } catch (IOException e) {
                logger.error("klarte ikke å sende respons");
            }
        }

        if(!repository.lagre(p)){
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB: klarte ikke å lagre pakke");
            } catch (IOException e) {
                logger.error("Klarte ikke å gi responsen om feil i DB om lagring av en pakke.");
            }
        }
    }

    @GetMapping("/api/eksamen/var2021/")
    public void SjekkPostnr(String postnr, HttpServletResponse response){
        if(repository.sjekkPostnr(postnr)){
            try {
                response.sendError(HttpStatus.OK.value(), "Postnr eksisterer");
            } catch (IOException e) {
                logger.error("klarte ikke å sende respons");
            }
        }else{
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Postnr eksisterer ikke");
            } catch (IOException e) {
                logger.error("klarte ikke å sende respons");
            }
        }


    }
}
