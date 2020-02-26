package com.zjw.graduation.service.post;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.post.PostActivity;

/**
 * 活动发布表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:27:41
 */
public interface PostActivityService {
    PagingResult<PostActivity> page(int pageIndex, int pageSize);

    PostActivity get(Long id);

    PostActivity save(PostActivity Admin);

    PostActivity update(PostActivity Admin);

    void delete(Long id);
}

