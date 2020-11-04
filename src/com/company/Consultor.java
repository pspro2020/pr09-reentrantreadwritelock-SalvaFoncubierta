package com.company;

public class Consultor implements Runnable{

    private final Almacen almacen;
    private final int consulta;
    private int contador = 0;

    public Consultor(Almacen almacen, int consulta) {
        this.almacen = almacen;
        this.consulta = consulta;
    }

    @Override
    public void run() {
        while (true){
            try {
                almacen.getProduct(consulta);
            } catch (InterruptedException e) {
                System.out.println("Se ha interrumpido mientras consultaba.");
                return;
            }
        }
    }
}
