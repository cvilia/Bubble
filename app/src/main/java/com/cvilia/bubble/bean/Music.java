package com.cvilia.bubble.bean;

public class Music {
    private String name;
    private String singer;
    private String url;
    private String path;
    private long time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                '}';
    }
}
