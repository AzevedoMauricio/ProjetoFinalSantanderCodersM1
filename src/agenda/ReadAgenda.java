package agenda;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadAgenda {

    private List<Contato> contatos;

    public ReadAgenda(){
        this.contatos = new ArrayList<>();
    }

    public List<Contato> read(String filePath){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                Long id = Long.valueOf(partes[0].trim());

                String[] nomes = partes[1].trim().split(" ");
                String nome = nomes[0].trim();
                String sobrenome = nomes[1].trim();

                String[] contatos = partes[2].trim().split(" ");
                String ddd = contatos[0].substring(2, contatos[0].length() - 1);
                Long telefone = Long.valueOf(contatos[1].substring(0, contatos[1].length() - 1));

                this.contatos.add(new Contato(id, nome, sobrenome, new Telefone(ddd, telefone)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.contatos;
    }
}
