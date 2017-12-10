package joshua.dMail;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Josh on 8/4/2017.
 */
public class FirstPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstantceState) {
        super.onCreate(savedInstantceState);
        setContentView(R.layout.first_page);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //initilize buttons
        Button emailBtn = (Button) findViewById(R.id.emailButton);
        Button picBtn = (Button) findViewById(R.id.pictureButton);

        //start activites on click
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartMain();
            }
        });

        picBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartPic();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent i = new Intent(this, SettingsActivity.class);
                this.startActivity(i);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void StartMain() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

    public void StartPic() {
        Intent i = new Intent(this, ProcedurePic.class);
        this.startActivity(i);
    }
}
