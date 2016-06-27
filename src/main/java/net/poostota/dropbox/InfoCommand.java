package net.poostota.dropbox;

import net.poostota.dropbox.Command;
import net.poostota.dropbox.Info;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class InfoCommand implements Command {

    private Info info;

    public InfoCommand(Info info){
        this.info = info;
    }

    public void execute() {
        info.getInfo();
    }
}
