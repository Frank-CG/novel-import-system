package com.assignment.novelimportsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "novel_info")
public class NovelInfo {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @NotNull
    private final Long writerId;
    @NotNull
    private final String novelName;
    private final String novelType;
    private final String novelStatus;
    private final String novelDesc;
    
    public NovelInfo(){
        id = null;
        writerId = -1L;
        novelName = "";
        novelType = "";
        novelStatus = "";
        novelDesc = "";
    }

    public NovelInfo(@JsonProperty("id") Long id,
                     @JsonProperty("writerId") Long writerId,
                     @JsonProperty("novelName") String novelName,
                     @JsonProperty("novelType") String novelType,
                     @JsonProperty("novelStatus") String novelStatus,
                     @JsonProperty("novelDesc") String novelDesc) {
        this.id = id;// == null ? RandomUtils.nextLong(): novelId;
        this.writerId = writerId;
        this.novelName = novelName;
        this.novelType = novelType;
        this.novelStatus = novelStatus;
        this.novelDesc = novelDesc;
    }

    public static NovelInfo parse(String record){
        String[] arr = record.split(",");
        if(arr[0].equals("")){
            return new NovelInfo(null,Long.parseLong(arr[1]),arr[2],arr[3],arr[4],arr[5]);
        }
        return new NovelInfo(Long.parseLong(arr[0]),Long.parseLong(arr[1]),arr[2],arr[3],arr[4],arr[5]);
    }

    public Long getId() {
        return id;
    }

    public Long getWriterId() {
        return writerId;
    }

    public String getNovelName() {
        return novelName;
    }

    public String getNovelType() {
        return novelType;
    }

    public String getNovelStatus() {
        return novelStatus;
    }

    public String getNovelDesc() {
        return novelDesc;
    }

    @Override
    public String toString() {
        return "NovelInfo{" +
                "id=" + id +
                ", writerId=" + writerId +
                ", novelName='" + novelName + '\'' +
                ", novelType='" + novelType + '\'' +
                ", novelStatus='" + novelStatus + '\'' +
                ", novelDesc='" + novelDesc + '\'' +
                '}';
    }
}
