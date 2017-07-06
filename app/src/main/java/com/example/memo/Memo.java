package com.example.memo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by YUNKYUSEOK on 2017-06-29.
 */
public class Memo {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("text")
    @Expose
    public String content;
    @SerializedName("id")
    @Expose
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
