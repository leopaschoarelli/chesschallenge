package jogo.estrutura.pecas;

import jogo.estrutura.tabuleiro.Cor;

public class Dama extends Peca {

    public Dama(Cor cor) {
        super(cor);
    }

    public Boolean isMovimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        var calculoLinha = Math.abs(linhaDestino - linhaOrigem);
        var calculoColuna = Math.abs(colunaDestino - colunaOrigem);

        if ((linhaOrigem == linhaDestino) || (colunaOrigem == colunaDestino)) {
            return true;
        }

        return calculoLinha == calculoColuna;
    }

    public String getSimboloPeca() {
        return "D";
    }

}
