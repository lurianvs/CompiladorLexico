package compilador.Erros;

import compilador.token.TabelaDeTokens;
import compilador.token.Token;
import java.util.Stack;

public class ErroLexico {

    private final Leitor leitor;
    private final Stack<Token> bufferTokens;
    private final TabelaDeTokens tabelaDeTokens;

    public ErroLexico(Leitor leitor) {
        this.leitor = leitor;
        this.bufferTokens = new Stack<>();
        this.tabelaDeTokens = new TabelaDeTokens();
    }

    public Stack<Token> geTokens() throws Lexico {
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            ///System.out.println("atual:"+caracter);
            if (Character.isWhitespace(caracter)) {
                continue;
            }
            if (Character.isLetter(caracter) || caracter == '_') {
                leitor.rollBack();
                bufferTokens.add(lerIdentificador());
            } else if (Character.isDigit(caracter)) {
                leitor.rollBack();
                bufferTokens.add(lerDigito());
            } else if (caracter.toString().matches("<|>|=")) {
                leitor.rollBack();
                bufferTokens.add(lerOperadorRelacional());
            } else if (caracter.toString().matches("$|;|,|\\.")) {
                leitor.rollBack();
                bufferTokens.add(lerCaracteresEspeciais());
            } else if (caracter == '\'') {
                bufferTokens.add(lerLiteral());
            } else if (caracter == ':') {
                leitor.rollBack();
                bufferTokens.add(lerAtribuidor());
            } else if (caracter.toString().matches("\\+|\\*|-|/")) {
                bufferTokens.add(new Token(tabelaDeTokens.getCodigo(caracter.toString()), caracter.toString(), leitor.getPosicao()));
            } else if (caracter.toString().matches("\\(|\\)|\\[|\\]")) {
                leitor.rollBack();
                lerCaracteresDeAberturaOuFechamento();
            } else {
                throw new Lexico(leitor.getPosicao(), "Caracter desconhecido '" + caracter + "'.");
            }
        }
        return bufferTokens;
    }

    private Token lerIdentificador() throws Lexico {
        String montaToken = "";
        while (leitor.hasNext()) {
            if (montaToken.length() > 30) {
                throw new Lexico(leitor.getPosicao(), "Valor maior do que o permitido.");
            }
            Character caracter = leitor.proximoCaracter();
            if (!Character.isLetterOrDigit(caracter) && caracter != '_') {
                leitor.rollBack();
                break;
            }
            montaToken += caracter;
            
        }
        
        //System.out.println(""+montaToken);
        return tabelaDeTokens.getIdentificador(montaToken.toUpperCase(), leitor.getPosicao());
    }

    private Token lerDigito() throws Lexico {
        String montaToken = "";
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (Character.isLetter(caracter) || caracter == '_') {
                throw new Lexico(leitor.getPosicao(), "Caracter inválido '" + caracter + "'.");
            }
            if (!Character.isDigit(caracter)) {
                leitor.rollBack();
                break;
            }
            montaToken += caracter;
            //Trata se o numero for maior que a quantidade permitida...
            int valorInteiro = Integer.parseInt(montaToken);
            if (valorInteiro > 32767) {
                throw new Lexico(leitor.getPosicao(), "Valor maior do que o permitido: 32767.");
            } else if (valorInteiro < -32767){
                throw new Lexico(leitor.getPosicao(), "Não é possivel ultilizar valores negativos.");
            }
        }
        return new Token(tabelaDeTokens.getCodigo("Inteiro"), montaToken, leitor.getPosicao());
    }

    private Token lerOperadorRelacional() {
        Character caracter = leitor.proximoCaracter();
        String montaToken = caracter.toString();
        if (caracter == '>' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '=') {
                montaToken += caracter;
            } else {
                leitor.rollBack();
            }
        } else if (caracter == '<' && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            switch (caracter) {
                case '=':
                    montaToken += caracter;
                    break;
                case '>':
                    montaToken += caracter;
                    break;
                default:
                    leitor.rollBack();
                    break;
            }
        }
        return new Token(tabelaDeTokens.getCodigo(montaToken), montaToken, leitor.getPosicao());
    }

    private Token lerCaracteresEspeciais() {
        Character caracter = leitor.proximoCaracter();
        String montaToken = caracter.toString();
        if (!caracter.toString().matches("$|;|,") && leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (caracter == '.') {
                montaToken += caracter;
            } else {
                leitor.rollBack();
            }
        }
        return new Token(tabelaDeTokens.getCodigo(montaToken), montaToken, leitor.getPosicao());
    }

    private Token lerLiteral() throws Lexico {
        String montaToken = "";
        Character caracter = null;
        while (leitor.hasNext()) {
            caracter = leitor.proximoCaracter();
            if (montaToken.length() > 255) {
                throw new Lexico(leitor.getPosicao(), "Quantidade de caracteres superior ao permitido '255'.");
            }
            if (caracter == '\'') {
                break;
            }
            montaToken += caracter;
        }
        if (caracter != '\'') {
            throw new Lexico(leitor.getPosicao(), "Esperado caracter de fechamento '\''.");
        }
        return new Token(tabelaDeTokens.getCodigo("Literal"), montaToken, leitor.getPosicao());
    }

    private Token lerAtribuidor() {
        String montaToken = leitor.proximoCaracter().toString();
        Character caracter = leitor.proximoCaracter();
        if (caracter != '=') {
            leitor.rollBack();
        } else {
            montaToken += caracter;
        }
        return new Token(tabelaDeTokens.getCodigo(montaToken), montaToken, leitor.getPosicao());
    }

    private void lerCaracteresDeAberturaOuFechamento() throws Lexico {
        String montaToken = leitor.proximoCaracter().toString();
        if (leitor.proximoCaracter() == '*') {
            lerComentario();
            return;
        }
        bufferTokens.push(new Token(tabelaDeTokens.getCodigo(montaToken), montaToken, leitor.getPosicao()));
        leitor.rollBack();
    }

    private void lerComentario() throws Lexico {
        boolean isFimDoComentario = false;
        while (leitor.hasNext()) {
            Character caracter = leitor.proximoCaracter();
            if (caracter == '*') {
                if (leitor.proximoCaracter() == ')') {
                    isFimDoComentario = true;
                    break;
                }
                leitor.rollBack();
            }
        }
        if (!isFimDoComentario) {
            throw new Lexico(leitor.getPosicao(), "É necessário encerrar o comentário.");
        }
    }
}
