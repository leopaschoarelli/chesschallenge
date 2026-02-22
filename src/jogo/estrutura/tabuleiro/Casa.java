package jogo.estrutura.tabuleiro;

import jogo.estrutura.pecas.Peca;

import java.util.Objects;

import static jogo.utils.Constantes.*;

public class Casa {

    private Cor cor;
    private Peca peca;

    public Casa(Cor cor) {
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public String montarCasa() {
        var corFundo = (cor == Cor.BRANCO) ? FUNDO_BRANCO : FUNDO_PRETO;

        if (Objects.nonNull(peca)) {
            var corTexto = (peca.getCor() == Cor.BRANCO) ? TEXTO_BRANCO : TEXTO_PRETO;
            return corFundo + corTexto + " " + peca.getSimboloPeca() + " " + RESETAR;
        }

        return corFundo + "   " + RESETAR;
    }

}
