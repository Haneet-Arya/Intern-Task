package org.codejudge.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.codejudge.android.adapter.RCVAdapter;
import org.codejudge.android.dataModel.Rest;
import org.codejudge.android.networkUtil.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {
    private RecyclerView recyclerView;
    private EditText editText;
    private Rest rest;
    private RCVAdapter adapter;
    List<Rest.Restaurants> restaurants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.edit_search);
        recyclerView = findViewById(R.id.restRCV);
        adapter = new RCVAdapter(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        Call<Rest> call = NetworkUtil.getClient().getData();
        call.enqueue(new Callback<Rest>() {
            @Override
            public void onResponse(Call<Rest> call, Response<Rest> response) {
                if(!response.isSuccessful()){
                    return;
                }
                rest = response.body();
                assert rest != null;
                restaurants.addAll(rest.getRestaurantss());
                adapter.update(restaurants);
            }

            @Override
            public void onFailure(Call<Rest> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_LONG).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals("")){
                    adapter.update(restaurants);
                    return;
                }
                List<Rest.Restaurants> temp = new ArrayList<>();
                for(Rest.Restaurants e: restaurants){
                    if(e.name.startsWith(charSequence.toString())){
                        temp.add(e);
                    }
                }
                adapter.update(temp);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}