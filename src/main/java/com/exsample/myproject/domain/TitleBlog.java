package com.exsample.myproject.domain;

import jakarta.persistence.*;

@Entity
public class TitleBlog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titleName;
    private String titleTag;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User authorName;


    public TitleBlog(String titleName, String titleTag, User user) {
        this.authorName = user;
        this.titleName = titleName;
        this.titleTag = titleTag;
    }
    public String getAuthorNameNew() {
        return authorName != null ? authorName.getUsername() : "<none>";
    }
    public User getAuthorName() {
        return authorName;
    }

    public void setAuthorName(User authorName) {
        this.authorName = authorName;
    }
    public TitleBlog() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }




    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public void setTitleTag(String titleTag) {
        this.titleTag = titleTag;
    }
}
