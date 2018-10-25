package app.people.ua.peoplegroups;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    TextView email_for_reset;
    Button reset_but;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        email_for_reset = (TextView)findViewById(R.id.email_for_reset);
        reset_but = (Button)findViewById(R.id.reset_but);

        firebaseAuth = FirebaseAuth.getInstance();
         reset_but.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email = email_for_reset.getText().toString();
                 if (email.equals("")){
                     Toast.makeText(ResetPassword.this, "Введите почту", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(ResetPassword.this, "Вам отправлено письмо", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(ResetPassword.this,Login.class));
                           }
                           else {
                               String Error = task.getException().getMessage();
                               Toast.makeText(ResetPassword.this, "Не правильный Email", Toast.LENGTH_SHORT).show();
                           }
                         }
                     });
                 }
             }
         });




    }
}
