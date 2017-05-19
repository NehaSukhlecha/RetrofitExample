package org.prod.retrofitexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.prod.retrofitexample.adapter.UserAdapter;
import org.prod.retrofitexample.api.Client;
import org.prod.retrofitexample.api.WebServices;
import org.prod.retrofitexample.model.GitUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView connection;
    private GitUser user;
    private ProgressDialog pDialog;
    private List<GitUser> gitUserArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        findViews();
    }

    private void findViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        connection = (TextView) findViewById(R.id.textview_nointernet);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Fetching GitUsers...");
        pDialog.setCancelable(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        showProgressDialog();
        CalApi();
    }

    private void CalApi() {
        try {
            WebServices request = new Client().retrofit.create(WebServices.class);
            Call<List<GitUser>> call = request.getUsers();
            call.enqueue(new Callback<List<GitUser>>() {

                @Override
                public void onResponse(Call<List<GitUser>> call, Response<List<GitUser>> response) {
                    gitUserArrayList = response.body();
                    recyclerView.setAdapter(new UserAdapter(getApplicationContext(), gitUserArrayList));
                    recyclerView.smoothScrollToPosition(0);
                    hideProgressDialog();

                }

                @Override
                public void onFailure(Call<List<GitUser>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(UserListActivity.this, "Error Fetching Data!", Toast.LENGTH_SHORT).show();
                    connection.setVisibility(View.VISIBLE);
                    hideProgressDialog();
                }

            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
