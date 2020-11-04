package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Almacen almacen = new Almacen();
        Thread[] consultores= new Thread[3];
        Thread productor = new Thread(new Productor(almacen), "Productor");

        for (int i = 0; i < 3; i++) {
            consultores[i] = new Thread(new Consultor(almacen, i), "Consultor " + i);
        }
        productor.start();

        for (int i = 0; i < 3; i++){
            consultores[i].start();
        }

        Thread.sleep(15000);

        for (int i = 0; i < 3; i++){
            consultores[i].interrupt();
        }
        productor.interrupt();
    }
}
