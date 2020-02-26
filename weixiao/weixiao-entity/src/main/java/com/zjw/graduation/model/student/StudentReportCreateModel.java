package com.zjw.graduation.model.student;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 举报表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:30:19
 */
@ApiModel(value = "student.StudentReportCreateModel", description = "举报表")
public class StudentReportCreateModel {

		
		/**
		 * 举报人主键
		 */
					@NotNull(message = "举报人主键 不能为空")
					

									@ApiModelProperty(value = "举报人主键", required = true)
							private Long studentId;

		
		/**
		 * 被举报人主键
		 */
					@NotNull(message = "被举报人主键 不能为空")
					

									@ApiModelProperty(value = "被举报人主键", required = true)
							private Long reportStudentId;

		
		/**
		 * 内容类型
		 */
							

									@ApiModelProperty(value = "内容类型")
							private Long categoryId;

		
		/**
		 * 发布内容主键
		 */
							

									@ApiModelProperty(value = "发布内容主键")
							private Long postId;

		
		/**
		 * 举报说明
		 */
								@Size(min = 0, max = 255, message = "举报说明 长度需要在0和255之间")
					

									@ApiModelProperty(value = "举报说明")
							private String content;

		
		/**
		 * 举报状态 0-全部 1-正在处理 2-已处理
		 */
							

									@ApiModelProperty(value = "举报状态 0-全部 1-正在处理 2-已处理")
							private Integer state;

		
		/**
		 * 反馈
		 */
								@Size(min = 0, max = 255, message = "反馈 长度需要在0和255之间")
					

									@ApiModelProperty(value = "反馈")
							private String reply;

				
			public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
			public Long getReportStudentId() {
		return reportStudentId;
	}

	public void setReportStudentId(Long reportStudentId) {
		this.reportStudentId = reportStudentId;
	}
			public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
			public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
			public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
			public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
			public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
				
}
