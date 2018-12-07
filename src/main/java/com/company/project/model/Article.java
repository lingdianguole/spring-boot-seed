package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Article {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 子版本的文章id
     */
    private Integer pid;

    /**
     * slug
     */
    private String slug;

    /**
     * 标题
     */
    private String title;

    /**
     * 编辑模式，默认为html，其他可选项包括html，markdown ..
     */
    @Column(name = "edit_mode")
    private String editMode;

    /**
     * 连接到(常用于谋文章只是一个连接)
     */
    @Column(name = "link_to")
    private String linkTo;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 样式
     */
    private String style;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 排序编号
     */
    @Column(name = "order_number")
    private Integer orderNumber;

    /**
     * 状态
     */
    private String status;

    /**
     * 评论状态，默认允许评论
     */
    @Column(name = "comment_status")
    private Boolean commentStatus;

    /**
     * 评论总数
     */
    @Column(name = "comment_count")
    private Integer commentCount;

    /**
     * 最后评论时间
     */
    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 访问量
     */
    @Column(name = "view_count")
    private Integer viewCount;

    /**
     * 创建日期
     */
    private Date created;

    /**
     * 最后更新日期
     */
    private Date modified;

    /**
     * 标识，通常用于对某几篇文章进行标识，从而实现单独查询
     */
    private String flag;

    /**
     * SEO关键字
     */
    @Column(name = "meta_keywords")
    private String metaKeywords;

    /**
     * SEO描述信息
     */
    @Column(name = "meta_description")
    private String metaDescription;

    /**
     * 内容
     */
    private String content;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 获取主键ID
     *
     * @return id - 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID
     *
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取子版本的文章id
     *
     * @return pid - 子版本的文章id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置子版本的文章id
     *
     * @param pid 子版本的文章id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取slug
     *
     * @return slug - slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 设置slug
     *
     * @param slug slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取编辑模式，默认为html，其他可选项包括html，markdown ..
     *
     * @return edit_mode - 编辑模式，默认为html，其他可选项包括html，markdown ..
     */
    public String getEditMode() {
        return editMode;
    }

    /**
     * 设置编辑模式，默认为html，其他可选项包括html，markdown ..
     *
     * @param editMode 编辑模式，默认为html，其他可选项包括html，markdown ..
     */
    public void setEditMode(String editMode) {
        this.editMode = editMode;
    }

    /**
     * 获取连接到(常用于谋文章只是一个连接)
     *
     * @return link_to - 连接到(常用于谋文章只是一个连接)
     */
    public String getLinkTo() {
        return linkTo;
    }

    /**
     * 设置连接到(常用于谋文章只是一个连接)
     *
     * @param linkTo 连接到(常用于谋文章只是一个连接)
     */
    public void setLinkTo(String linkTo) {
        this.linkTo = linkTo;
    }

    /**
     * 获取缩略图
     *
     * @return thumbnail - 缩略图
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置缩略图
     *
     * @param thumbnail 缩略图
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 获取样式
     *
     * @return style - 样式
     */
    public String getStyle() {
        return style;
    }

    /**
     * 设置样式
     *
     * @param style 样式
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取排序编号
     *
     * @return order_number - 排序编号
     */
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置排序编号
     *
     * @param orderNumber 排序编号
     */
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取评论状态，默认允许评论
     *
     * @return comment_status - 评论状态，默认允许评论
     */
    public Boolean getCommentStatus() {
        return commentStatus;
    }

    /**
     * 设置评论状态，默认允许评论
     *
     * @param commentStatus 评论状态，默认允许评论
     */
    public void setCommentStatus(Boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * 获取评论总数
     *
     * @return comment_count - 评论总数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 设置评论总数
     *
     * @param commentCount 评论总数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 获取最后评论时间
     *
     * @return comment_time - 最后评论时间
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * 设置最后评论时间
     *
     * @param commentTime 最后评论时间
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * 获取访问量
     *
     * @return view_count - 访问量
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * 设置访问量
     *
     * @param viewCount 访问量
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * 获取创建日期
     *
     * @return created - 创建日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建日期
     *
     * @param created 创建日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取最后更新日期
     *
     * @return modified - 最后更新日期
     */
    public Date getModified() {
        return modified;
    }

    /**
     * 设置最后更新日期
     *
     * @param modified 最后更新日期
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * 获取标识，通常用于对某几篇文章进行标识，从而实现单独查询
     *
     * @return flag - 标识，通常用于对某几篇文章进行标识，从而实现单独查询
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置标识，通常用于对某几篇文章进行标识，从而实现单独查询
     *
     * @param flag 标识，通常用于对某几篇文章进行标识，从而实现单独查询
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取SEO关键字
     *
     * @return meta_keywords - SEO关键字
     */
    public String getMetaKeywords() {
        return metaKeywords;
    }

    /**
     * 设置SEO关键字
     *
     * @param metaKeywords SEO关键字
     */
    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    /**
     * 获取SEO描述信息
     *
     * @return meta_description - SEO描述信息
     */
    public String getMetaDescription() {
        return metaDescription;
    }

    /**
     * 设置SEO描述信息
     *
     * @param metaDescription SEO描述信息
     */
    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取摘要
     *
     * @return summary - 摘要
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置摘要
     *
     * @param summary 摘要
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}