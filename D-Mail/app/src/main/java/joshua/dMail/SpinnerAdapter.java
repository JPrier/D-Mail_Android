package joshua.dMail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Josh on 8/18/2017.
 */
public class SpinnerAdapter extends BaseAdapter
{
    String[] options;
    Context context;

    public SpinnerAdapter(Context context)
    {
        this.context=context;
        options = context.getResources().getStringArray(R.array.con_array);

    }
    @Override
    public int getCount()
    {
        return options.length;
    }
    @Override
    public Object getItem(int arg0)
    {
        return options[arg0];
    }
    @Override
    public long getItemId(int arg0)
    {
        return arg0;
    }
    @Override
    public View getView(int pos, View view, ViewGroup parent)
    {
        LayoutInflater inflater=LayoutInflater.from(context);
        view=inflater.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        TextView txv=(TextView)view.findViewById(android.R.id.text1);
        if (pos == 0 || pos == 6) {
            txv.setBackgroundColor(context.getResources().getColor(R.color.spinner_title_color));
        }
        txv.setTextSize(20f);
        txv.setText(options[pos]);
        return view;
    }

}
