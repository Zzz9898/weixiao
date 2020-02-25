package com.zjw.graduation.service.post;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.post.PostCommant;

/**
 * 
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:09:07
 */
public interface PostCommantService {
    PagingResult<PostCommant> page(int pageIndex, int pageSize);

    PostCommant get(Long id);

    PostCommant save(PostCommant Admin);

    PostCommant update(PostCommant Admin);

    void delete(Long id);
}

