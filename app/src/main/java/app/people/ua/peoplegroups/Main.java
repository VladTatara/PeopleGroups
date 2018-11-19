package app.people.ua.peoplegroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    TextView username_on_main;

    public Toolbar toolbar;
    public NavigationView nav_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(this);

        username_on_main = findViewById(R.id.username_on_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_info user = dataSnapshot.getValue(user_info.class);

                username_on_main.setText(user.getUsername());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    //Метод для определения меню в верхнем левом углу Toolbar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    //Метод для обработки элементов меню.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {


        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Upload_image) {
            startActivity(new Intent(Main.this, Upload_image.class));
        }
        else if(id==R.id.chat){
            startActivity(new Intent(Main.this,Chat.class));

        }else if(id==R.id.galerey){
            startActivity(new Intent(Main.this,Galerey.class));
        }else if (id==R.id.Log_out){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Main.this, Start.class));
            finish();
            return true;
        }
        return true;
    }
}
