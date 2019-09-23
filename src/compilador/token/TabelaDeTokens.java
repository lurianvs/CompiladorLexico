package compilador.token;

import compilador.Erros.Leitor.Posicao;
import java.util.HashMap;

public class TabelaDeTokens {
    private final HashMap<String, Integer> tabela;
    
    public TabelaDeTokens() {
        tabela = new HashMap<>();
        tabela.put("PROGRAM", 1);
        tabela.put("LABEL   ", 2);
        tabela.put("CONST", 3);
        tabela.put("VAR", 4);
        tabela.put("PEOCEDURE", 5);
        tabela.put("BEGIN", 6);
        tabela.put("END", 7);
        tabela.put("INTEGER", 8);
        tabela.put("ARRAY", 9);
        tabela.put("OF", 10);
        tabela.put("CALL", 11);
        tabela.put("GOTO", 12);
        tabela.put("IF", 13);
        tabela.put("THEN", 14);
        tabela.put("ELSE", 15);
        tabela.put("WHILE", 16);
        tabela.put("DO", 17);
        tabela.put("REPEAT", 18);
        tabela.put("UNTIL", 19);
        tabela.put("READLN", 20);
        tabela.put("WRITELN", 21);
        tabela.put("OR", 22);
        tabela.put("AND", 23);
        tabela.put("NOT", 24);
        tabela.put("Identificador", 25);
        tabela.put("Inteiro", 26);
        tabela.put("FOR", 27);
        tabela.put("TO", 28);
        tabela.put("CASE", 29);
        tabela.put("+", 30);
        tabela.put("-", 31);
        tabela.put("*", 32);
        tabela.put("/", 33);
        tabela.put("[", 34);
        tabela.put("]", 35);
        tabela.put("(", 36);
        tabela.put(")", 37);
        tabela.put(":=", 38);
        tabela.put(":", 39);
        tabela.put("=", 40);
        tabela.put(">", 41);
        tabela.put(">=", 42);
        tabela.put("<", 43);
        tabela.put("<=", 44);
        tabela.put("<>", 45);
        tabela.put(",", 46);
        tabela.put(";", 47);
        tabela.put("Literal", 48);
        tabela.put(".", 49);
        tabela.put("..", 50);
        tabela.put("$", 51);  
    }

    public int getCodigo(String palavra){
            return tabela.get(palavra);
    }
    
    public Token getIdentificador(String palavra, Posicao posicao) {
        
        if (tabela.containsKey(palavra)) {
            return new Token(tabela.get(palavra), palavra, posicao);
        } else {
            return new Token(tabela.get("Identificador"), palavra, posicao);
        }
    }
}
