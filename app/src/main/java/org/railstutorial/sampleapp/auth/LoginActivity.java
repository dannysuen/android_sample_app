package org.railstutorial.sampleapp.auth;


import org.parceler.Parcels;
import org.railstutorial.sampleapp.R;
import org.railstutorial.sampleapp.SampleAppApplication;
import org.railstutorial.sampleapp.UserActivity;

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

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.email_field)
    EditText mEmailField;

    @BindView(R.id.password_field)
    EditText mPasswordField;

    LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        SampleAppApplication.netComponent().inject(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new LoginPresenter(this, new LoginInteractor());
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

        mPresenter.login(email, password);
    }

    @Override
    public void startProgress() {

    }

    @Override
    public void showLoginSuccess(User user) {
        Toast.makeText(this, user.email, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(user));
        startActivity(intent);
    }

    @Override
    public void showLoginFailure(String failureMessage) {
        Toast.makeText(this, failureMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void moveToNext(User loggedInUser) {
        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
        intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(loggedInUser));
        startActivity(intent);
    }
}
