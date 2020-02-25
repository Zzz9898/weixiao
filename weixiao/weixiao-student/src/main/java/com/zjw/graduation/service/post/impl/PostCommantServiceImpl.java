package com.zjw.graduation.service.post.impl;

import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.post.PostCommant;
import com.zjw.graduation.repository.post.PostCommantDao;
import com.zjw.graduation.service.post.PostCommantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("postCommantService")
public class PostCommantServiceImpl implements PostCommantService  {

    @Autowired
    private PostCommantDao postCommantDao;

    public PagingResult<PostCommant> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<PostCommant> page = postCommantDao.findAll(pageable);

        PagingResult<PostCommant> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public PostCommant get(Long id) {
        return postCommantDao.findById(id).orElse(new PostCommant());
    }

    @Override
    public PostCommant save(PostCommant postCommant) {
        return postCommantDao.save(postCommant);
    }

    @Override
    public PostCommant update(PostCommant postCommant) {
        return postCommantDao.save(postCommant);
    }

    @Override
    public void delete(Long id) {
        PostCommant postCommant = postCommantDao.findById(id).orElse(null);
        if (postCommant != null){
            postCommant.setLogicFlag(EnumLogicType.DELETE.getValue());
            postCommantDao.save(postCommant);
        }
    }

}
