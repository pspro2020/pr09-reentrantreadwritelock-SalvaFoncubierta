package com.company;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Almacen {

    private ArrayList<Integer> lista = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public ArrayList<Integer> getLista() {
        return lista;
    }

    public void getProduct(int consulta) throws InterruptedException {
        readLock.lock();
        try {
            consultProduct(consulta);
        } finally {
            readLock.unlock();
        }
    }

    private void consultProduct(int consulta) throws InterruptedException {
        int contador = 0;
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i) == consulta + 1){
                contador++;
            }
        }
        Thread.sleep(500);
        System.out.printf("%s: el número %d aparece %d veces %s\n",Thread.currentThread().getName(), consulta + 1,
                contador, LocalTime.now().format(dateTimeFormatter));
    }

    public void addProduct (int product) throws InterruptedException {
        writeLock.lock();
        try {
            lista.add(product);
            System.out.printf("Se ha añadido el producto %d a la lista %s\n",
                                product, LocalTime.now().format(dateTimeFormatter));
        } finally {
            writeLock.unlock();
        }
    }

}
