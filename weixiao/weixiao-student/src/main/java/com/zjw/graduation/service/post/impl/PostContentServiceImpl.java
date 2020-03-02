package com.zjw.graduation.service.post.impl;

import com.zjw.graduation.enums.EnumStateType;
import com.zjw.graduation.repository.post.PostContentViewDao;
import com.zjw.graduation.view.post.PostContentView;
import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostContent;
import com.zjw.graduation.repository.post.PostContentDao;
import com.zjw.graduation.service.post.PostContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service("postContentService")
public class PostContentServiceImpl implements PostContentService  {

    @Autowired
    private PostContentDao postContentDao;

    @Autowired
    private PostContentViewDao postContentViewDao;

    public PagingResult<PostContentView> page(String truename, Long academyId, Long categoryId, int reviewState, int state, int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostContentView> page = postContentViewDao.findAll(truename, academyId, categoryId, reviewState, state,pageable);

        PagingResult<PostContentView> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public void enableOrDisable(Long id) {
        PostContent postContent = postContentDao.findById(id).orElse(null);
        if (postContent != null){
            postContent.setState(postContent.getState().equals(0) ? EnumStateType.NORMAL.getValue() : EnumStateType.DISABLE.getValue());
            postContent.setUpdated(LocalDateTime.now());
            postContentDao.save(postContent);
        }
    }

    @Override
    public void review(Long id, int reviewState) {
        PostContent postContent = postContentDao.findById(id).orElse(null);
        if (postContent != null){
            postContent.setReviewState(reviewState);
            postContent.setUpdated(LocalDateTime.now());
            postContentDao.save(postContent);
        }
    }

    @Override
    public void batchReview(String ids, int reviewState) {
        List<Long> collect =
                Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        LocalDateTime now = LocalDateTime.now();
        postContentDao.batchReview(collect, reviewState, now);
    }

    @Override
    public PostContent get(Long id) {
        return postContentDao.findById(id).orElse(new PostContent());
    }

    @Override
    public PostContent save(PostContent postContent) {
        return postContentDao.save(postContent);
    }

    @Override
    public PostContent update(PostContent postContent) {
        return postContentDao.save(postContent);
    }

    @Override
    public void delete(Long id) {
        PostContent postContent = postContentDao.findById(id).orElse(null);
        if (postContent != null){
            postContent.setLogicFlag(EnumLogicType.DELETE.getValue());
            postContentDao.save(postContent);
        }
    }

}
