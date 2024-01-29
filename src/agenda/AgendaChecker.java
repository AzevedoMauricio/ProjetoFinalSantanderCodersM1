package agenda;
import java.io.File;
import java.io.IOException;

public class AgendaChecker {
    public AgendaChecker(){
        File agenda = new File("resources/agenda.txt");

        try{
            agenda.createNewFile();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

}
