package com.zjw.graduation.service.post.impl;

import com.zjw.graduation.repository.post.PostSignViewDao;
import com.zjw.graduation.view.post.PostSignView;
import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostSign;
import com.zjw.graduation.repository.post.PostSignDao;
import com.zjw.graduation.service.post.PostSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("postSignService")
public class PostSignServiceImpl implements PostSignService  {

    @Autowired
    private PostSignDao postSignDao;

    @Autowired
    private PostSignViewDao postSignViewDao;

    @Override
    public PagingResult<PostSignView> page(Long activityId, String truename, int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostSignView> page = postSignViewDao.findAll(activityId, truename, pageable);

        PagingResult<PostSignView> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public PostSign get(Long id) {
        return postSignDao.findById(id).orElse(new PostSign());
    }

    @Override
    public PostSign save(PostSign postSign) {
        return postSignDao.save(postSign);
    }

    @Override
    public PostSign update(PostSign postSign) {
        return postSignDao.save(postSign);
    }

    @Override
    public void delete(Long id) {
        PostSign postSign = postSignDao.findById(id).orElse(null);
        if (postSign != null){
            postSign.setLogicFlag(EnumLogicType.DELETE.getValue());
            postSignDao.save(postSign);
        }
    }
}
