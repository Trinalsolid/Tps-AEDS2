import java.io.File;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

class arquivo{
    public static void main(String[] args) {
    
        File arquivo = new File("arquivo.txt");

        try {
            arquivo.createNewFile();
            
        } catch (Exception e) {
            System.out.println("ERRO na Abertura");
        }

    } 
}
