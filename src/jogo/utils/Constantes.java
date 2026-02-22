package jogo.utils;

public class Constantes {

    public static final int TAMANHO_LINHA_TABULEIRO = 8;
    public static final int TAMANHO_COLUNA_TABULEIRO = 8;

    public static final String CHAR_ESC = "\u001B[";
    public static final String RESETAR = CHAR_ESC+"0m";

    public static final String FUNDO_BRANCO = CHAR_ESC+"47m";
    public static final String FUNDO_PRETO  = CHAR_ESC+"44m";

    public static final String TEXTO_BRANCO = CHAR_ESC+"97m";
    public static final String TEXTO_PRETO  = CHAR_ESC+"30m";
}
