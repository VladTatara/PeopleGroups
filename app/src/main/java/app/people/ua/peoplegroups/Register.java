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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText User_name_on_register,Email_on_register,Password_on_register;
    Button Register_on_register;

    FirebaseAuth Auth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText User_name_on_register = (EditText)findViewById(R.id.User_name_on_register);
        final EditText Email_on_register = (EditText)findViewById(R.id.Email_on_register);
        final EditText Password_on_register = (EditText)findViewById(R.id.Password_on_register);
        final Button Register_on_register = (Button)findViewById(R.id.Register_on_register);

        Auth = FirebaseAuth.getInstance();

        Register_on_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = User_name_on_register.getText().toString();
                String txt_email = Email_on_register.getText().toString();
                String txt_password = Password_on_register.getText().toString();

                if (TextUtils.isEmpty(txt_username)||TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Register.this,"Все поля должны быть заполнены" , Toast.LENGTH_SHORT).show();
                }else if (txt_password.length()<6){
                    Toast.makeText(Register.this,"Минимальное количество цыфр = 6 " , Toast.LENGTH_SHORT).show();
                }else {
                    Registered_New_User(txt_username,txt_email ,txt_password );
                }
            }
        });
    }
    private void Registered_New_User (final String User_name_on_register, String Email_on_register, String Password_on_register){
        Auth.createUserWithEmailAndPassword(Email_on_register,Password_on_register)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = Auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",User_name_on_register);
                            hashMap.put("imageURL","default");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(Register.this,Main.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(Register.this,"Аккаунт с такими данными уже существует" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
