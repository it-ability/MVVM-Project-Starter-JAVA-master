package com.aditp.mdvkarch.data.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Errors {
    @SerializedName("message")
    private String message;
    @SerializedName("documentation_url")
    private String documentationUrl;
}