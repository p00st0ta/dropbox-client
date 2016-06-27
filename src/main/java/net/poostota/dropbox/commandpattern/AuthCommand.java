package net.poostota.dropbox.commandpattern;

import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import net.poostota.dropbox.Auth;

import java.io.IOException;

/**
 * Created by pOOstOta on 31.05.2016.
 */
public class AuthCommand implements Command {

    private Auth auth;

    public AuthCommand(AbstractImplementationClass auth){
        this.auth = (Auth) auth;
    }

    public void execute() {
        try {
            auth.getAuth();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
