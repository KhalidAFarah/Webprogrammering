package com.oslomet.webprogrammering.uke6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MotovognController {
    @Autowired
    private MotorvognRepository motorvognRepository;

    @Autowired
    private BrukerRepository brukerRepository;

    private final String[] merker = {"Volvo", "Nissan", "Tesla"};

    private Logger logger = LoggerFactory.getLogger(MotovognController.class);

    @Autowired
    private HttpSession session;

    @GetMapping("/api/uke15/motorvogn/login/")
    public boolean login(Bruker bruker, HttpServletResponse response){
        if(brukerRepository.sjekkBrukernavnOgPassord(bruker)){
           session.setAttribute("innlogget", bruker);
            return true;
        }else{
            try {
                response.sendError(HttpStatus.NOT_FOUND.value(),
                        "Kunne ikke finne brukeren.");

            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om feilet innlogging");
            }
            return false;
        }
    }
    @GetMapping("/api/uke15/motorvogn/logut/")
    public void logut(Bruker bruker, HttpServletResponse response){
        if(session.getAttribute("innlogget") != null){
            session.removeAttribute("innlogget");
            try {
                response.sendError(HttpStatus.OK.value(),
                        "logget ut.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om ut logging");
            }
        }else{
            try {
                response.sendError(HttpStatus.NOT_FOUND.value(),
                        "Alerede logget ut.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om feilet utlogging");
            }
        }
    }

    public boolean validering(Bruker bruker) {
        String regexp = "[A-Za-z0-9\\s]{3,20}";

        boolean brukernavnOK = bruker.getBrukernavn().matches(regexp);
        boolean passordOK = bruker.getPassord().matches(regexp);

        if(brukernavnOK && passordOK){
            return true;
        }
        logger.error("Validerings feil");
        return false;
    }

    @PostMapping("/api/uke15/motorvogner/bruker/")
    public void registrer(Bruker bruker, HttpServletResponse response){
        if(validering(bruker)){
            if(!brukerRepository.lagre(bruker)){
                try {
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Feil i DB - klarte ikke aa registrere en bruker");
                } catch (IOException e) {
                    logger.error("Kunne ikke registrere en bruker og sende det som respons");
                }
            }
        }else{
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(),
                        "Feil input - Valideringsfeil for registrering av en bruker");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons for valideringsfeil");
            }
        }
    }

    public boolean validering(Motorvogn motorvogn){
        String regexp_personnummer = "[0-9]{11}";
        String regexp_navn = "[A-ZØÆÅa-zøæå\\s]{3,30}";
        String regexp_adresse = "[A-ZØÆÅa-zøæå0-9\\-,\\s]{3,50}";
        String regexp_kjennetegn = "[A-Z0-9]{3,10}";

        boolean personnummerOK = motorvogn.getPersonnummer().matches(regexp_personnummer);
        boolean navnOK = motorvogn.getNavn().matches(regexp_navn);
        boolean adresseOK = motorvogn.getAdresse().matches(regexp_adresse);
        boolean kjennetegnOK = motorvogn.getKjennetegn().matches(regexp_kjennetegn);
        boolean bilmerkeOK = motorvogn.getBilmerke() != null && !motorvogn.getBilmerke().equals("");
        boolean biltypeOK = motorvogn.getBiltype() != null && !motorvogn.getBiltype().equals("");

        if(personnummerOK && navnOK && adresseOK && kjennetegnOK && bilmerkeOK && biltypeOK){
            return true;
        }
        logger.error("Validerings feil");
        return false;
    }

    @PostMapping("/api/uke6/motorvogner/")
    public List<Motorvogn> registrerMotorvogn(Motorvogn motorvogn, HttpServletResponse response){
        if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                        "For å registrere en motorvogn maa du veere logget inn.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om at brukeren maa veere logget inn for aa registrere motorvogn");
            }
            return null;
        }

        if(validering(motorvogn)){
            if(!motorvognRepository.lagre(motorvogn)){
                try {
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Feil i DB - klarte ikke aa registrere en motorvogner");
                    return null;
                } catch (IOException e) {
                    logger.error("Kunne ikke registrere en motorvogn og sende det som respons");
                }
            }
            return motorvognRepository.getAll();
        }else{
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(),
                        "Feil input - Valideringsfeil for registrering av en motorvogn");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons for valideringsfeil");
            }
            return null;
        }
    }
    @GetMapping("/api/uke6/motorvogner/")
    public List<Motorvogn> getMotorvogner(HttpServletResponse response){
        return motorvognRepository.getAll();
    }

    @GetMapping("/api/uke14/motorvogner/{id}/")
    public Motorvogn getMotorvogner(@PathVariable("id") int id, HttpServletResponse response){
        return motorvognRepository.get(id);
    }

    @DeleteMapping("/api/uke6/motorvogner/")
    public List<Motorvogn> tomListe(HttpServletResponse response){
        if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                        "For å registrere en motorvogn maa du veere logget inn.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om at brukeren maa veere logget inn for aa tomme motorvogn tabellen");
            }
            return null;
        }

        if(!motorvognRepository.clear()){
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Feil i DB - klarte ikke aa fjerne alle motorvogner");
            } catch (IOException e) {
                logger.error("Kunne ikke tomme motorvogn tabellen og sende det som respons");
            }
            return motorvognRepository.getAll();
        }
        return new ArrayList<>();
    }
    @DeleteMapping("/api/uke6/motorvogner/{id}/")
    public List<Motorvogn> removeMotorvogn(@PathVariable("id") int id, HttpServletResponse response){
        if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                        "For å registrere en motorvogn maa du veere logget inn.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om at brukeren maa veere logget inn for aa fjerne en motorvogn");
            }
            return null;
        }

        if(!motorvognRepository.remove(id)){
            try {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Feil i DB - klarte ikke aa fjerne en motorvogner");
            } catch (IOException e) {
                logger.error("Kunne ikke fjerne en motorvogn og sende det som respons");
            }
        }
        return motorvognRepository.getAll();
    }
    @PutMapping("/api/uke14/motorvogner/{id}/")
    public List<Motorvogn> updateMotorvogn(@PathVariable("id") int id, Motorvogn motorvogn, HttpServletResponse response){
        if(session.getAttribute("innlogget") == null){
            try {
                response.sendError(HttpStatus.METHOD_NOT_ALLOWED.value(),
                        "For å registrere en motorvogn maa du veere logget inn.");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons om at brukeren maa veere logget inn for aa oppdatere en motorvogn");
            }
            return null;
        }

        if(validering(motorvogn)){
            if(!motorvognRepository.update(id, motorvogn)){
                try {
                    response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            "Feil i DB - klarte ikke aa oppdatere en motorvogner");
                    return null;
                } catch (IOException e) {
                    logger.error("Kunne ikke oppdatere en motorvogn og sende det som respons");
                }
            }
            return motorvognRepository.getAll();
        }else{
            try {
                response.sendError(HttpStatus.NOT_ACCEPTABLE.value(),
                        "Feil input - Valideringsfeil for oppdatering av en motorvogn");
            } catch (IOException e) {
                logger.error("Kunne ikke sende respons for valideringsfeil");
            }
            return null;
        }
    }

    @GetMapping("/api/uke8/merker/")
    public String[] getMerker(){
        return merker;
    }

    @GetMapping("/api/uke8/merker/{merke}/")
    public String[] getMerker(@PathVariable("merke") String merke){
        switch (merke){
            case "Volvo":
                return new String[]{"V30","V70","V90"};
            case "Nissan":
                return new String[]{"V20","V40","V60"};
            case "Tesla":
                return new String[]{"Model s", "Standard"};
            default:
                return null;
        }
    }
}