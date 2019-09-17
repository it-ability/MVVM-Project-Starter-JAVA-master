package com.aditp.mdvkarch.data.model.user_repos;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class License {
    @SerializedName("name")
    private String name;
    @SerializedName("spdx_id")
    private String spdxId;
    @SerializedName("key")
    private String key;
    @SerializedName("url")
    private String url;
    @SerializedName("node_id")
    private String nodeId;
}