package com.oslomet.webprogrammering.uke11;


import java.util.*;
import java.util.Comparator;

public class MobelRegister<T extends Mobel> {
    private List<T> mobler;

    public MobelRegister(){
        mobler = new ArrayList<>();
    }

    public void addMobel(T mobel){
        mobler.add(mobel);
    }

    public T getMobel(int nummer){
        for(T mobel : mobler){
            if(mobel.getNummer() == nummer){
                return mobel;
            }
        }
        return null;
    }

    public boolean removeMobel(int nummer){
        for(T mobel : mobler){
            if(mobel.getNummer() == nummer){
                mobler.remove(nummer);
                return true;
            }
        }
        return false;
    }

    public void skrivUtAlle(){
        for(T mobel : mobler){
            System.out.println(mobel);
        }
    }

    public void sorterstorst(){
        Collections.sort(mobler);
    }
    public void sorter2(){
        Comparator comparator = new com.oslomet.webprogrammering.uke11.Comparator();
        Collections.sort(mobler, comparator);
    }

    public void sorter(java.util.Comparator comparator){
        Collections.sort(mobler, comparator);
    }

    public List<T> getMobler() {
        return mobler;
    }

    public static <T extends Mobel> void skrivUtAlleGenerisk(List<T> moblergenerisk){
        for(T mobel : moblergenerisk){
            System.out.println(mobel);
        }
    }



    public static void main(String[] args){
        MobelRegister<MobelImpl> mobelregister = new MobelRegister<>();
        mobelregister.addMobel(new MobelImpl("Sverre", "Stol", 10, 1000, 2));
        mobelregister.addMobel(new MobelImpl("Espen","Bord",30,1500,3));
        mobelregister.addMobel(new MobelImpl("BREEE","Bord",30,2200,1));

        mobelregister.skrivUtAlle();
        mobelregister.sorter2();
        mobelregister.skrivUtAlle();

        System.out.println("--------------------------");
        MobelRegister<Bord> mr = new MobelRegister<>();
        mr.addMobel(new Bord("Ice", 20, 1750, 2, 4));
        mr.addMobel(new Bord("Box",30,1500,3, 3));
        mr.addMobel(new Bord("Breeze",30,2200,1, 1));

        mr.skrivUtAlle();
        mr.sorter(new ComparatorBord());
        mr.skrivUtAlle();


        System.out.println("--------------------");

        MobelRegister<Stol> mr2 = new MobelRegister<>();
        mr2.addMobel(new Stol("Ascent", 5, 350, 2));
        mr2.addMobel(new Stol("Hedviw",3,400,3));
        mr2.addMobel(new Stol("Espen",7,500,1));


        skrivUtAlleGenerisk(mr.getMobler());
        skrivUtAlleGenerisk(mr2.getMobler());

        /**
         * Oppgave 6
         * hvis vi begrenser det til bord kan vi ikke ha objekter som Stol og MobelImpl i list en siden de ikke arver
         * fra Bord
         * */
        System.out.println("---ArrayList times---");
        ArrayList<Integer> ints = new ArrayList<>();
        long startTid = System.nanoTime();
        for(int i = 0; i < 1000000; i++){
            ints.add(i);
        }
        long sluttTid = System.nanoTime();

        long varighet = (sluttTid-startTid)/1000000; // del med 1 000 000 for å konvertere til millisekunder
        System.out.println("Tok: " + varighet + " milisekunder");

        System.out.println("---Vector times---");
        Vector<Integer> ints2 = new Vector<>();
        startTid = System.nanoTime();
        for(int i = 0; i < 1000000; i++){
            ints2.add(i);
        }
        sluttTid = System.nanoTime();

        varighet = (sluttTid-startTid)/1000000; // del med 1 000 000 for å konvertere til millisekunder
        System.out.println("Tok: " + varighet + " milisekunder");

        System.out.println("---LenketListe times---");
        LinkedList<Integer> ints3 = new LinkedList<>();
        startTid = System.nanoTime();
        for(int i = 0; i < 1000000; i++){
            ints3.add(i);
        }
        sluttTid = System.nanoTime();

        varighet = (sluttTid-startTid)/1000000; // del med 1 000 000 for å konvertere til millisekunder
        System.out.println("Tok: " + varighet + " milisekunder");
    }




}
