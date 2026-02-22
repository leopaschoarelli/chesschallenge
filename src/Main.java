import jogo.estrutura.tabuleiro.Tabuleiro;

void main () {

    var tabuleiro = new Tabuleiro();
    var scanner = new Scanner(System.in);

    while (true) {
        tabuleiro.imprimirTabuleiro();

        IO.print("Digite o movimento da peça: ");
        var comando = scanner.nextLine();
        if ("SAIR".equalsIgnoreCase(comando)) {
            break;
        }

        tabuleiro.moverPecaTabuleiro(comando);
    }


}
