package algorithms;

import java.util.ArrayList;
import java.util.List;

public class ShiftAndAprox {
    public static List<Integer> run(String texto, String padrao) {
        // Cria a lista de ocorrências
        List<Integer> ocorrencias = new ArrayList<>();
        // Converte o texto e o padrão para um array de caracteres
        char[] caracteresDoTexto = texto.toCharArray();
        char[] caracteresDoPadrao = padrao.toCharArray();
        // Tamanho do padrão
        int tamanhoPadrao = caracteresDoPadrao.length;
        // Máscara para a última posição do padrão
        long[] mascara = new long[Character.MAX_VALUE + 1];
        // Estado inicial com todos os bits em 1
        long estadoInicial = ~1;
        // Caso o padrão seja vazio, retornamos uma lista vazia
        if (tamanhoPadrao == 0) {
            return ocorrencias;
        }
        // Inicializando a máscara com o valor 1 em todos os bits
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            mascara[i] = ~0;
        }
        // Atualizando a máscara com o valor 0 na posição do caractere
        for (int i = 0; i < tamanhoPadrao; i++) {
            mascara[caracteresDoPadrao[i]] &= ~(1L << i);
        }
        // Executando o algoritmo Shift-And
        for (int i = 0; i < caracteresDoTexto.length; i++) {
            // Aqui estamos deslocando o estado uma posição para a esquerda e
            // adicionando o valor do caractere atual na última posição
            estadoInicial |= mascara[caracteresDoTexto[i]];
            estadoInicial <<= 1;
            // Caso o estado seja igual a 0 e o caractere seja igual ao último caractere do padrão,
            // significa que o padrão foi encontrado
            if ((estadoInicial & (1L << tamanhoPadrao)) == 0) {
                // Adiciona o índice da ocorrência na lista de ocorrências
                ocorrencias.add(i - tamanhoPadrao + 1);
            }
        }
        // Retornando a lista de ocorrências
        return ocorrencias;
    }
}
