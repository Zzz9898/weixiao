package com.zjw.graduation.service.post.impl;

import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostLike;
import com.zjw.graduation.repository.post.PostLikeDao;
import com.zjw.graduation.service.post.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("postLikeService")
public class PostLikeServiceImpl implements PostLikeService  {

    @Autowired
    private PostLikeDao postLikeDao;

    public PagingResult<PostLike> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostLike> page = postLikeDao.findAll(pageable);

        PagingResult<PostLike> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public PostLike get(Long id) {
        return postLikeDao.findById(id).orElse(new PostLike());
    }

    @Override
    public PostLike save(PostLike postLike) {
        return postLikeDao.save(postLike);
    }

    @Override
    public PostLike update(PostLike postLike) {
        return postLikeDao.save(postLike);
    }

    @Override
    public void delete(Long id) {
        PostLike postLike = postLikeDao.findById(id).orElse(null);
        if (postLike != null){
            postLike.setLogicFlag(EnumLogicType.DELETE.getValue());
            postLikeDao.save(postLike);
        }
    }

}
