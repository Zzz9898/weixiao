package com.zjw.graduation.model.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 热点内容表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:02:40
 */
@ApiModel(value = "post.PostHotCreateModel", description = "热点内容表")
public class PostHotCreateModel {

		
		/**
		 * 发布内容
		 */
								@Size(min = 0, max = 65535, message = "发布内容 长度需要在0和65535之间")
					

									@ApiModelProperty(value = "发布内容")
							private String content;

		
		/**
		 * 发布时间
		 */
							

									@ApiModelProperty(value = "发布时间", example = "2019-01-01 09:01:01")
			        	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
				private LocalDateTime releaseTime;

		
		/**
		 * 浏览次数
		 */
							

									@ApiModelProperty(value = "浏览次数")
							private Integer lookNum;

		
		/**
		 * 点赞次数
		 */
							

									@ApiModelProperty(value = "点赞次数")
							private Integer likeNum;

		
		/**
		 * 状态 0-禁止 1-正常
		 */
							

									@ApiModelProperty(value = "状态 0-禁止 1-正常")
							private Integer state;

				
			public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
			public LocalDateTime getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(LocalDateTime releaseTime) {
		this.releaseTime = releaseTime;
	}
			public Integer getLookNum() {
		return lookNum;
	}

	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}
			public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
			public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
				
}
