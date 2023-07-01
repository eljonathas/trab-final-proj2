package algorithms;

import java.util.ArrayList;
import java.util.List;

public class BMH {
    public static List<Integer> run(String text, String pattern) {
        // Recebe o texto e o padrão a ser buscado
        List<Integer> ocorrencias = new ArrayList<>();
        // Aqui, definimos o tamanho do texto e do padrão para poder iterar sobre eles
        int tamanhoTexto = text.length();
        int tamanhoPadrao = pattern.length();
        // Agora, definimos a tabela de deslocamento
        int[] tabelaDeslocamento = new int[256];
        // Preenchemos a tabela de deslocamento com o tamanho do padrão
        for (int i = 0; i < 256; i++) {
            tabelaDeslocamento[i] = tamanhoPadrao;
        }
        // Depois disso, reenchemos a tabela de deslocamento com o tamanho do padrão menos o índice
        for (int i = 0; i < tamanhoPadrao - 1; i++) {
            tabelaDeslocamento[pattern.charAt(i)] = tamanhoPadrao - i - 1;
        }
        // Definimos o índice como o tamanho do padrão menos 1
        int indice = tamanhoPadrao - 1;
        // Agora, iteramos sobre o texto para encontrar o padrão,
        // enquanto o índice for menor que o tamanho do texto
        while(indice < tamanhoTexto){
            // Definimos o i como o tamanho do padrão menos 1
            int i = tamanhoPadrao - 1;
            // Definimos o j como o índice
            int j = indice;
            // Enquanto o i for maior ou igual a 0 e o texto na posição j for igual ao padrão na posição i
            while(i >= 0 && text.charAt(j) == pattern.charAt(i)){
                // Decrementamos o i e o j
                i--;
                j--;
            }
            if (i == -1) {
                ocorrencias.add(j + 1);
            }
            // Incrementamos o índice com o valor da tabela de deslocamento na posição do texto no índice
            indice += tabelaDeslocamento[text.charAt(indice)];
        }
        // Retornamos as ocorrências
        return ocorrencias;
    }
}