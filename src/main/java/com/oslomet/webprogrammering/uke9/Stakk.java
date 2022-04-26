package com.oslomet.webprogrammering.uke9;



public class Stakk {

    Node first;

    public void push(int value){
        if(first == null){
            first = new Node(value);
            return;
        }
        Node tmp = new Node(value);
        tmp.nesteNode = first;
        first = tmp;

    }
    public void pop(){
        if(first == null){
            return;
        }

        if(first.nesteNode != null) {
            Node tmp = first.nesteNode;
            first.nesteNode = null;
            first = tmp;
        }else {
            first = null;
        }
    }

    public String skrivUtListe(){
        Node tmp = first;
        String ut = "";

        while(tmp != null) {
            ut += tmp.getValue() + ",";
            tmp = tmp.nesteNode;
        }
        return ut;
    }

    public static void main(String[] args){
        Stakk stakk = new Stakk();


        stakk.pop();
        stakk.skrivUtListe();

        stakk.push(5);
        stakk.push(10);
        stakk.push(15);
        stakk.push(20);
        stakk.push(25);
        stakk.push(30);

        System.out.println(stakk.skrivUtListe());

        stakk.pop();
        stakk.pop();
        stakk.pop();
        System.out.println("-- " + stakk.skrivUtListe());
        stakk.pop();
        stakk.pop();
        stakk.pop();
        System.out.println("-- " + stakk.skrivUtListe());


    }


}
