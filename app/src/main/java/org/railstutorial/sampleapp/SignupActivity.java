package org.railstutorial.sampleapp;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.api.UserApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.name_field)
    EditText mNameField;

    @BindView(R.id.email_field)
    EditText mEmailField;

    @BindView(R.id.password_field)
    EditText mPasswordField;

    @BindView(R.id.confirm_password_field)
    EditText mConfirmPasswordField;

    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRetrofit = ((SampleAppApplication) getApplication()).getRetrofit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.commit_sign_up_btn)
    public void commitSignup() {
        String name = mNameField.getText().toString();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String confirmPassword = mConfirmPasswordField.getText().toString();

        UserApiService service = mRetrofit.create(UserApiService.class);
        Call<Envelop<User>> call = service.signup(name, email, password, confirmPassword);
        call.enqueue(new Callback<Envelop<User>>() {
            @Override
            public void onResponse(Call<Envelop<User>> call, Response<Envelop<User>> response) {
//                Envelop<User> envelop = response.body();
//
//                Intent intent = new Intent(LoginActivity.this, UserActivity.class);
//                intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(envelop.data));
//                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Envelop<User>> call, Throwable t) {

            }
        });
    }
}
