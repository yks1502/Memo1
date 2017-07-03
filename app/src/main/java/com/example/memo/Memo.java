package com.example.memo;

/**
 * Created by YUNKYUSEOK on 2017-06-29.
 */

public class Memo {
    public String title;
    public String content;
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
