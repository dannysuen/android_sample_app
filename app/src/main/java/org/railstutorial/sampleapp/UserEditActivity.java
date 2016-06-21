package org.railstutorial.sampleapp;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.api.UserApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserEditActivity extends AppCompatActivity {

    public static final int ACTIVITY_CODE_USER_EDIT = 1001;

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

        mUser = Parcels.unwrap(getIntent().getParcelableExtra(User.class.getCanonicalName()));

        mNameField.setText(mUser.name);
        mEmailField.setText(mUser.email);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.save_changes_btn)
    public void onSaveChangesButtonClick(View view) {
        String name = mNameField.getText().toString();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String passwordConfirmation = mPasswordConfirmationField.getText().toString();

        UserApiService service = mRetrofit.create(UserApiService.class);
        Call<Envelop<User>> call = service.update(mUser.id, name, email, password, passwordConfirmation);
        call.enqueue(new Callback<Envelop<User>>() {
            @Override
            public void onResponse(Call<Envelop<User>> call, Response<Envelop<User>> response) {
                if (response.isSuccessful()) {
                    Envelop<User> envelop = response.body();

                    Intent data = new Intent();
                    data.putExtra(User.class.getCanonicalName(), Parcels.wrap(envelop.data));
                    setResult(RESULT_OK, data);

                    finish();
                } else if (response.code() >= 400 && response.code() < 500) {
                    Envelop<User> envelop = response.body();

                    Toast.makeText(UserEditActivity.this, envelop.meta.errorMessage, Toast.LENGTH_SHORT)
                            .show();
                } else if (response.code() >= 500){
                    Toast.makeText(UserEditActivity.this, R.string.log_in_failed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Envelop<User>> call, Throwable t) {

            }
        });
    }


}
