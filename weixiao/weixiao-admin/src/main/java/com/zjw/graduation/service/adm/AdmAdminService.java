package com.zjw.graduation.service.adm;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.adm.AdmAdmin;

/**
 * 后台用户表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-20 17:23:15
 */
public interface AdmAdminService {
    PagingResult<AdmAdmin> page(int pageIndex, int pageSize);

    AdmAdmin get(Long id);

    AdmAdmin save(AdmAdmin Admin);

    void delete(Long id);
}

