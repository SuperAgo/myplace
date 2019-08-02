package com.myplace.models.entity;

import java.util.Date;

public class MyUser {
    /**
     * Id
     */
    private Integer id;

    /**
     * 真实姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 手机号
     */
    private Integer telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 网络头像
     */
    private String networkAvatar;

    /**
     * 1男
            2女
     */
    private Integer sex;

    /**
     * 个人签名
     */
    private String signature;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 地区
     */
    private String region;

    /**
     * 地区代码
     */
    private String regionCode;

    /**
     * 手机号
     */
    private String background;

    /**
     * 小图1
     */
    private String picOne;

    /**
     * 小图2
     */
    private String picTwo;

    /**
     * 小图3
     */
    private String picThree;

    /**
     * 小图4
     */
    private String picFour;

    /**
     * 留言板背景图
     */
    private String messageBoardPictures;

    /**
     * 留言板寄语
     */
    private String messageBoards;

    /**
     * 状态
            1使用中
            0已删除
     */
    private Integer state;

    /**
     * 0未实名
            1已实名
     */
    private Integer struts;

    /**
     * 新增时间
     */
    private Date insTime;

    /**
     * 盐
     */
    private String salt;

    /**
     * 1.博主
            2.其他
     */
    private Integer type;

    /**
     * 订阅状态:0未订阅，1已订阅
     */
    private Integer subscribe;

    /**
     * Id
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * Id
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 真实姓名
     * 
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 真实姓名
     * 
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 昵称
     * 
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     * 
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * 手机号
     * 
     */
    public Integer getTelephone() {
        return telephone;
    }

    /**
     * 手机号
     * 
     */
    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    /**
     * 邮箱
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * 
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 密码
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * 
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 生日
     * 
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 生日
     * 
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 网络头像
     * 
     */
    public String getNetworkAvatar() {
        return networkAvatar;
    }

    /**
     * 网络头像
     * 
     */
    public void setNetworkAvatar(String networkAvatar) {
        this.networkAvatar = networkAvatar == null ? null : networkAvatar.trim();
    }

    /**
     * 1男
            2女
     * 
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 1男
            2女
     * 
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 个人签名
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 个人签名
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 简介
     * 
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 简介
     * 
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * 爱好
     * 
     */
    public String getHobby() {
        return hobby;
    }

    /**
     * 爱好
     * 
     */
    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    /**
     * 地区
     * 
     */
    public String getRegion() {
        return region;
    }

    /**
     * 地区
     * 
     */
    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    /**
     * 地区代码
     * 
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * 地区代码
     * 
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    /**
     * 手机号
     * 
     */
    public String getBackground() {
        return background;
    }

    /**
     * 手机号
     * 
     */
    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    /**
     * 小图1
     * 
     */
    public String getPicOne() {
        return picOne;
    }

    /**
     * 小图1
     * 
     */
    public void setPicOne(String picOne) {
        this.picOne = picOne == null ? null : picOne.trim();
    }

    /**
     * 小图2
     * 
     */
    public String getPicTwo() {
        return picTwo;
    }

    /**
     * 小图2
     * 
     */
    public void setPicTwo(String picTwo) {
        this.picTwo = picTwo == null ? null : picTwo.trim();
    }

    /**
     * 小图3
     * 
     */
    public String getPicThree() {
        return picThree;
    }

    /**
     * 小图3
     * 
     */
    public void setPicThree(String picThree) {
        this.picThree = picThree == null ? null : picThree.trim();
    }

    /**
     * 小图4
     * 
     */
    public String getPicFour() {
        return picFour;
    }

    /**
     * 小图4
     * 
     */
    public void setPicFour(String picFour) {
        this.picFour = picFour == null ? null : picFour.trim();
    }

    /**
     * 留言板背景图
     */
    public String getMessageBoardPictures() {
        return messageBoardPictures;
    }

    /**
     * 留言板背景图
     */
    public void setMessageBoardPictures(String messageBoardPictures) {
        this.messageBoardPictures = messageBoardPictures;
    }

    /**
     * 留言板寄语
     */
    public String getMessageBoards() {
        return messageBoards;
    }

    /**
     * 留言板寄语
     */
    public void setMessageBoards(String messageBoards) {
        this.messageBoards = messageBoards;
    }

    /**
     * 状态
            1使用中
            0已删除
     * 
     */
    public Integer getState() {
        return state;
    }

    /**
     * 状态
            1使用中
            0已删除
     * 
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 0未实名
            1已实名
     * 
     */
    public Integer getStruts() {
        return struts;
    }

    /**
     * 0未实名
            1已实名
     * 
     */
    public void setStruts(Integer struts) {
        this.struts = struts;
    }

    /**
     * 新增时间
     * 
     */
    public Date getInsTime() {
        return insTime;
    }

    /**
     * 新增时间
     * 
     */
    public void setInsTime(Date insTime) {
        this.insTime = insTime;
    }

    /**
     * 盐
     * 
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     * 
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 1.博主
            2.其他
     * 
     */
    public Integer getType() {
        return type;
    }

    /**
     * 1.博主
            2.其他
     * 
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 订阅状态:0未订阅，1已订阅
     * 
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 订阅状态:0未订阅，1已订阅
     * 
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }
}