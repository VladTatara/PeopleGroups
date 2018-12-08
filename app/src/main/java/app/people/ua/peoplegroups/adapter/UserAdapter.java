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

import java.util.List;

import app.people.ua.peoplegroups.R;
import app.people.ua.peoplegroups.user_info;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context vContext;
    private List<user_info> vuser_info;

    public UserAdapter (Context vContext, List<user_info> vuser_info){
        this.vuser_info = vuser_info;
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
        user_info user_info = vuser_info.get(position);
        holder.username_on_main.setText(user_info.getUsername());

        if (user_info.getImageURL().equals("default")){
            holder.imageProfile.setImageResource(R.drawable.iconmain);
        }
else {
            Glide.with(vContext).load(user_info.getImageURL()).into(holder.imageProfile);
        }
    }

    @Override
    public int getItemCount() {
        return vuser_info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username_on_main;
        public ImageView imageProfile;

        ViewHolder(View itemView) {
            super(itemView);
            username_on_main = itemView.findViewById(R.id.username_on_main);
            imageProfile = itemView.findViewById(R.id.imageProfile);
        }
    }


}
