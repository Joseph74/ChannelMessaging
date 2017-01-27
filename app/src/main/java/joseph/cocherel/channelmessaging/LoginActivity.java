package joseph.cocherel.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements onDownloadCompleteListener, View.OnClickListener {

    private static final String PREFS_NAME = "";
    protected String Id;
    protected String Password;
    Button ButtonValider;

    public LoginActivity(String id, String password) {
        EditText textViewId = (EditText) findViewById(R.id.txtId);
        EditText textViewPassword = (EditText) findViewById(R.id.txtPassword);
        Id = textViewId.getText().toString();
        Password = textViewPassword.getText().toString();
        ButtonValider = (Button) findViewById(R.id.buttonValider);
        ButtonValider.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v) {
        Downloader d = new Downloader();
        d.setListeners(this);
        d.execute();
    }

    public void onDownloadCompleted(String content) {
        Gson gson = new Gson();
        Response response = gson.fromJson(content, Response.class);

        if (response.getResponse().equals("Valide")) {
            Toast.makeText(getApplicationContext(), "Bien connecté", Toast.LENGTH_SHORT).show();
            SharedPreferences settings = getSharedPreferences(Downloader.PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("accesstoken", response.getAccesstoken());
            editor.commit();

            Intent myIntent = new Intent(getApplicationContext(), ChannelActivity.class);
            LoginActivity.this.startActivity(myIntent);
        }
    }
}
