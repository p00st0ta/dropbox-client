package net.poostota.dropbox;

import com.dropbox.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by pOOstOta on 25.05.2016.
 */
public class Auth extends AbstractImplementationClass {

    private String APP_KEY;
    private String APP_SECRET;

    public Auth(String APP_KEY, String APP_SECRET) throws IOException {

        this.APP_KEY = APP_KEY;
        this.APP_SECRET = APP_SECRET;
    }

    public void getAuth() throws IOException {

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY,APP_SECRET);
        DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("challenge", Locale.getDefault().toString());
        DbxWebAuthNoRedirect dbxWebAuth = new DbxWebAuthNoRedirect(dbxRequestConfig,appInfo);
        String auth = dbxWebAuth.start();

        System.out.println("\n1. Go to: " + auth
                           +"\n2. Click \"Allow\" (you might have to log in first)"
                           +"\n3. Copy the authorization code and paste it here:");

        while (true){

            String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();

            try {
                DbxAuthFinish authFinish = dbxWebAuth.finish(code);
                String accessToken = authFinish.getAccessToken();
                System.out.println("\nYou access token:\n" + accessToken);
                break;
            } catch (NetworkIOException e){
                System.out.println("\nplease, check your connection, then try again\n");
            } catch (BadRequestException e) {
                System.out.println("\nplease, check your authorization code, then try again\n");
            } catch (DbxException e) {
                e.printStackTrace();
            }
        }
    }
}
