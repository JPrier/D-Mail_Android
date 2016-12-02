package joshua.buttonpractice;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Set;

/**
 * Created by Josh on 12/1/2016.
 */
public class SettingsActivity extends Activity{


    public static final String SET_NAME = "EmailSettingsFile";

    protected static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        settings = getSharedPreferences(SET_NAME, 0);

        final EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SettingsActivity.settings.edit().putString("toEmail", emailEdit.getText().toString()).commit();
            }
        });
    }
}
