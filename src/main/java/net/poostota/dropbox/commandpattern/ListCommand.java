package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.AbstractImplementationClass;
import net.poostota.dropbox.List;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class ListCommand implements Command {

    private List list;

    public ListCommand(AbstractImplementationClass list){
        this.list = (List) list;
    }

    public void execute() {
        try {
            list.getList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
