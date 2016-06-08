package org.railstutorial.sampleapp;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.model.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.avatar_image)
    ImageView mAvatarImage;

    @BindView(R.id.name_text)
    TextView mNameText;

    @BindView(R.id.email_text)
    TextView mEmailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User user = Parcels.unwrap(getIntent().getParcelableExtra(User.class.getCanonicalName()));

        Picasso.with(this).load(user.gravatarUrl).into(mAvatarImage);
        mNameText.setText(user.name);
        mEmailText.setText(user.email);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
