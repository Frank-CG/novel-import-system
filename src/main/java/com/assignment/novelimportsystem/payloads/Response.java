package com.assignment.novelimportsystem.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Response {
    private int code;
    private String message;
    private HashMap<String,Object> metadata;

    public Response() {
        this.metadata = new HashMap<>();
    }

    public Response(@JsonProperty("code") int code,
                    @JsonProperty("message") String message,
                    @JsonProperty("metadata") HashMap<String, Object> metadata) {
        this.code = code;
        this.message = message;
        this.metadata = metadata;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(HashMap<String, Object> metadata) {
        this.metadata = metadata;
    }
}
