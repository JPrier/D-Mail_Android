package joshua.dMail;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Joshua on 12/25/2016.
 */
public class Settingsfragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
