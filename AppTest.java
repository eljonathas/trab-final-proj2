import java.util.List;
import java.util.Random;
import algorithms.BMH;
import algorithms.ShiftAnd;
import algorithms.ShiftAndAprox;

public class AppTest {
    public static void realizarExperimentos() {
        // Definir tamanhos diferentes de texto e padrão
        int[] tamanhosTexto = {1000, 10000, 100000};
        int[] tamanhosPadrao = {1, 10, 100};

        for (int tamanhoTexto : tamanhosTexto) {
            for (int tamanhoPadrao : tamanhosPadrao) {
                // Gerar texto e padrão aleatórios com os tamanhos definidos
                String texto = gerarTextoAleatorio(tamanhoTexto);
                String padrao = gerarPadraoAleatorio(tamanhoPadrao);

                // Executar os algoritmos de busca e registrar o tempo de execução
                long inicio, fim;
                List<Integer> ocorrencias;
                double tempoBMH = 0.0;
                double tempoShiftAnd = 0.0;
                double tempoShiftAndAprox = 0.0;
                int numExecucoes = 10;

                for (int i = 0; i < numExecucoes; i++) {
                    inicio = System.nanoTime();
                    ocorrencias = BMH.run(texto, padrao);
                    fim = System.nanoTime();
                    tempoBMH += (fim - inicio) / 1e6; // Converter para milissegundos

                    inicio = System.nanoTime();
                    ocorrencias = ShiftAnd.run(texto, padrao);
                    fim = System.nanoTime();
                    tempoShiftAnd += (fim - inicio) / 1e6; // Converter para milissegundos

                    inicio = System.nanoTime();
                    ocorrencias = ShiftAndAprox.run(texto, padrao);
                    fim = System.nanoTime();
                    tempoShiftAndAprox += (fim - inicio) / 1e6; // Converter para milissegundos
                }

                // Calcular a média dos tempos de execução
                tempoBMH /= numExecucoes;
                tempoShiftAnd /= numExecucoes;
                tempoShiftAndAprox /= numExecucoes;

                // Imprimir resultados
                System.out.println("Experimento para texto de tamanho " + tamanhoTexto + " e padrão de tamanho " + tamanhoPadrao + ":");
                System.out.println("Tempo médio (BMH): " + tempoBMH + " ms");
                System.out.println("Tempo médio (Shift-And): " + tempoShiftAnd + " ms");
                System.out.println("Tempo médio (Shift-And Aproximado): " + tempoShiftAndAprox + " ms");
                System.out.println();
            }
        }
    }

    // Método auxiliar para gerar texto aleatório
    public static String gerarTextoAleatorio(int tamanho) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    // Método auxiliar para gerar padrão aleatório
    public static String gerarPadraoAleatorio(int tamanho) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        realizarExperimentos();
    }
}
