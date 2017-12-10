package joshua.dMail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

/**
 * Created by Josh on 12/1/2016.
 */
public class SettingsActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Settingsfragment())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
