package agenda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String menu =
        """
        ##################
        ##### AGENDA #####
        ##################
        >>>> Menu <<<<
        1 - Adicionar Contato
        2 - Remover Contato
        3 - Editar
        4 - Sair
        """;

        Agenda Agenda = new Agenda();

        new AgendaChecker();

        Scanner scan = new Scanner(System.in);

        int choice;

        do{
            System.out.println(menu);
            choice = scan.nextInt();

            if(choice == 1){
                Agenda.addContato();
            }else if(choice == 2){
                Agenda.removeContato();
            }else if(choice == 3){
                Agenda.updateContato();
            }else if(choice == 4){
                System.out.println("Saindo... Obrigado por utilizar a agenda! =D");
            }else{
                System.out.println("Opção inválida. Digite outro número!");
            }
        } while(choice != 4);
    }
}