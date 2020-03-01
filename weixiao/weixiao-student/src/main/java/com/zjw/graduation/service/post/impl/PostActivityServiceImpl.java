package com.zjw.graduation.service.post.impl;

import com.zjw.graduation.repository.post.PostActivityViewDao;
import com.zjw.graduation.view.post.PostActivityView;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service("postActivityService")
public class PostActivityServiceImpl implements PostActivityService  {

    @Autowired
    private PostActivityDao postActivityDao;

    @Autowired
    private PostActivityViewDao postActivityViewDao;

    public PagingResult<PostActivityView> page(String truename,
                                               String title,
                                               Long categoryId,
                                               Long academyId,
                                               int type,
                                               int state,
                                               int pageIndex,
                                               int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostActivityView> page = postActivityViewDao.findAll(truename, title, categoryId, academyId, type ,state, pageable);

        PagingResult<PostActivityView> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public void review(Long id, int state) {
        PostActivity postActivity = postActivityDao.findById(id).orElse(null);
        if (postActivity != null){
            postActivity.setState(state);
            postActivity.setUpdated(LocalDateTime.now());
            postActivityDao.save(postActivity);
        }
    }

    @Override
    public void batchPass(String ids, int state) {
        List<Long> collect =
                Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        LocalDateTime now = LocalDateTime.now();
        postActivityDao.batchPass(collect, state, now);
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
