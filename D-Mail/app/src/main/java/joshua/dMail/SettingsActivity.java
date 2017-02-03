package joshua.dMail;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Josh on 12/1/2016.
 */
public class SettingsActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Settingsfragment())
                .commit();
    }
}
