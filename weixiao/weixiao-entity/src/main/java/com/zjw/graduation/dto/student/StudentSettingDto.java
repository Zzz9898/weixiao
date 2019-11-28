package com.zjw.graduation.dto.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * 学生设置表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-27 19:11:16
 */
@ApiModel(value="student.StudentSettingDto", description="学生设置表")
public class StudentSettingDto {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
@ApiModelProperty(value="主键", name="id", required=true)
	private Long id;

	/**
	 * 用户主键
	 */
@ApiModelProperty(value="用户主键", name="studentId", required=true)
	private Long studentId;

	/**
	 * 是否允许私聊 0-否 1-是
	 */
@ApiModelProperty(value="是否允许私聊 0-否 1-是", name="chatSet")
	private Integer chatSet;

	/**
	 * 是否展示性别 0-否 1-是
	 */
@ApiModelProperty(value="是否展示性别 0-否 1-是", name="sexSet")
	private Integer sexSet;

	/**
	 * 是否显示院部 0-否 1-是
	 */
@ApiModelProperty(value="是否显示院部 0-否 1-是", name="academySet")
	private Integer academySet;

	/**
	 * 创建时间
	 */
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+08:00")
	@ApiModelProperty(value="创建时间", name="created", example="2019-01-01 09:01:01")
	private LocalDateTime created;

	/**
	 * 更新时间
	 */
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+08:00")
	@ApiModelProperty(value="更新时间", name="updated", example="2019-01-01 09:01:01")
	private LocalDateTime updated;

	/**
	 * 逻辑删除 0-是 1-否
	 */
@ApiModelProperty(value="逻辑删除 0-是 1-否", name="logicFlag")
	private Integer logicFlag;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Integer getChatSet() {
		return chatSet;
	}

	public void setChatSet(Integer chatSet) {
		this.chatSet = chatSet;
	}
	public Integer getSexSet() {
		return sexSet;
	}

	public void setSexSet(Integer sexSet) {
		this.sexSet = sexSet;
	}
	public Integer getAcademySet() {
		return academySet;
	}

	public void setAcademySet(Integer academySet) {
		this.academySet = academySet;
	}
	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	public Integer getLogicFlag() {
		return logicFlag;
	}

	public void setLogicFlag(Integer logicFlag) {
		this.logicFlag = logicFlag;
	}

}
