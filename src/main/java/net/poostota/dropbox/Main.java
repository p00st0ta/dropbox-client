package net.poostota.dropbox;

import com.dropbox.core.DbxException;
import net.poostota.dropbox.commandpattern.Action;
import net.poostota.dropbox.commandpattern.AuthCommand;
import net.poostota.dropbox.commandpattern.InfoCommand;
import net.poostota.dropbox.commandpattern.ListCommand;
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
        Factory factory = new Factory(args);
        AbstractImplementationClass implementationClass = factory.getInstance();
        char command = args[0].toLowerCase().charAt(0);

        switch (command){

            case 'a':
                action.makeCommand(new AuthCommand(implementationClass));
                break;
            case 'i':
                action.makeCommand(new InfoCommand(implementationClass));
                break;
            case 'l':
                action.makeCommand(new ListCommand(implementationClass));
                break;
            default:
                helper();

        }

    }

    public static void helper(){

        System.out.println("\n Dropbox client supports following commands:\n"
        System.exit(0);

    }
}
