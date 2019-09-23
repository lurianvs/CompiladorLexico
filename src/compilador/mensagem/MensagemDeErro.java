package compilador.mensagem;

import java.awt.Color;

public class MensagemDeErro implements Mensagem{
    private final String mensagem;

    public MensagemDeErro(String mensagem) {
        this.mensagem = mensagem;
    }
    
    @Override
    public String getMensagem() {
        return mensagem;
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}
