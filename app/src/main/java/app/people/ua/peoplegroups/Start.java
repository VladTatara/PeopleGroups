package app.people.ua.peoplegroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static app.people.ua.peoplegroups.R.layout.activity_start;

public class Start extends AppCompatActivity {
    TextView Already_have_account_on_start;
    Button Register_on_start;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //Проверяем пользователя на наличие в базе!
        if (firebaseUser != null){
            Intent intent = new Intent(Start.this,Main.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_start);
        Already_have_account_on_start = (TextView)findViewById(R.id.Already_have_account_on_start);
        Register_on_start = (Button)findViewById(R.id.Register_on_start);

        Register_on_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this,Register.class));
            }
        });
        Already_have_account_on_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Start.this,Login.class));
            }
        });

    }
}
