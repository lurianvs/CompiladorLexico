package compilador.mensagem;

import java.awt.Color;

public class MensagemDeSucesso implements Mensagem{

    @Override
    public String getMensagem() {
        return "Compilado com sucesso.";
    }

    @Override
    public Color getColor() {
        return new Color(0, 100, 0);
    }
}
