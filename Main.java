import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = reader.readLine();
            System.out.println(command);
            if(command.startsWith("load")){
                command = command.substring(5).trim();
                //TODO sukurti klase ir perkelti failo skaityma i ja
                BufferedReader fileReader = new BufferedReader(new FileReader(command));
                while(fileReader.ready()){
                    String currentLine = fileReader.readLine();
                    if(currentLine.isEmpty()){
                        continue;
                    }
                    System.out.println(currentLine);
                }
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("BufferedReader exception.");
            e.printStackTrace();
        }

    }
}