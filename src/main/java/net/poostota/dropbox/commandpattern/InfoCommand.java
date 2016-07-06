package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class InfoCommand implements Command {


    private AbstractImplementationClass info;


    public InfoCommand(AbstractImplementationClass info){

        this.info = info;
    }

    public void execute() throws IOException, ParseException {

        info.runTheCommand();
    }
}
