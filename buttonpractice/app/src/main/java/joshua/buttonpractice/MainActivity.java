package joshua.buttonpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static MainActivity a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        final EditText mrnEdit = (EditText) findViewById(R.id.mrnEdit);
        final EditText acctEdit = (EditText) findViewById(R.id.acctEdit);
        btn.setOnClickListener(new View.OnClickListener(){

                                   @Override
                              public void onClick(View v) {
                                       Calendar c = Calendar.getInstance();
                                       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
                                       String strDate = sdf.format(c.getTime());

                                       String emailBody = "MRN: " + mrnEdit.getText().toString() +
                                               "\n\nAcct Number: " + acctEdit.getText().toString() +
                                               "\n\nDate: " + strDate;

                                       Intent emailIntent = new Intent(Intent.ACTION_SEND);

                                       emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"joshkprier@gmail.com"});
                                       emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Doc App");
                                       emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

                                       emailIntent.setType("message/rfc822");
                                       try {
                                           startActivity(Intent.createChooser(emailIntent, "send mail..."));
                                           finish();

                                       } catch (android.content.ActivityNotFoundException ex) {
                                           Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                                       }

                                   }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                a = this;
                Intent i = new Intent(a, SettingsActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
