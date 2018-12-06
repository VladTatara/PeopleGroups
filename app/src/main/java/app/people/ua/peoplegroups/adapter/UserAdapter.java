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
import com.google.firebase.auth.UserInfo;

import java.util.List;

import app.people.ua.peoplegroups.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context vContext;
    private List<UserInfo> vUsers;

    public UserAdapter (Context vContext, List<UserInfo> vUsers){
        this.vUsers = vUsers;
        this.vContext = vContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(vContext).inflate(R.layout.user_item,parent,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserInfo user = vUsers.get(position);
        holder.username_on_main.setText(user.getDisplayName());
        if (user.getPhotoUrl().equals("default")){
            holder.imageProfile.setImageResource(R.drawable.iconmain);
        }
else {
            Glide.with(vContext).load(user.getPhotoUrl()).into(holder.imageProfile);
        }
    }

    @Override
    public int getItemCount() {
        return vUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username_on_main;
        public ImageView imageProfile;

        public ViewHolder(View itemView) {
            super(itemView);
            username_on_main = itemView.findViewById(R.id.username_on_main);
            imageProfile = itemView.findViewById(R.id.imageProfile);
        }
    }


}
