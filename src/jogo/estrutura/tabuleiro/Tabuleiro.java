package jogo.estrutura.tabuleiro;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static jogo.utils.Constantes.TAMANHO_COLUNA_TABULEIRO;
import static jogo.utils.Constantes.TAMANHO_LINHA_TABULEIRO;

public class Tabuleiro {

    private final Casa[][] casas = new Casa[TAMANHO_LINHA_TABULEIRO][TAMANHO_COLUNA_TABULEIRO];

    public Tabuleiro() {
        prepararTabuleiro();
    }

    private void prepararTabuleiro() {
        IntStream.range(0, TAMANHO_LINHA_TABULEIRO)
                 .forEach(linha -> IntStream.range(0, TAMANHO_COLUNA_TABULEIRO)
                                                .forEach(coluna -> {
                                                    var corCasa = ((linha + coluna) % 2 == 0) ? Cor.BRANCO : Cor.PRETO;
                                                    casas[linha][coluna] = new Casa(corCasa);
                                                })
        );
    }

    public void imprimirTabuleiro() {

        IO.println();
        IO.println(construirLetras());

        IntStream.range(0, TAMANHO_LINHA_TABULEIRO).forEach(linha -> {
            var numeroLinha = TAMANHO_LINHA_TABULEIRO - linha;

            var casasLinha = Arrays.stream(casas[linha])
                                   .map(Casa::montarCasa)
                                   .collect(Collectors.joining());

            IO.println(numeroLinha + " " + casasLinha + " " + numeroLinha);
        });

        IO.println(construirLetras());
        IO.println();
    }

    private String construirLetras() {
        return "  " + IntStream.rangeClosed('A', 'H')
                               .mapToObj(character -> " " + (char) character + " ")
                               .collect(Collectors.joining());
    }

}
