package com.cvilia.bubble.model;

import java.util.List;

public class BubbleModel {

    private Integer code;
    private String msg;
    private List<Data> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data {
        private String title;
        private String srcUrl;
        private String arouter;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSrcUrl() {
            return srcUrl;
        }

        public void setSrcUrl(String srcUrl) {
            this.srcUrl = srcUrl;
        }

        public String getArouter() {
            return arouter;
        }

        public void setArouter(String arouter) {
            this.arouter = arouter;
        }
    }
}
