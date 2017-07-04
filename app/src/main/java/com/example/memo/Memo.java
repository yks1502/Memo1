package com.example.memo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by YUNKYUSEOK on 2017-06-29.
 */

public class Memo {
    @SerializedName("title")
    public String title;
    @SerializedName("text")
    public String content;
    @SerializedName("id")
    public int id;

    public Memo (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Memo (String title, String content, int id) {
        this.title = title;
        this.content = content;
        this.id = id;
    }
}
