package app.people.ua.peoplegroups.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import app.people.ua.peoplegroups.ChatPerson;
import app.people.ua.peoplegroups.R;

public class MessageAdapter  extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context vContext;
    private List<ChatPerson> vChatPerson;
    private String imageurl;

    FirebaseUser quser;

    public MessageAdapter (Context vContext, List<ChatPerson> vChatPerson,String imageurl){
        this.vChatPerson = vChatPerson;
        this.vContext = vContext;
        this.imageurl = imageurl;

    }
    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(vContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(vContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {

        ChatPerson chatPerson = vChatPerson.get(position);
        holder.show_message.setText(chatPerson.getMessage());

        if (imageurl.equals("default")){
            holder.imageProfile.setImageResource(R.drawable.icons_name);
        }else {
            Glide.with(vContext).load(imageurl).into(holder.imageProfile);
        }

    }
    @Override
    public int getItemCount() {
        return vChatPerson.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView show_message;
        public ImageView imageProfile;

        ViewHolder(View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.show_message);
            imageProfile = itemView.findViewById(R.id.imageProfile);
        }
    }
    @Override
    public int getItemViewType(int position) {
        quser = FirebaseAuth.getInstance().getCurrentUser();
        if (vChatPerson.get(position).getSender().equals(quser.getUid())){
            return MSG_TYPE_RIGHT;
        }else {
            return MSG_TYPE_LEFT;
        }
    }
}
