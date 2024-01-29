package agenda;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;

public class WriteAgenda {
    public WriteAgenda(){
    }

    public void write(String filePath, Contato contato){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(contato.toString());
            writer.newLine();
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void clean(String filePath){
        try{
            FileWriter writer = new FileWriter(filePath, false);
            writer.write("");
            writer.close();
        } catch (IOException err){
            err.printStackTrace();
        }
    }

}
