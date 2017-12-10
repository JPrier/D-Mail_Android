package joshua.dMail;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Josh on 8/18/2017.
 */
public class ProcedurePic extends AppCompatActivity{

    static final int REQUEST_IMAGE_CAPTURE = 1;

    String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.procedure_picture);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        /*take picture immediately
        try {
            dispatchTakePictureIntent();

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        final EditText mrnEdit = (EditText) findViewById(R.id.mrnEditPic);
        final EditText acctEdit = (EditText) findViewById(R.id.acctEditPic);
        final EditText notes = (EditText) findViewById(R.id.notesPic);
        final Button photoBtn = (Button) findViewById(R.id.photoButton);
        final Button sendBtn = (Button) findViewById(R.id.emailButton);

        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take another picture
                try {
                    dispatchTakePictureIntent();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //format email
                if (sharedPref.getString("provider", "").equals("")){
                    Toast.makeText(ProcedurePic.this, "There is no " +
                            "provider, go to settings " +
                            "to change this", Toast.LENGTH_LONG).show();
                }

                //get current date
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
                String strDate = sdf.format(c.getTime());

                //get to email and subject of email from settings
                final String to = sharedPref.getString("to_email", "");
                final String subject = sharedPref.getString("subject", "");

                //format email body
                String emailBody = "MRN: " + mrnEdit.getText().toString() +
                        "\n\nAcct Number: " + acctEdit.getText().toString();

                if (notes.getText().toString().equals("")) {
                    emailBody += "\n\nNotes: " + notes.getText().toString();
                }

                emailBody += "\n\nProvider: " +
                        sharedPref.getString("provider", "") +
                        "\n\nDate: " + strDate;

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                //Add everything into the emailIntent
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);
                if(mCurrentPhotoPath != null){
                    emailIntent.putExtra(Intent.EXTRA_STREAM,
                            Uri.parse(mCurrentPhotoPath));
                    Toast.makeText(ProcedurePic.this, "It tried  to attach" +
                            " the photo still", Toast.LENGTH_LONG).show();
                }

                emailIntent.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(emailIntent, "send mail..."));
                    finish();

                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ProcedurePic.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
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

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try{
                photoFile = createImageFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(),
                        "joshua.dMail.fileProvider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
