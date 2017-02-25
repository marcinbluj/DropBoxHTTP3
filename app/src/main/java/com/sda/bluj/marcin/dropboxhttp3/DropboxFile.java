package com.sda.bluj.marcin.dropboxhttp3;

/**
 * Created by RENT on 2017-02-25.
 */

public class DropboxFile {

    private String id;
    private String name;
    private String path;
    private String tag;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "DropboxFile " +
                "name: '" + name + '\'';
    }
}
