package app.people.ua.peoplegroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {
    FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        //Проверяем пользователя на наличие в базе!
        if (firebaseUser != null){
            Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Splash.this,Main.class);
            startActivity(intent);
            finish();
        }
    }
}
