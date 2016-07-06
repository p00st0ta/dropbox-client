package net.poostota.dropbox.factorypattern;

import net.poostota.dropbox.Auth;
import net.poostota.dropbox.Info;
import net.poostota.dropbox.List;

import java.io.IOException;

/**
 * Created by pOOstOta on 27.06.2016.
 */
public class Factory {


    private AbstractImplementationClass instance;


    public AbstractImplementationClass getInstance(String[] args) throws IOException {


        if (args[0].toLowerCase().equals("auth")){

            instance = new Auth(args[1], args[2]);

        } else if (args[0].toLowerCase().equals("info")) {

            if (args.length == 3){
                instance = new Info(args[1], args[2]);
            } else {
                instance = new Info(args[1]);
            }

        } else if (args[0].toLowerCase().equals("list")){

            if (args.length == 4){
                instance = new List(args[1], args[2], args[3]);
            } else {
                instance = new List(args[1], args[2]);
            }
        }

        return instance;
    }

}
