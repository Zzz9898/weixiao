package com.zjw.graduation.service.post;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.post.PostSign;

/**
 * 活动报名表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:09:07
 */
public interface PostSignService {
    PagingResult<PostSign> page(int pageIndex, int pageSize);

    PostSign get(Long id);

    PostSign save(PostSign Admin);

    PostSign update(PostSign Admin);

    void delete(Long id);
}

