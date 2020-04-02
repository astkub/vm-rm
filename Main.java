import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        //VirtualMachine virtualMachine = new VirtualMachine();
        RM rm = new RM();
        System.out.println("Possible commands:\nload [file_name]");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = reader.readLine();
            System.out.println(command);
            if(command.startsWith("load")){
                String fileName = command.substring(5).trim();
                rm.loadProgram(fileName);
                // TODO: sukurti klase ir perkelti failo skaityma i ja
                
            }
        } catch (IOException e) {
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }

    }
}