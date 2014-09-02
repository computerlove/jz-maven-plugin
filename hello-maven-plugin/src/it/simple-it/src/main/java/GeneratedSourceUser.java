import java.io.IOException;

public class GeneratedSourceUser {

    public GeneratedSourceUser() {
        try(Generated generated = new Generated()){
            System.out.println("Lalala!");
        } catch ( IOException e ) {
            System.out.println(":O");
        }
    }
}