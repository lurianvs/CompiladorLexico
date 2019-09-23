package compilador.controllers;

import compilador.mensagem.MensagemDeErro;
import compilador.mensagem.MensagemDeSucesso;
import compilador.Erros.Lexico;
import compilador.Erros.Leitor;
import compilador.Erros.ErroLexico;
import compilador.token.Token;
import compilador.utils.ArquivoUtils;
import compilador.view.EditorDeTexto;
import compilador.Erros.Tabela;
import compilador.view.Console;
import compilador.view.TabelaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
Esta é a classe responsável por abrir;criar;salvar documentos txt :) -nati
*/
public class EditorController implements ActionListener {

    private final EditorDeTexto editor;
    private String filePath = null;

    public EditorController(EditorDeTexto editor) {
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            verificarComando(e);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(editor, ex.getMessage());
        }
    }

    private void verificarComando(ActionEvent e) throws IOException{
        switch (e.getActionCommand()) {
            case "Novo":
                novo();
                break;
            case "Abrir":
                abrir();
                break;
            case "Salvar":
                salvar();
                break;
            case "Salvar como":
                salvarComo();
                break;
            
            case "Compilar":
                compilar();
                break;
            case "Sair":
                sair();
                break;
            default:
                break;
        }
    }

    private void novo() {
        editor.setTexto("");
        filePath = null;
    }

    private void abrir() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        filePath = fileChooser.getSelectedFile().getAbsolutePath();
        editor.setTexto(ArquivoUtils.ler(filePath));
    }

    private void salvar() throws IOException {
        if (filePath == null) {
            salvarComo();
            return;
        }
        ArquivoUtils.salvar(editor.getTexto(), filePath);
    }

    private void salvarComo() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(editor) == JFileChooser.CANCEL_OPTION) {
            return;
        }
        filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".txt";
        salvar();
    }

    

    private void compilar() {
        try {
            Stack<Token> pilha = new ErroLexico(new Leitor(editor.getTexto())).geTokens();
            editor.adicionarTabela(new TabelaView(new Tabela(pilha)));
            editor.adicionarConsole(new Console(new MensagemDeSucesso()));
        } catch (Lexico e) {
            editor.adicionarConsole(new Console(new MensagemDeErro(e.getMessage())));
        }
    }

    private void sair() {
        editor.dispose();
    }
}
