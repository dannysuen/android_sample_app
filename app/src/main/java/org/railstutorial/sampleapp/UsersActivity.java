package org.railstutorial.sampleapp;

import org.parceler.Parcels;
import org.railstutorial.sampleapp.api.UserApiService;
import org.railstutorial.sampleapp.model.Envelop;
import org.railstutorial.sampleapp.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersActivity extends AppCompatActivity {

    private static final int INVALID_PAGE = -1;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.users_list)
    RecyclerView mUsersRecyclerView;

    @Inject
    Retrofit mRetrofit;

    private UserApiService mService;
    private int mNextPage = 1;

    private UsersAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SampleAppApplication.NET_COMPONENT.inject(this);

        mService = mRetrofit.create(UserApiService.class);

        // Set layout manager to position the items
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UsersActivity.this);
        mUsersRecyclerView.setLayoutManager(linearLayoutManager);
        mUsersRecyclerView.addItemDecoration(new DividerItemDecoration(UsersActivity.this,
                DividerItemDecoration.VERTICAL_LIST));
        mUsersRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                loadMoreFromApi(mNextPage);
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

        // It needs to be put into MessageQueue in order to animate the control
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
            }
        });

        fetchUsersList();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchUsersList();
            }
        });
    }

    private void fetchUsersList() {
        mNextPage = 1;

        Call<Envelop<List<User>>> call = mService.fetchUsers(mNextPage);
        call.enqueue(new Callback<Envelop<List<User>>>() {
            @Override
            public void onResponse(Call<Envelop<List<User>>> call, Response<Envelop<List<User>>> response) {
                if (response.isSuccessful()) {
                    Envelop<List<User>> envelop = response.body();

                    mNextPage = envelop.pagination.nextUrl == null ? INVALID_PAGE : mNextPage + 1;

                    // Create adapter passing in the sample user data
                    mAdapter = new UsersAdapter(UsersActivity.this, envelop.data);
                    // Attach the adapter to the recyclerview to populate items
                    mUsersRecyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(UsersActivity.this, R.string.fetch_users_failed, Toast.LENGTH_SHORT)
                            .show();
                }

                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Envelop<List<User>>> call, Throwable t) {
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMoreFromApi(int page) {
        if (page != INVALID_PAGE) {
            Call<Envelop<List<User>>> call = mService.fetchUsers(page);
            call.enqueue(new Callback<Envelop<List<User>>>() {
                @Override
                public void onResponse(Call<Envelop<List<User>>> call, Response<Envelop<List<User>>>
                        response) {
                    if (response.isSuccessful()) {
                        Envelop<List<User>> envelop = response.body();
                        int size = mAdapter.getItemCount();
                        mAdapter.addAll(envelop.data);
//                        mAdapter.notifyItemRangeInserted(size, envelop.data.size());

                        mNextPage = envelop.pagination.nextUrl == null ? INVALID_PAGE : mNextPage + 1;
                    }
                }

                @Override
                public void onFailure(Call<Envelop<List<User>>> call, Throwable t) {

                }
            });
        } else {

        }
    }


}
