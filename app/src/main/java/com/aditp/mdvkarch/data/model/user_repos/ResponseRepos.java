package com.aditp.mdvkarch.data.model.user_repos;

import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class ResponseRepos {
    @SerializedName("stargazers_count")
    private int stargazersCount;
    @SerializedName("pushed_at")
    private String pushedAt;
    @SerializedName("subscription_url")
    private String subscriptionUrl;
    @SerializedName("language")
    private Object language;
    @SerializedName("branches_url")
    private String branchesUrl;
    @SerializedName("issue_comment_url")
    private String issueCommentUrl;
    @SerializedName("labels_url")
    private String labelsUrl;
    @SerializedName("subscribers_url")
    private String subscribersUrl;
    @SerializedName("releases_url")
    private String releasesUrl;
    @SerializedName("svn_url")
    private String svnUrl;
    @SerializedName("id")
    private int id;
    @SerializedName("forks")
    private int forks;
    @SerializedName("archive_url")
    private String archiveUrl;
    @SerializedName("git_refs_url")
    private String gitRefsUrl;
    @SerializedName("forks_url")
    private String forksUrl;
    @SerializedName("statuses_url")
    private String statusesUrl;
    @SerializedName("ssh_url")
    private String sshUrl;
    @SerializedName("license")
    private License license;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("size")
    private int size;
    @SerializedName("languages_url")
    private String languagesUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("collaborators_url")
    private String collaboratorsUrl;
    @SerializedName("clone_url")
    private String cloneUrl;
    @SerializedName("name")
    private String name;
    @SerializedName("pulls_url")
    private String pullsUrl;
    @SerializedName("default_branch")
    private String defaultBranch;
    @SerializedName("hooks_url")
    private String hooksUrl;
    @SerializedName("trees_url")
    private String treesUrl;
    @SerializedName("tags_url")
    private String tagsUrl;
    @SerializedName("private")
    private boolean jsonMemberPrivate;
    @SerializedName("contributors_url")
    private String contributorsUrl;
    @SerializedName("has_downloads")
    private boolean hasDownloads;
    @SerializedName("notifications_url")
    private String notificationsUrl;
    @SerializedName("open_issues_count")
    private int openIssuesCount;
    @SerializedName("description")
    private String description;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("watchers")
    private int watchers;
    @SerializedName("keys_url")
    private String keysUrl;
    @SerializedName("deployments_url")
    private String deploymentsUrl;
    @SerializedName("has_projects")
    private boolean hasProjects;
    @SerializedName("archived")
    private boolean archived;
    @SerializedName("has_wiki")
    private boolean hasWiki;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("comments_url")
    private String commentsUrl;
    @SerializedName("stargazers_url")
    private String stargazersUrl;
    @SerializedName("disabled")
    private boolean disabled;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("has_pages")
    private boolean hasPages;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("commits_url")
    private String commitsUrl;
    @SerializedName("compare_url")
    private String compareUrl;
    @SerializedName("git_commits_url")
    private String gitCommitsUrl;
    @SerializedName("blobs_url")
    private String blobsUrl;
    @SerializedName("git_tags_url")
    private String gitTagsUrl;
    @SerializedName("merges_url")
    private String mergesUrl;
    @SerializedName("downloads_url")
    private String downloadsUrl;
    @SerializedName("has_issues")
    private boolean hasIssues;
    @SerializedName("url")
    private String url;
    @SerializedName("contents_url")
    private String contentsUrl;
    @SerializedName("mirror_url")
    private Object mirrorUrl;
    @SerializedName("milestones_url")
    private String milestonesUrl;
    @SerializedName("teams_url")
    private String teamsUrl;
    @SerializedName("fork")
    private boolean fork;
    @SerializedName("issues_url")
    private String issuesUrl;
    @SerializedName("events_url")
    private String eventsUrl;
    @SerializedName("issue_events_url")
    private String issueEventsUrl;
    @SerializedName("assignees_url")
    private String assigneesUrl;
    @SerializedName("open_issues")
    private int openIssues;
    @SerializedName("watchers_count")
    private int watchersCount;
    @SerializedName("node_id")
    private String nodeId;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("forks_count")
    private int forksCount;

    {
        language = "-";
    }
}