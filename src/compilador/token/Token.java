package compilador.token;

import compilador.Erros.Leitor.Posicao;

public class Token {
    private final int codigo;
    private final String montaToken;
    private final Posicao posicao;
    
    public Token(int codigo, String montaToken, Posicao posicao) {
        this.codigo = codigo;
        this.montaToken = montaToken;
        this.posicao = posicao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getLexema() {
        return montaToken;
    }

    public int getLinha(){
        return posicao.getLinha();
    }
    
    public int getColuna(){
        return posicao.getColuna();
    }
    
    @Override
    public String toString() {
        return codigo + "|" + montaToken;
    }
}
