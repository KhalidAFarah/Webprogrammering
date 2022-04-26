package com.oslomet.webprogrammering.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class KundeController {

    @Autowired
    KundeRepository repository;

    @Autowired
    private HttpSession session;

    @GetMapping("/demo/kunde/")
    public Kunde hentKunde(Kunde kunde, HttpServletResponse response){
        /*if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Ikke logget inn.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/
        return kunde;
    }
    @GetMapping("/demo/kunder/")
    public List<Kunde> hentKunder(HttpServletResponse response){
        /*if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Ikke logget inn.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/

        return repository.hentAlleKunder();
    }
    @PostMapping("/demo/kunder/")
    public Kunde leggTilKunde(Kunde kunde, HttpServletResponse response){
        /*if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Ikke logget inn.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }*/

        String regexp_navn = "[A-ZØÆÅa-zøæå]{2,30}";
        String regexp_adresse = "[0-9a-zA-ZæøåÆØÅ .\\-]{2,50}";

        boolean navnOK = kunde.getNavn().matches(regexp_navn);
        boolean adresseOK = kunde.getAdresse().matches(regexp_adresse);
        if(navnOK && adresseOK){
            if(!repository.lagreKunde(kunde)){
                try {
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Feil i DB - prøv igjen senere");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return kunde;
        }else if(!navnOK && adresseOK){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Navn er ugyldig");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else if(navnOK && !adresseOK){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Adresse er ugyldig");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else{
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Navn og adresees er ugyldig");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    @DeleteMapping("demo/kunder/")
    public void slettAllkunder(HttpServletResponse response){
        /*if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(), "Ikke logget inn.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }*/
        repository.slettAlleKunder();
    }

    @GetMapping("demo/login")
    public void login(Kunde kunde, HttpServletResponse response){
        //if(repository.sjekkNavnOgPassord(kunde)){
            //session.setAttribute("innlogget", kunde);
        //}else{

        //}
    }

    @GetMapping("demo/logut")
    public void logut(Kunde kunde){
        session.removeAttribute("innlogget");
    }


}
