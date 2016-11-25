package org.railstutorial.sampleapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.api.ApiRoot;
import org.railstutorial.sampleapp.api.SessionApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_field)
    EditText mEmailField;

    @BindView(R.id.password_field)
    EditText mPasswordField;

    @Inject
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        SampleAppApplication.NET_COMPONENT.inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.login_btn)
    public void onLoginClick(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        SessionApiService service = mRetrofit.create(SessionApiService.class);
        Call<Envelop<User>> call = service.login(email, password);
        call.enqueue(new Callback<Envelop<User>>() {
            @Override
            public void onResponse(Call<Envelop<User>> call, Response<Envelop<User>> response) {
                if (response.isSuccessful()) {
                    Envelop<User> envelop = response.body();

                    Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                    intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(envelop.data));
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, R.string.log_in_failed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Envelop<User>> call, Throwable t) {

            }
        });
    }
}
