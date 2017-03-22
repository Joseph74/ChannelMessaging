package joseph.cocherel.channelmessaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArrayAdapterChannel extends ArrayAdapter<Channel> {
    private final Context context;
    private List<Channel> listChannel;
    //private final Channel[] values;

    public ArrayAdapterChannel(Context context, List<Channel> listChannel) {
        super(context, R.layout.activity_channel, listChannel);
        this.context = context;
        this.listChannel = listChannel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_channel, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.lblTitre);
        textView.setText(this.getItem(position).getName());
        return rowView;
    }

}