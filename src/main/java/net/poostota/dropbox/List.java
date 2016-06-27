package net.poostota.dropbox;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.poostota.dropbox.factorypattern.AbstractImplementationClass;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * Created by pOOstOta on 26.05.2016.
 */
public class List extends AbstractImplementationClass {

    private String strUrl;
    private String token;

    public List(String token, String path) throws IOException {
        this.token = token;
        strUrl = "https://api.dropboxapi.com/1/metadata/auto" + path + "/?locale=" + Locale.getDefault().toString();
    }


    public List(String token, String path, String local) throws IOException {
        this.token = token;
        strUrl = "https://api.dropboxapi.com/1/metadata/auto" + path + "/?locale=" + local;
    }


    public void getList() throws IOException, ParseException {

        URL url = new URL(strUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode;

        try {
            responseCode = connection.getResponseCode();
        } catch (UnknownHostException e){
            System.out.println("\nUnable to make a connection, check connection then try again");
            return;
        }


        if (responseCode == 400){
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String res = reader.readLine();
            System.out.println("\nfield \"local\" was not expected\n" + res);
            return;
        } else if (responseCode == 401){
            System.out.println("\nbad or expired access_token, you should re-authenticate");
            return;
        } else if (responseCode == 404){
            System.out.println("\nfile not found, please try again");
            return;
        }

        BufferedReader buffer = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String response = buffer.readLine();
        listParse(response);
    }


    private void listParse(String response) throws ParseException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = objectMapper.readValue(response, JsonNode.class);

        JsonNode isDir = node.get("is_dir");
        String dir = isDir.asText();

        if (dir.equals("true")){

            String str = parseDir(node);
            System.out.println("\n"+str);

        } else {

            String str = parseFile(node);
            System.out.println("\n"+str);
            return;
        }

        JsonNode jContents = node.get("contents");

        if (jContents.size() == 0) return;

        for (JsonNode nod : jContents){

            if (nod.get("is_dir").toString().equals("true")){
                System.out.println(parseDir(nod).replaceFirst(node.get("path").asText(), " - "));
            } else {
                System.out.println(parseFile(nod).replaceFirst(node.get("path").asText(), " - "));
            }
        }

    }


    private String parseDir(JsonNode node){

        JsonNode jPath = node.get("path");
        String path = jPath.asText();
        String modified;

        if (!path.equals("/")){
            JsonNode jModified = node.get("modified");
           modified  = jModified.asText().replace(" +0000", "");
        } else {
            modified = "";
        }

        return (path + " : " + "dir, modified at: " + modified);
    }


    private String parseFile(JsonNode node){

        JsonNode jFilePath = node.get("path");
        String  filePath = jFilePath.asText();

        JsonNode jFileSize = node.get("size");
        String fileSize = jFileSize.asText();

        JsonNode jFileMime = node.get("mime_type");
        String fileMime = jFileMime.asText();

        JsonNode jFileModified = node.get("modified");
        String fileModified = jFileModified.asText().replace(" +0000", "");

        return  filePath + " : " + "file, " + fileSize + ", " + fileMime + ", modified at: " + fileModified;
    }
}
