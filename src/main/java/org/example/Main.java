package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        final int totalListas = random.nextInt(1000) + 1;
        final int numEmLista = random.nextInt(100000) + 1;

        final int totalDeNumeros = totalListas * numEmLista;

        List<List<Integer>> listasEmLista = criarEstruturaDeListas(totalListas);
        List<Thread> threadsAtivas = popularListas(listasEmLista, numEmLista);

        // esperar threads finalizarem\acabar laço | para não dar erro no calculo
        for (Thread thread : threadsAtivas) {
            thread.join();
        }
        double somaTotal = calcularSomaTotal(listasEmLista);
        System.out.println("números totais ->" + somaTotal);

        double media = somaTotal / totalDeNumeros;
        System.out.println("media de números em todas as listas ->" + media);
    }

    /**
     * calcula total de números de todas as listas
     *
     * @param listaEmListas conjunto de listas
     * @return soma de todos os números
     */
    private static double calcularSomaTotal (List<List<Integer>> listaEmListas) {
        double somaTotal = 0;

        // Percorre cada lista dentro da lista de listas
        for (List<Integer> lista : listaEmListas) {
            // Para cada número dentro da lista, adiciona ao acumulador
            for (double numero : lista){
                somaTotal = somaTotal + numero;
            }
        }

        return somaTotal;
    }

    /**
     * cria estrutura/conjunto de listas vazias.
     *
     * @param totalListas número de listas que devem ser criadas
     * @return uma lista com a quantidade de listas (vazias)
     */
    private static List<List<Integer>> criarEstruturaDeListas (int totalListas){
        List<List<Integer>> listasEmLista = new ArrayList<>();

        // listas vazias e adiciona na lista principal
        for (int i = 0; i < totalListas; i++) {
            listasEmLista.add(new ArrayList<>());
        }

        return listasEmLista;
    }

    /**
     * Cria threads que preenchem cada lista com números random
     *
     * @param listasEmLista conjunto de listas
     * @param numEmLista quantidade de números adicionados em cada lista
     * @return lista de threads ativas que populam as listas
     */
    private static List<Thread> popularListas(List<List<Integer>> listasEmLista, int numEmLista){
        List<Thread> threadsAtivas = new ArrayList<>();

        // para cada lista, cria thread PopulaLista que gera números aleatórios
        for (List<Integer> lista : listasEmLista) {

            Thread novaThread = new PopulaLista(lista, numEmLista);

            threadsAtivas.add(novaThread);
            novaThread.start();
        }

        return threadsAtivas;
    }

}
