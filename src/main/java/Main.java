import com.dropbox.core.DbxException;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 05.05.2016.
 */
public class Main {


    public static void main(String[] args) throws IOException, DbxException, ParseException {

        Main main = new Main();
        main.command(args);
    }

    private void command(String[] args) throws IOException, ParseException, DbxException {

        Action action = new Action();

        if (args.length == 0){

            helper();

        } else if (args[0].toLowerCase().equals("auth") && args.length == 3){

            Auth auth = new Auth(args[1], args[2]);
            action.makeCommand(new AuthCommand(auth));

        } else if (args[0].toLowerCase().equals("info") && args.length > 1 && args.length < 4) {

            Info info;
            if (args.length == 3){
                info = new Info(args[1], args[2]);
            } else {
                info = new Info(args[1]);
            }
            action.makeCommand(new InfoCommand(info));

        } else if (args[0].toLowerCase().equals("list") && args.length > 2 && args.length < 5){

            List list;
            if (args.length == 4){
                list = new List(args[1], args[2], args[3]);
            } else {
                list = new List(args[1], args[2]);
            }
            action.makeCommand(new ListCommand(list));

        } else {

            helper();

        }

    }

    private void helper() throws IOException {

        System.out.println("\n Dropbox client supports following commands:\n"
                           +"\"auth\" with syntax: auth appKey appSecret\n"
                           +"\"info\" with syntax: info accessToken locale(optional)\n"
                           +"\"list\" with syntax: list accessToken path locale(optional)\n");

    }
}
