package com.zjw.graduation.service.post.impl;

import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostActivity;
import com.zjw.graduation.repository.post.PostActivityDao;
import com.zjw.graduation.service.post.PostActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("postActivityService")
public class PostActivityServiceImpl implements PostActivityService  {

    @Autowired
    private PostActivityDao postActivityDao;

    public PagingResult<PostActivity> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostActivity> page = postActivityDao.findAll(pageable);

        PagingResult<PostActivity> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public PostActivity get(Long id) {
        return postActivityDao.findById(id).orElse(new PostActivity());
    }

    @Override
    public PostActivity save(PostActivity postActivity) {
        return postActivityDao.save(postActivity);
    }

    @Override
    public PostActivity update(PostActivity postActivity) {
        return postActivityDao.save(postActivity);
    }

    @Override
    public void delete(Long id) {
        PostActivity postActivity = postActivityDao.findById(id).orElse(null);
        if (postActivity != null){
            postActivity.setLogicFlag(EnumLogicType.DELETE.getValue());
            postActivityDao.save(postActivity);
        }
    }

}
