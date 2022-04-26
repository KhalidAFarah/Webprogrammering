package com.oslomet.webprogrammering.uke9;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rekursjon {
    @GetMapping("/api/uke9/fakultet/") //http://localhost:8080/api/uke9/fakultet/?n=5
    public static int fakultet(int n){
        if(n > 0){
            return n * fakultet(n-1);
        }return 1;
    }

    @GetMapping("/api/uke9/rekursivsummasjon/")//http://localhost:8080/api/uke9/rekursivsummasjon/?n=5
    public static int rekursivSummasjon(int n){
        if(n > 0){
            return n + rekursivSummasjon(n-1);
        }return 0;
    }
    @GetMapping("/api/uke9/iterativsummasjon/")//http://localhost:8080/api/uke9/iterativsummasjon/?n=5
    public static int iterativSummasjon(int n){
        int sum = 0;
        for(int i = 0; i <= n; i++){
            sum += i;
        }
        return sum;
    }

    public static int binersok(int[] liste, int nokkel, int venstre, int hoyre){
        if(venstre <= hoyre){
            int mid = (venstre + hoyre) / 2;
            if(liste[mid] > nokkel){
                return binersok(liste, nokkel, venstre, mid - 1);
            }else if(liste[mid] < nokkel){
                return binersok(liste, nokkel, mid + 1, hoyre);
            }else{
                return mid;
            }
        }else{
            return -1;
        }
    }

    public static int fibonnaci(int n){
        if(n <= 1){
            return n;
        }
        return fibonnaci(n - 1) + fibonnaci(n - 2);
    }

}
