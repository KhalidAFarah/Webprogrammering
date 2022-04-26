package com.oslomet.webprogrammering.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HeiController {
    final List<Kunde> kunder = new ArrayList<>();

    @GetMapping("/demo/navn/")
    public String hei(String navn){
        int antallBokstaver = navn.length();
        return "Hei verden " + navn + ", navnet ditt har " + antallBokstaver + " bokstaver";
    }

}
