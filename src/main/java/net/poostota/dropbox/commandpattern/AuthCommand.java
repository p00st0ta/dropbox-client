package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class AuthCommand implements Command {


    private AbstractImplementationClass auth;


    public AuthCommand(AbstractImplementationClass auth){

        this.auth =  auth;
    }

    public void execute() throws IOException, ParseException {

        auth.runTheCommand();

    }
}
