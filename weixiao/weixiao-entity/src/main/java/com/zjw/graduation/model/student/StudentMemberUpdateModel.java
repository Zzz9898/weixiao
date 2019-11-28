package com.zjw.graduation.model.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 学生表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-25 10:30:03
 */
@ApiModel(value = "student.StudentMemberUpdateModel", description = "学生表")
public class StudentMemberUpdateModel {


    /**
     * 主键
     */
    @NotNull(message = "主键 不能为空")
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;


    /**
     * 性别 0-不详 1-男 2-女
     */
    @ApiModelProperty(value = "性别 0-不详 1-男 2-女")
    private Integer sex;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;


    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;


    /**
     * 地区
     */
    @ApiModelProperty(value = "地区")
    private String area;


    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private String number;


    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;


    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String faceImg;


    /**
     * 聊天头像
     */
    @ApiModelProperty(value = "聊天头像")
    private String faceImgMin;


    /**
     * 专业id
     */
    @ApiModelProperty(value = "专业id")
    private Long academyId;


    /**
     * 个人签名
     */
    @ApiModelProperty(value = "个人签名")
    private String autograph;


    /**
     * 是否隐藏个人信息 1-不隐藏 2-隐藏
     */
    @ApiModelProperty(value = "是否隐藏个人信息 1-不隐藏 2-隐藏")
    private Integer hide;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFaceImg() {
        return faceImg;
    }

    public void setFaceImg(String faceImg) {
        this.faceImg = faceImg;
    }

    public String getFaceImgMin() {
        return faceImgMin;
    }

    public void setFaceImgMin(String faceImgMin) {
        this.faceImgMin = faceImgMin;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
    }

}
