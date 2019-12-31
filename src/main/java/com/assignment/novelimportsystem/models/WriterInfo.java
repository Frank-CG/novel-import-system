package com.assignment.novelimportsystem.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "writer_info")
public class WriterInfo {
    @javax.persistence.Id
    private final Long id;
    private final String name;
    private final String email;

    public WriterInfo() {
        this.id = -1L;
        this.email = "";
        this.name = "";
    }

    public WriterInfo(@JsonProperty("id") Long id,
                      @JsonProperty("name") String name,
                      @JsonProperty("email") String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "WriterInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
