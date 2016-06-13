package org.railstutorial.sampleapp;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.api.UserApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersActivity extends AppCompatActivity {

    private Retrofit mRetrofit;

    @BindView(R.id.users_list)
    RecyclerView mUsersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRetrofit = ((SampleAppApplication) getApplication()).getRetrofit();

        UserApiService service = mRetrofit.create(UserApiService.class);
        Call<Envelop<List<User>>> call = service.fetchUsers();

        call.enqueue(new Callback<Envelop<List<User>>>() {
            @Override
            public void onResponse(Call<Envelop<List<User>>> call, Response<Envelop<List<User>>> response) {
                if (response.isSuccessful()) {
                    Envelop<List<User>> envelop = response.body();

                    // Create adapter passing in the sample user data
                    UsersAdapter adapter = new UsersAdapter(UsersActivity.this, envelop.data);
                    // Attach the adapter to the recyclerview to populate items
                    mUsersRecyclerView.setAdapter(adapter);
                    // Set layout manager to position the items
                    mUsersRecyclerView.setLayoutManager(new LinearLayoutManager(UsersActivity.this));
                    RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(UsersActivity
                            .this, DividerItemDecoration.VERTICAL_LIST);
                    mUsersRecyclerView.addItemDecoration(itemDecoration);
                } else {
                    Toast.makeText(UsersActivity.this, R.string.fetch_users_failed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Envelop<List<User>>> call, Throwable t) {

            }
        });

        ItemClickSupport.addTo(mUsersRecyclerView).setOnItemClickListener(
                new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        List<User> users = ((UsersAdapter) recyclerView.getAdapter()).getUsers();
                        User user = users.get(position);

                        Intent intent = new Intent(UsersActivity.this, UserActivity.class);
                        intent.putExtra(User.class.getCanonicalName(), Parcels.wrap(user));
                        startActivity(intent);
                    }
                }
        );
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
