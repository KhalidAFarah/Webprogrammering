package com.oslomet.webprogrammering.uke9;

class Node{
    private int value;
    public Node nesteNode;

    public Node(int value){
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }public void setValue(int value) {
        this.value = value;
    }
}

public class LenketListe {
    private Node hode;
    private Node hale;

    public void leggTilNode(int value){
        Node ny = new Node(value);
        if(hode == null){
            hode = ny;
        }else{
            hale.nesteNode = ny;
        }

        hale = ny;
        hale.nesteNode = hode;

    }

    public boolean slettNode(int value){
        Node tmp = hode;
        Node forrigeNode = tmp;
        do {
            if(tmp.getValue() == value){
                if(tmp == hode){//first node
                    hode = hode.nesteNode;
                    hale.nesteNode = hode;
                }else if(tmp == hale){
                    forrigeNode.nesteNode = null;
                    hale = forrigeNode;
                    hale.nesteNode = hode;
                }else{
                    forrigeNode.nesteNode= tmp.nesteNode;
                }


                return true;
            }
            forrigeNode = tmp;
            tmp = tmp.nesteNode;

        }while(tmp != hode);
        return false;
    }

    public boolean inneholderNode(int value){
        Node tmp = hode;

        do {
            if(tmp.getValue() == value)
                return true;
            tmp = tmp.nesteNode;
        }while(tmp != hode);
        return false;
    }


    public String skrivUtListe(){
        Node tmp = hode;
        String ut = "";

        do{
            ut += tmp.getValue() + ",";
            tmp = tmp.nesteNode;
        }while(tmp != hode);
        return ut;
    }
    public static void main(String[] args){
        LenketListe liste = new LenketListe();

        liste.leggTilNode(5);
        liste.leggTilNode(10);
        liste.leggTilNode(15);
        liste.leggTilNode(20);
        liste.leggTilNode(25);

        System.out.println("Inneholder 5: " + liste.inneholderNode(5));
        System.out.println("Inneholder 15: " + liste.inneholderNode(15));
        System.out.println("Inneholder 25: " + liste.inneholderNode(25));
        System.out.println("Inneholder 30: " + liste.inneholderNode(30));

        System.out.println(liste.skrivUtListe());

        System.out.println("\n\nSletter tallet 5");
        liste.slettNode(25);
        System.out.println(liste.skrivUtListe());
        //liste.leggTilNode(5);

        System.out.println("\n\nSletter tallet 15");
        liste.slettNode(15);
        System.out.println(liste.skrivUtListe());
        //liste.leggTilNode(15);

        System.out.println("\n\nSletter tallet 25");
        liste.slettNode(25);
        System.out.println(liste.skrivUtListe());
        //liste.leggTilNode(25);


        System.out.println("\n\nSletter tallet 30");
        liste.slettNode(30);
        System.out.println(liste.skrivUtListe());
        //liste.leggTilNode(30);

    }
}
