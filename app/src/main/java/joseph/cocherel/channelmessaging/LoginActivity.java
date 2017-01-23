package joseph.cocherel.channelmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    protected String Id;
    protected String Password;
    Button ButtonValider;

    public LoginActivity(String id, String password) {
        EditText textViewId = (EditText) findViewById (R.id.txtId);
        EditText textViewPassword = (EditText) findViewById (R.id.txtPassword);
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

    public void onClick(View v){
        Downloader d = new Downloader();
        d.setListDownload(this);
        d.execute();
    }
}
