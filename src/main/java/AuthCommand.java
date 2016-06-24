import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class AuthCommand implements Command {

    private Auth auth;

    public AuthCommand(Auth auth){
        this.auth = auth;
    }

    public void execute() {
        try {
            auth.getAuth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
