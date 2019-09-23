package compilador.Erros;

import compilador.Erros.Leitor.Posicao;

public class Lexico extends Exception {

    public Lexico() {
    }

    public Lexico(String message) {
        super(message);
    }
    
    public Lexico(Posicao posicao, String descricao){
        super("Erro léxico na " 
                + posicao.toString()
                + '\n'
                + descricao
        );
    }
}
