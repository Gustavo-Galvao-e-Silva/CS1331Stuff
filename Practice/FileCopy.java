import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileCopy {
    private File file;

    public FileCopy(File file) {
        this.file = file;
    }

    public void makeCopy() {
        try {
            Scanner fileScanner = new Scanner(this.file);
            String buffer = "";
            while (fileScanner.hasNextLine()) {
                buffer += fileScanner.nextLine();
            }
            System.out.println(buffer);
            fileScanner.close();
            FileWriter fw = new FileWriter("copy_of_" + this.file.getName());
            fw.write(buffer);
            fw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not copy file, as it wasn't found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("File writing gone wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File f = new File("test.txt");
        FileCopy fc = new FileCopy(f);
        fc.makeCopy();
    }
}
