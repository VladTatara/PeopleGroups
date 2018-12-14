package app.people.ua.peoplegroups;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.people.ua.peoplegroups.adapter.MessageAdapter;
import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    CircleImageView imageProfile;
    TextView username_on_main;
    MessageAdapter messageAdapter;
    List<ChatPerson> vchat;

    RecyclerView recyclerView;

    FirebaseUser fuser;
    DatabaseReference reference;
    ImageButton but_send;
    EditText field_for_send_text;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);



        imageProfile = findViewById(R.id.imageProfile);
        username_on_main = findViewById(R.id.username_on_main);
        but_send = findViewById(R.id.but_send);
        field_for_send_text = findViewById(R.id.field_for_send_text);
        intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        but_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = field_for_send_text.getText().toString();
                if (msg.equals("")){
                   sendMassage(fuser.getUid(),userid ,msg );
                }
                else {
                    Toast.makeText(MessageActivity.this, "Вы не можете отправить пустое сообщение!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_info user = dataSnapshot.getValue(user_info.class);
                username_on_main.setText(user.getUsername());
                if (user.getImageURL().equals("default")){
                    imageProfile.setImageResource(R.drawable.iconmain);
                }
                else {
                    Glide.with(MessageActivity.this).load(user.getImageURL()).into(imageProfile);
                }
                field_for_send_text.setText("");
                readMessages(fuser.getUid(), userid, user.getImageURL());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendMassage (String sender,String receiver,String message){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        HashMap<String,Object> hashMap =  new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("recever", receiver);
        hashMap.put("message", message);

        reference.child("Chats").push().setValue(hashMap);

    }
    private void readMessages(final String myid, final String userid, final String imageurl){
        vchat = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("ChatsPrivate");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vchat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChatPerson chatPerson = snapshot.getValue(ChatPerson.class);
                    if (chatPerson.getReceiver().equals(myid) && chatPerson.getSender().equals(userid) ||
                            chatPerson.getReceiver().equals(userid)&& chatPerson.getSender().equals(myid)){
                     vchat.add(chatPerson);
                    }
                   messageAdapter = new MessageAdapter(MessageActivity.this,vchat,imageurl);
        recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
