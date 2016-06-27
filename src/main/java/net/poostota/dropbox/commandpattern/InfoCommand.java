package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import net.poostota.dropbox.Info;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class InfoCommand implements Command {

    private Info info;

    public InfoCommand(AbstractImplementationClass info){
        this.info = (Info) info;
    }

    public void execute() {
        info.getInfo();
    }
}
