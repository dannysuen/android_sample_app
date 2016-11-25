package org.railstutorial.sampleapp;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.avatar_image)
    ImageView mAvatarImage;

    @BindView(R.id.name_text)
    TextView mNameText;

    @BindView(R.id.email_text)
    TextView mEmailText;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUser = Parcels.unwrap(getIntent().getParcelableExtra(User.class.getCanonicalName()));

        Picasso.with(this).load(mUser.gravatarUrl).into(mAvatarImage);
        mNameText.setText(mUser.name);
        mEmailText.setText(mUser.email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_user_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_user_edit:
                Intent intent = new Intent(UserActivity.this, UserEditActivity.class);
                intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(mUser));
                startActivityForResult(intent, UserEditActivity.ACTIVITY_CODE_USER_EDIT);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == UserEditActivity.ACTIVITY_CODE_USER_EDIT) {
                mUser = Parcels.unwrap(data.getParcelableExtra(User.class.getCanonicalName()));
                mNameText.setText(mUser.name);
                mEmailText.setText(mUser.email);
                Picasso.with(this).load(mUser.gravatarUrl).into(mAvatarImage);
            }
        }
    }
}
