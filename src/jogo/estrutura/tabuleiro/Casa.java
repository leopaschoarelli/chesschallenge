package jogo.estrutura.tabuleiro;

import static jogo.utils.Constantes.*;

public class Casa {

    private Cor cor;

    public Casa(Cor cor) {
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public String montarCasa() {
        String corFundo = (cor == Cor.BRANCO) ? FUNDO_BRANCO : FUNDO_PRETO;

        return corFundo + "   " + RESETAR;
    }

}
