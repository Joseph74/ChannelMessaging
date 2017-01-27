package joseph.cocherel.channelmessaging;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by cocherej on 20/01/2017.
 */
public class Downloader extends AsyncTask <Void, Void, String> implements onDownloadCompleteListener {

    public static final String PREFS_NAME = "properties";

    private ArrayList<onDownloadCompleteListener> Listeners = new ArrayList<>();
    LoginActivity login;
    ChannelActivity channel;

    protected String doInBackground(Void...params){

        login = new LoginActivity();

        if(this.login != null) {
            HashMap<String, String> postparams = new HashMap<>();
            postparams.put ("username", "cocherej");
            postparams.put ("password", "cocherejpassword");
            String response = performPostCall("http://www.raphaelbischof.fr/messaging/?function=connect", postparams);
            return response;
        }
        else if (this.channel != null){

        }
    }

    protected void onPostExecute(String s){
        super.onPostExecute(s);
        for (onDownloadCompleteListener Listener:Listeners){
            Listener.onDownloadCompleted(s);
        }
    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";
        try
        {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line= br.readLine()) != null) {
                    response +=line;
                }
            }
            else
            {
                response = "";
            }
        }
        catch
                (Exception e) {
                e.printStackTrace();
        }
        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()){
            if (first) first = false;
            else result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    public void setListeners(onDownloadCompleteListener Listeners) {
        this.Listeners.add(Listeners);
    }

    public void onDownloadCompleted(String content) {

    }
}
