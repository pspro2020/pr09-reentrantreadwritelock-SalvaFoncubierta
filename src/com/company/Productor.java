package com.company;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Productor implements Runnable{

    private final Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        while (true){
            try {
                almacen.addProduct(ThreadLocalRandom.current().nextInt(3)+1);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido mientras añadía un producto a la lista.");
                return;
            }
        }
    }
}
