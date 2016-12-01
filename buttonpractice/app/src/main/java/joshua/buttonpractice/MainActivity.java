package joshua.buttonpractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button);
        final EditText mEdit = (EditText) findViewById(R.id.mEdit);
        btn.setOnClickListener(new View.OnClickListener(){

                                   @Override
                              public void onClick(View v) {

                                       Intent emailIntent = new Intent(Intent.ACTION_SEND);

                                       emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"joshkprier@gmail.com"};
                                       emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Doc App");
                                       emailIntent.putExtra(Intent.EXTRA_TEXT, mEdit.getText().toString());

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
}
