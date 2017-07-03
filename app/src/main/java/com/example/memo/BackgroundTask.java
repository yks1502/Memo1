package com.example.memo;

import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by YUNKYUSEOK on 2017-07-03.
 */

public class BackgroundTask extends AsyncTask<Void, Void, List<Memo>> {
    @Override
    protected List<Memo> doInBackground(Void ... values) {
        GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
        Call<List<Memo>> call = gitHubService.repoMemo();

        try {
            return call.execute().body();
        } catch (IOException e) {}

        return null;
    }

}
