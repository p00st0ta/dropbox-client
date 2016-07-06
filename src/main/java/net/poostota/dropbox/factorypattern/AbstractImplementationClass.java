package net.poostota.dropbox.factorypattern;

import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 27.06.2016.
 */
public interface AbstractImplementationClass {

    void runTheCommand() throws IOException, ParseException;
}
