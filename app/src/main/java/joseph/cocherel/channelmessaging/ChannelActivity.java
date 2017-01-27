package joseph.cocherel.channelmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by cocherej on 27/01/2017.
 */
public class ChannelActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
    }

    public void onDownloadCompleted(String content){
        Gson gson = new Gson();
        Channel channel = gson.fromJson(content, Channel.class);

        ListView listView = (ListView) findViewById(R.id.listView);

        

    }
}

