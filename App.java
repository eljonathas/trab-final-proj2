import java.util.List;
import algorithms.BMH;
import algorithms.ShiftAnd;
import algorithms.ShiftAndAprox;

public class App {
     public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Digite um texto: ");
        String texto = scanner.nextLine();
        System.out.print("Digite 'bmh' para busca exata com BMH, 'shift-end' para busca exata com Shift-End ou 'shift-end-aprox' para busca aproximada com Shift-End: ");
        String tipoBusca = scanner.nextLine();
        System.out.print("Digite um padrão: ");
        String padrao = scanner.nextLine();
        List<Integer> ocorrencias;
        if (tipoBusca.equalsIgnoreCase("bmh")) {
            ocorrencias = BMH.run(texto, padrao);
        } else if (tipoBusca.equalsIgnoreCase("shift-end")) {
            ocorrencias = ShiftAnd.run(texto, padrao);
        } else if (tipoBusca.equalsIgnoreCase("shift-end-aprox")) {
            ocorrencias = ShiftAndAprox.run(texto, padrao);
        } else {
            System.out.println("Tipo de busca inválido. Encerrando o programa.");
            scanner.close();
            return;
        }

        // Imprimir resultados
        if (ocorrencias.isEmpty()) {
            System.out.println("O padrão não foi encontrado no texto.");
        } else {
            System.out.println("O padrão foi encontrado nas seguintes posições:");
            for (int posicao : ocorrencias) {
                System.out.println(posicao);
            }
        }

        scanner.close();
    }
}
