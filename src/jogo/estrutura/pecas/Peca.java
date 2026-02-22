package jogo.estrutura.pecas;

import jogo.estrutura.tabuleiro.Cor;

public abstract class Peca {

    private Cor cor;

    public Peca(Cor cor) {
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public abstract Boolean isMovimentoValido(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino);

    public abstract String getSimboloPeca();

}
