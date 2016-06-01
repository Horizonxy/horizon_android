package com.horizon.android.model.bean;

import java.io.Serializable;
import java.util.List;

public class JokeVo implements Serializable {

    private List<JokeData>  data;

    public List<JokeData> getData() {
        return data;
    }

    public void setData(List<JokeData> data) {
        this.data = data;
    }

    public class JokeData implements Serializable {
        private String content;
        private String hashId;
        private long unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public long getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(long unixtime) {
            this.unixtime = unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }
    }
}


