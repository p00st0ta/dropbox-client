package net.poostota.dropbox.commandpattern;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class Action {

    public void makeCommand(Command command) throws IOException, ParseException {
        command.execute();
    }
}
