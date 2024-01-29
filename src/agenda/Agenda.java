package agenda;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    private List<Contato> contatos;
    private final String filePath = "resources/agenda.txt";
    private WriteAgenda Writer = new WriteAgenda();
    private ReadAgenda Reader = new ReadAgenda();
    Scanner scan = new Scanner(System.in);

    public Agenda(){
    }

    public Agenda(List<Contato> contatos){
        this.contatos = contatos;
    }

    public void setContatos(List<Contato> contatos){
        this.contatos = contatos;
    }

    public List<Contato> getContatos(){
        return this.contatos;
    }

    public void addContato(){
        System.out.println("Primeiro, digite o nome do contato: ");
        String nome = scan.nextLine();

        System.out.println("Agora, digite o sobrenome do contato: ");
        String sobrenome = scan.nextLine();

        System.out.println("Digite o ddd do número: ");
        String ddd = scan.nextLine();

        System.out.println("Por fim, digite o número: ");
        Long numero = scan.nextLong();

        if(isTheNumberRegistered(ddd, numero)){
            System.out.println("Esse número já está registrado para outra pessoa.");
            return;
        }

        Telefone telefone = new Telefone(ddd, numero);

        Contato contato = new Contato(getLastContatoId() + 1, nome, sobrenome, telefone);

        Writer.write(filePath, contato);

        System.out.println("Contato adicionado com sucesso");
    }

    public void loadContatos(){
        this.contatos = new ArrayList<>();
        this.contatos = Reader.read(filePath);
    }

    public Long getLastContatoId(){
        loadContatos();
        Long lastId;

        try{
            lastId = this.contatos.get(this.contatos.size() - 1).getId();
        }catch (IndexOutOfBoundsException err){
            lastId = 0L;
        }

        return lastId;
    }

    public void readContatos(){
        loadContatos();
        System.out.println(this.contatos);
    }

    public boolean isTheNumberRegistered(String ddd, Long numero){
        loadContatos();

        for(Contato contato : this.contatos){
            for (Telefone telefone : contato.getTelefones()){
                if (telefone.getDdd().equals(ddd) && telefone.getNumero().equals(numero)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isValidId(Long id){
        loadContatos();

        for(Contato contato : this.contatos){
            if(contato.getId().equals(id)){
                return true;
            }
        }

        return false;
    }

    public void removeContato(){
        System.out.println("Digite o ID do contato que você gostaria de excluir: ");
        Long id = scan.nextLong();

        if(!isValidId(id)){
            System.out.println("Id não encontrado");
            return;
        }

        Writer.clean(filePath);

        for(Contato contato : this.contatos){
            if(!(contato.getId().equals(id))){
                Writer.write(filePath, contato);
            }
        }


        System.out.println("Removido com sucesso");
    }

    public void updateContato(){
        System.out.println("Digite o ID do contato que você gostaria de atualizar: ");
        Long id = scan.nextLong();

        if(!isValidId(id)){
            System.out.println("Id não encontrado");
            return;
        }

        System.out.println(
        """
        O que você quer atualizar?
        1 - Nome
        2 - Sobrenome
        3 - DDD
        4 - Número
        """
        );
        int choice = scan.nextInt();

        if(choice == 1){
            System.out.println("Digite o novo nome: ");

            scan.nextLine();

            String nome = scan.nextLine();

            for(Contato contato : this.contatos){
                if(contato.getId().equals(id)){
                    contato.setNome(nome);
                }
            }

        }else if(choice == 2){
            System.out.println("Digite o novo sobrenome: ");

            scan.nextLine();

            String sobrenome = scan.nextLine();

            for(Contato contato : this.contatos){
                if(contato.getId().equals(id)){
                    contato.setSobreNome(sobrenome);
                }
            }
        }else if(choice == 3){
            System.out.println("Digite o novo DDD: ");

            scan.nextLine();

            String ddd = scan.nextLine();

            for(Contato contato : this.contatos){
                if(contato.getId().equals(id)){
                    Telefone telefone = contato.getTelefones().get(0);
                    telefone.setDdd(ddd);
                }
            }
        }else if(choice == 4){
            System.out.println("Digite o novo número: ");
            Long numero = scan.nextLong();

            for(Contato contato : this.contatos){
                if(contato.getId().equals(id)){
                    Telefone telefone = contato.getTelefones().get(0);
                    telefone.setNumero(numero);
                }
            }
        }else{
            System.out.println("Opção inválida");
        }

        Writer.clean(filePath);

        for(Contato contato : this.contatos){
            Writer.write(filePath, contato);
        }


        System.out.println("Dados atualizados com sucesso!");
    }
}
