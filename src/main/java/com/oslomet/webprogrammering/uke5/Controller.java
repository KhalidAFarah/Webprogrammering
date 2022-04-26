package com.oslomet.webprogrammering.uke5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {

    private final Integer[] tempArray = new Integer[]{-3,-2,2,7,12,16,18,17,12,7,3,-2};
    private ArrayList<Valuta> valutaRegister = new ArrayList<>();

    @GetMapping("/uke5/")
    public Integer temp(String maned){
        switch (maned){
            case "januar":
                return tempArray[0];
            case "februar":
                return tempArray[1];
            case "mars":
                return tempArray[2];
            case "april":
                return tempArray[3];
            case "mai":
                return tempArray[4];
            case "juni":
                return tempArray[5];
            case "juli":
                return tempArray[6];
            case "august":
                return tempArray[7];
            case "september":
                return tempArray[8];
            case "oktober":
                return tempArray[9];
            case "november":
                return tempArray[10];
            case "desember":
                return tempArray[11];
            default:
                return 0;

        }
    }

    @GetMapping("/uke5/valuta/")
    public double getTemp(Valuta valuta){
        for(int i = 0; i < valutaRegister.size(); i++){
            if(valutaRegister.get(i).getValutaSort().equals(valuta.getValutaSort())){
                return valutaRegister.get(i).getValutaKurs() * valuta.getValutaKurs();
            }
        }
        return 0;
    }
    @PostMapping("/uke5/valuta/")
    public Valuta postTemp(Valuta valuta){
        valutaRegister.add(valuta);
        return valuta;
    }

    @GetMapping("/uke5/calc/plus/")
    public String plus(String x, String y){
        try{
            int val = Integer.parseInt(x) + Integer.parseInt(y);
            return String.valueOf(val);
        }catch (Exception e){
            return "Vennlisg oppgi gyldig info for x og y";
        }
    }
    @GetMapping("/uke5/calc/minus/")
    public String minus(String x, String y){
        try{
            int val = Integer.parseInt(x) - Integer.parseInt(y);
            return String.valueOf(val);
        }catch (Exception e){
            return "Vennlisg oppgi gyldig info for x og y";
        }
    }
    @GetMapping("/uke5/calc/multi/")
    public String multi(String x, String y){
        try{
            int val = Integer.parseInt(x) * Integer.parseInt(y);
            return String.valueOf(val);
        }catch (Exception e){
            return "Vennlisg oppgi gyldig info for x og y";
        }
    }

    @GetMapping("/uke5/calc/dele/")
    public String dele(String x, String y){
        try{
            int xval =Integer.parseInt(x);
            int yval = Integer.parseInt(y);
            if(xval > 0 && yval > 0){
                int val = xval / yval;
                return String.valueOf(val);
            }
            return "y må være større enn 0";
        }catch (Exception e){
            return "Vennlisg oppgi gyldig info for x og y";
        }
    }
}
