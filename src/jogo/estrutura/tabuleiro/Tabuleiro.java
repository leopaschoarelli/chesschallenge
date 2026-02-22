package jogo.estrutura.tabuleiro;

import jogo.estrutura.pecas.Peca;
import jogo.estrutura.pecas.Dama;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static jogo.utils.Constantes.TAMANHO_COLUNA_TABULEIRO;
import static jogo.utils.Constantes.TAMANHO_LINHA_TABULEIRO;

public class Tabuleiro {

    private final Casa[][] casas = new Casa[TAMANHO_LINHA_TABULEIRO][TAMANHO_COLUNA_TABULEIRO];
    private final Map<String, Casa> posicoesCasa = new HashMap<>();

    public Tabuleiro() {
        prepararTabuleiro();
        incluirPecasPosicoesIniciais();
    }

    private void prepararTabuleiro() {
        IntStream.range(0, TAMANHO_LINHA_TABULEIRO)
                 .forEach(linha -> IntStream.range(0, TAMANHO_COLUNA_TABULEIRO)
                                                .forEach(coluna -> {
                                                    var corCasa = ((linha + coluna) % 2 == 0) ? Cor.BRANCO : Cor.PRETO;
                                                    var casa = new Casa(corCasa);
                                                    casas[linha][coluna] = casa;

                                                    var posicaoCasa = gerarPosicaoCasa(linha, coluna);
                                                    posicoesCasa.put(posicaoCasa, casa);
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

    private String gerarPosicaoCasa(int linha, int coluna) {
        var letraColuna = (char) ('A' + coluna);
        var numeroLinha = TAMANHO_LINHA_TABULEIRO - linha;

        return letraColuna + String.valueOf(numeroLinha);
    }

    private void incluirPecasPosicoesIniciais() {
        Dama damaBranca = new Dama(Cor.BRANCO);
        colocarPeca(damaBranca, linhaParaPosicao(1), colunaParaPosicao('D'));
    }

    private void colocarPeca(Peca peca, int linha, int coluna) {
        casas[linha][coluna].setPeca(peca);
    }

    private int linhaParaPosicao(int linha) {
        return TAMANHO_LINHA_TABULEIRO - linha;
    }

    private int colunaParaPosicao(char coluna) {
        return Character.toLowerCase(coluna) - 'a';
    }

    private Optional<Map.Entry<String, Casa>> buscarPecaPorIdentificador(char identificador) {
        return posicoesCasa.entrySet()
                                      .stream()
                                      .filter(posicao -> {
                                          var peca = posicao.getValue().getPeca();
                                          return Objects.nonNull(peca) &&
                                                 peca.getSimboloPeca().equalsIgnoreCase(String.valueOf(identificador));
                                        }
                                      )
                                      .findFirst();
    }

    public void moverPecaTabuleiro(String comando) {
       var identificador = comando.charAt(0);
       var destino = comando.substring(1).toUpperCase();

       var posicaoOrigemOptional = buscarPecaPorIdentificador(identificador);

       if (posicaoOrigemOptional.isEmpty()) {
           IO.println("Peça informada para movimentar não encontrada no tabuleiro.");
           return;
       }
       var posicao = posicaoOrigemOptional.get();
       var posicaoOrigem = posicao.getKey();
       var casaOrigem = posicao.getValue();

       var casaDestino = posicoesCasa.get(destino);
       if (Objects.isNull(casaDestino)) {
           IO.println("Posição de destino invalido!");
           return;
       }

       var peca = casaOrigem.getPeca();

       var linhaOrigem = linhaParaPosicao(Character.getNumericValue(posicaoOrigem.charAt(1)));
       var colunaOrigem = colunaParaPosicao(posicaoOrigem.charAt(0));

       var linhaDestino = linhaParaPosicao(Character.getNumericValue(destino.charAt(1)));
       var colunaDestino = colunaParaPosicao(destino.charAt(0));

       if (peca.isMovimentoValido(linhaOrigem, colunaOrigem, linhaDestino, colunaDestino)) {
           casaOrigem.setPeca(null);
           casaDestino.setPeca(peca);

            IO.println(peca.getClass().getSimpleName() + " na " + posicaoOrigem + " movida para " + destino);
       } else {
           IO.println("O movimento da peça " + peca.getClass().getSimpleName() + " é invalido!");
       }
    }

}
