package org.example;

import java.util.List;
import java.util.Random;

public class PopulaLista extends Thread {
    private List<Integer> lista;
    private int tamanhoLista;

    public PopulaLista(List<Integer> lista, int tamanhoLista) {
        this.lista = lista;
        this.tamanhoLista = tamanhoLista;
    }

    @Override
    public void run() {
            Random random = new Random();

        for (int i = 0; i < tamanhoLista; i++) {
            int numAleatorio = random.nextInt(1000, 100000);
            lista.add(numAleatorio); // ou algum valor aleatório
        }
    }

}


