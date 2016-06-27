package net.poostota.dropbox.factorypattern;

import net.poostota.dropbox.Auth;
import net.poostota.dropbox.Info;
import net.poostota.dropbox.List;
import net.poostota.dropbox.Main;
import net.poostota.dropbox.factorypattern.AbstractImplementationClass;

import java.io.IOException;

/**
 * Created by pOOstOta on 27.06.2016.
 */
public class Factory {

    AbstractImplementationClass instance;


    public Factory(String[] args) throws IOException {

        if (args.length == 0){

            Main.helper();

        } else if (args[0].toLowerCase().equals("auth") && args.length == 3){

            instance = new Auth(args[1], args[2]);

        } else if (args[0].toLowerCase().equals("info") && args.length > 1 && args.length < 4) {

            if (args.length == 3){
                instance = new Info(args[1], args[2]);
            } else {
                instance = new Info(args[1]);
            }

        } else if (args[0].toLowerCase().equals("list") && args.length > 2 && args.length < 5){

            if (args.length == 4){
                instance = new List(args[1], args[2], args[3]);
            } else {
                instance = new List(args[1], args[2]);
            }

        } else {

            Main.helper();

        }

    }

    public AbstractImplementationClass getInstance(){
        return instance;
    }

}
