package org.prod.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.prod.retrofitexample.R;
import org.prod.retrofitexample.model.GitUser;

import java.util.List;

/**
 * Created by Neha on 5/19/17.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<GitUser> GitUsers;
    private Context context;

    public UserAdapter(Context applicationContext, List<GitUser> GitUserArrayList) {
        this.context = applicationContext;
        this.GitUsers = GitUserArrayList;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_gituser, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder viewHolder, int i) {
        GitUser user = GitUsers.get(i);

        if (user != null) {
            viewHolder.name.setText(GitUsers.get(i).getName());

            // This is how we use Picasso to load images from the internet.
            Picasso.with(context)
                    .load(GitUsers.get(i).getImage_url())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(viewHolder.imageView);

        }
        ;
    }

    @Override
    public int getItemCount() {
        return GitUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textview_name);
            imageView = (ImageView) view.findViewById(R.id.imageview_user);


        }
    }

    public void clear() {
        GitUsers.clear();
        notifyDataSetChanged();
    }

    public void addAll(int position, List<GitUser> mov) {
        GitUsers.addAll(0, mov);
        notifyItemInserted(0);
        notifyDataSetChanged();
    }
}
