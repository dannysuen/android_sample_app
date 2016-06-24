package org.railstutorial.sampleapp;

import com.squareup.picasso.Picasso;

import org.railstutorial.sampleapp.model.User;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by danny on 16-6-12.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_image)
        ImageView mAvatarImage;

        @BindView(R.id.name_text)
        TextView mNameText;

        @BindView(R.id.email_text)
        TextView mEmailText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private List<User> mUsers;
    private Context mContext;

    public UsersAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    public void addAll(List<User> users) {
        int size = getItemCount();
        mUsers.addAll(users);
        notifyItemRangeInserted(size, getItemCount());
    }

    public void clear() {
        mUsers.clear();
        notifyDataSetChanged();
    }

    public List<User> getUsers() {
        return mUsers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_user, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mUsers.get(position);

        Picasso.with(mContext).load(user.gravatarUrl).into(holder.mAvatarImage);

        holder.mNameText.setText(user.name);
        holder.mEmailText.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
