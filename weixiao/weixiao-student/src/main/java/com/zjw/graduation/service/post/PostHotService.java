package com.zjw.graduation.service.post;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.post.PostHot;

/**
 * 热点内容表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:27:41
 */
public interface PostHotService {
    PagingResult<PostHot> page(int pageIndex, int pageSize);

    PostHot get(Long id);

    PostHot save(PostHot Admin);

    PostHot update(PostHot Admin);

    void delete(Long id);
}

