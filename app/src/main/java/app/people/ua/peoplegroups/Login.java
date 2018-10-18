package app.people.ua.peoplegroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText Email_on_login,Password_on_login;
    Button Login_on_login;
    FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        Email_on_login = (EditText)findViewById(R.id.Email_on_login);
        Password_on_login = (EditText)findViewById(R.id.Password_on_login);
        Login_on_login = (Button)findViewById(R.id.Login_on_login);

        Login_on_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = Email_on_login.getText().toString();
                String txt_password = Password_on_login.getText().toString();

                if (TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Login.this, "Все поля должны быть заполненны", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(Login.this,Main.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(Login.this, "Что то не так!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
