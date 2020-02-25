package com.zjw.graduation.service.post.impl;

import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostHot;
import com.zjw.graduation.repository.post.PostHotDao;
import com.zjw.graduation.service.post.PostHotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("postHotService")
public class PostHotServiceImpl implements PostHotService  {

    @Autowired
    private PostHotDao postHotDao;

    public PagingResult<PostHot> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostHot> page = postHotDao.findAll(pageable);

        PagingResult<PostHot> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public PostHot get(Long id) {
        return postHotDao.findById(id).orElse(new PostHot());
    }

    @Override
    public PostHot save(PostHot postHot) {
        return postHotDao.save(postHot);
    }

    @Override
    public PostHot update(PostHot postHot) {
        return postHotDao.save(postHot);
    }

    @Override
    public void delete(Long id) {
        PostHot postHot = postHotDao.findById(id).orElse(null);
        if (postHot != null){
            postHot.setLogicFlag(EnumLogicType.DELETE.getValue());
            postHotDao.save(postHot);
        }
    }

}
