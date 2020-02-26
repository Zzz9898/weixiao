package com.zjw.graduation.service.post;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.post.PostContent;

/**
 * 发布内容表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:27:41
 */
public interface PostContentService {
    PagingResult<PostContent> page(int pageIndex, int pageSize);

    PostContent get(Long id);

    PostContent save(PostContent Admin);

    PostContent update(PostContent Admin);

    void delete(Long id);
}

