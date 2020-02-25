package com.zjw.graduation.service.post.impl;

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


@Service("postContentService")
public class PostContentServiceImpl implements PostContentService  {

    @Autowired
    private PostContentDao postContentDao;

    public PagingResult<PostContent> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostContent> page = postContentDao.findAll(pageable);

        PagingResult<PostContent> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
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
