package org.codejudge.android.networkUtil;

import org.codejudge.android.dataModel.Rest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetApi {
    @GET("/final.json")
    Call<Rest> getData();
}
