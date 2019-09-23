package compilador.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoUtils {
    public static String ler(String path) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(path));) {
            String linha;
            StringBuilder conteudo = new StringBuilder();
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append('\n');
            }
            return conteudo.toString();
        } catch (IOException e) {
            throw new IOException("Arquivo não encontrado.");
        }
    }
    
    public static void salvar(String conteudo, String path) throws IOException{
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(conteudo);
        } catch (IOException ex) {
            throw new IOException("Não foi possível salvar o arquivo.");
        }
    }
}
