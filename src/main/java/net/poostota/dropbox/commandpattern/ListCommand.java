package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class ListCommand implements Command {


    private AbstractImplementationClass list;


    public ListCommand(AbstractImplementationClass list){

        this.list = list;
    }

    public void execute() throws IOException, ParseException {

        list.runTheCommand();

    }
}
