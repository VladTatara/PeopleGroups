package app.people.ua.peoplegroups;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Upload_image extends AppCompatActivity {

    Button choice_file;
    EditText enter_name_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        choice_file = (Button) findViewById(R.id.choice_file);
        enter_name_file = (EditText) findViewById(R.id.enter_name_file);
    }
}
