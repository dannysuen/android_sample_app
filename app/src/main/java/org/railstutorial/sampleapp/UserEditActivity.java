package org.railstutorial.sampleapp;

import org.railstutorial.sampleapp.model.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class UserEditActivity extends AppCompatActivity {

    @BindView(R.id.name_field)
    EditText mNameField;

    @BindView(R.id.email_field)
    EditText mEmailField;

    @BindView(R.id.password_field)
    EditText mPasswordField;

    @BindView(R.id.password_confirmation_field)
    EditText mPasswordConfirmationField;

    private Retrofit mRetrofit;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRetrofit = ((SampleAppApplication) getApplication()).getRetrofit();

        mUser = getIntent().getParcelableExtra(User.class.getCanonicalName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onSaveChangesButtonClick(View view) {

    }


}
