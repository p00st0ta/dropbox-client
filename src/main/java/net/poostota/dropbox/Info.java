package net.poostota.dropbox;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.InvalidAccessTokenException;
import com.dropbox.core.NetworkIOException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;
import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Locale;

/**
 * Created by pOOstOta on 26.05.2016.
 */
public class Info extends AbstractImplementationClass {

    private String token;
    private String local;


    public Info(String token){
        this.token = token;
        local = Locale.getDefault().toString();
    }

    public Info(String token, String local){
        this.token = token;
        this.local = local;
    }

    public void getInfo(){

        DbxRequestConfig config = new DbxRequestConfig("DBC", local);
        DbxClientV2 clientV2 = new DbxClientV2(config, token);

        try {
            FullAccount account = clientV2.users().getCurrentAccount();
            infoParse(account);
        } catch (InvalidAccessTokenException e) {
            System.out.println("\nbad or expired access_token, you should re-authenticate");
        } catch (NetworkIOException e) {
            System.out.println("\nplease, check your connection and try again");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DbxException e) {
            e.printStackTrace();
        }
    }

    private void infoParse(FullAccount account) throws ParseException {

        String acc = account.toString();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(acc);
        JSONObject jO = (JSONObject) jsonObject.get("name");

        String account_id = (String) jsonObject.get("account_id");
        String display_name = (String) jO.get("display_name");
        String given_name = (String) jO.get("given_name");
        String surname = (String) jO.get("surname");
        String familiar_name = (String) jO.get("familiar_name");
        String e_mail = (String) jsonObject.get("email");
        String country = (String) jsonObject.get("country");
        String referral_link = (String) jsonObject.get("referral_link");

        System.out.println("\n"
                + "Account id: " + account_id + "\n"
                + "Display name: " + display_name + "\n"
                + "Name: " + given_name + " " + surname + " " + "(" + familiar_name + ")" + "\n"
                + "E-mail: " + e_mail + "\n"
                + "Country: " + country + "\n"
                + "Referral link: " + referral_link + "\n\n");
    }

}
