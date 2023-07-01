package algorithms;

import java.util.ArrayList;
import java.util.List;

public class ShiftAnd {
    public static List<Integer> run(String texto, String padrao) {
        // Lista de ocorrências
        List<Integer> ocorrencias = new ArrayList<>();
        // Tamanho do texto e do padrão
        int n = texto.length();
        int m = padrao.length();
        // Máscara para a última posição do padrão
        int mascara = 1 << (m - 1);
        int[] R = new int[256]; // tabela de caracteres
        // Pré-processamento para construir a tabela de caracteres
        for (int i = 0; i < 256; i++) {
            // Inicializando a tabela de caracteres com o valor 1 em todos os bits
            R[i] = ~0;
        }
        // Atualizando a tabela de caracteres com o valor 0 na posição do caractere
        for (int j = 0; j < m; j++) {
            R[padrao.charAt(j)] &= ~(1 << j);
        }
        // Estado inicial
        int estado =  0;
        // Executando o algoritmo Shift-And
        for (int i = 0; i < n; i++) {
            // Aqui estamos deslocando o estado uma posição para a esquerda e
            // adicionando o valor do caractere atual na última posição
            estado = (estado << 1) | R[texto.charAt(i)];
            System.out.println("Estado: "+ Integer.toBinaryString(estado));
            System.out.println("Máscara: "+ Integer.toBinaryString(mascara));
            // Caso o estado seja igual a 0, significa que o padrão foi encontrado
            if ((estado & mascara) == 0) {
                System.out.println("Match: "+ Integer.toBinaryString(estado & mascara));
                ocorrencias.add(i - m + 1);
            }
        }
        // Retornando a lista de ocorrências
        return ocorrencias;
    }

    public static void main(String[] args) {
        String texto = "abracadabra";
        String padrao = "abra";
        List<Integer> ocorrencias = run(texto, padrao);
        System.out.println(ocorrencias);
    }
}
