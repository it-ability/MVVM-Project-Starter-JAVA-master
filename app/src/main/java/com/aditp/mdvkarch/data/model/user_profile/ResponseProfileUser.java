package com.aditp.mdvkarch.data.model.user_profile;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ResponseProfileUser {
    @SerializedName("gists_url")
    private String gistsUrl;
    @SerializedName("repos_url")
    private String reposUrl;
    @SerializedName("following_url")
    private String followingUrl;
    @SerializedName("bio")
    private String bio;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("login")
    private String login;
    @SerializedName("type")
    private String type;
    @SerializedName("blog")
    private String blog;
    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("site_admin")
    private boolean siteAdmin;
    @SerializedName("company")
    private String company;
    @SerializedName("id")
    private int id;
    @SerializedName("public_repos")
    private int publicRepos;
    @SerializedName("gravatar_id")
    private String gravatarId;
    @SerializedName("email")
    private Object email;
    @SerializedName("organizations_url")
    private String organizationsUrl;
    @SerializedName("hireable")
    private boolean hireable;
    @SerializedName("starred_url")
    private String starredUrl;
    @SerializedName("followers_url")
    private String followersUrl;
    @SerializedName("public_gists")
    private int publicGists;
    @SerializedName("url")
    private String url;
    @SerializedName("received_events_url")
    private String receivedEventsUrl;
    @SerializedName("followers")
    private int followers;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("following")
    private int following;
    @SerializedName("name")
    private String name;
    @SerializedName("location")
    private String location;
    @SerializedName("node_id")
    private String nodeId;
}