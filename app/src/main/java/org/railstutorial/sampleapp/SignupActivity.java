package org.railstutorial.sampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

//        RequestBody body = new FormBody.Builder()
//                .add("user[name]", name)
//                .add("user[email]", email)
//                .add("user[password]", password)
//                .add("user[confirm_password]", confirmPassword)
//                .build();
//        Request request = new Request.Builder().url(ROOT_API + "api/v1/users").post(body).build();

//        mClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println(response.body().string());
//            }
//        });
    }
}
