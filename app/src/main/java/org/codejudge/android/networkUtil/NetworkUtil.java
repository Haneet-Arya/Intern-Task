package org.codejudge.android.networkUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {
    private static final String BASE_URL = "https://codejudge-question-artifacts.s3.ap-south-1.amazonaws.com/q-1731";

    private static RetApi retrofit = null;

    public static RetApi getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RetApi.class);
        }
        return retrofit;
    }
}
