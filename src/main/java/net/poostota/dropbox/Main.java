package net.poostota.dropbox;

import com.dropbox.core.DbxException;
import net.poostota.dropbox.commandpattern.Action;
import net.poostota.dropbox.commandpattern.AuthCommand;
import net.poostota.dropbox.commandpattern.InfoCommand;
import net.poostota.dropbox.commandpattern.ListCommand;
import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import net.poostota.dropbox.factorypattern.Factory;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 05.05.2016.
 */
public class Main {


    public static void main(String[] args) throws IOException, DbxException, ParseException {

        Main main = new Main();
        main.checkArgs(args);
        main.command(args);
    }


    private void command(String[] args) throws IOException, ParseException, DbxException {

        Action action = new Action();
        Factory factory = new Factory();
        AbstractImplementationClass implementationClass = factory.getInstance(args);

        String command = args[0];

        switch (command.toLowerCase()){

            case "auth":
                action.makeCommand(new AuthCommand(implementationClass));
                break;
            case "info":
                action.makeCommand(new InfoCommand(implementationClass));
                break;
            case "list":
                action.makeCommand(new ListCommand(implementationClass));
                break;
            default:
                helper();
        }

    }

    private void checkArgs(String[] args) {

        if (args.length < 2) {
            helper();
        } else if (!args[0].toLowerCase().equals("auth") &&
                   !args[0].toLowerCase().equals("info") &&
                   !args[0].toLowerCase().equals("list")) {
            helper();
        } else if (args[0].toLowerCase().equals("auth") && args.length != 3) {
            helper();
        } else if (args[0].toLowerCase().equals("info") && args.length > 3) {
            helper();
        } else if (args[0].toLowerCase().equals("list") && args.length < 3 || args.length > 4) {
            helper();
        }
    }

    private void helper(){

        System.out.println("\n Dropbox client supports following commands:\n"
                          +"\"auth\" with syntax: auth appKey appSecret\n"
                          +"\"info\" with syntax: info accessToken locale(optional)\n"
                          +"\"list\" with syntax: list accessToken path locale(optional)\n");
        System.exit(0);

    }
}
