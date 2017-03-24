package joseph.cocherel.channelmessaging;

import joseph.cocherel.channelmessaging.Message;
import joseph.cocherel.channelmessaging.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ArrayAdaptaterMessage extends ArrayAdapter<Message> {
    private final Context context;
    private List<Message> listmessage;
    //private final Channel[] values;

    public ArrayAdaptaterMessage(Context context, List<Message> listMessage) {
        super(context, R.layout.activity_message, listMessage);
        this.context = context;
        this.listmessage = listmessage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_channel, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.lblTitre);
        textView.setText(this.getItem(position).getMessage());
        return rowView;
    }
}
