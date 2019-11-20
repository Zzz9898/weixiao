package com.zjw.graduation.service.adm.impl;

import org.springframework.data.domain.Page;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.entity.adm.AdmAdmin;
import com.zjw.graduation.repository.adm.AdmAdminDao;
import com.zjw.graduation.service.adm.AdmAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service("admAdminService")
public class AdmAdminServiceImpl implements AdmAdminService  {

    @Autowired
    private AdmAdminDao admAdminDao;

    public PagingResult<AdmAdmin> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<AdmAdmin> page = admAdminDao.findAll(pageable);

        PagingResult<AdmAdmin> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public AdmAdmin get(Long id) {
        return admAdminDao.findById(id).orElse(new AdmAdmin());
    }

    @Override
    public AdmAdmin save(AdmAdmin admAdmin) {
        return admAdminDao.save(admAdmin);
    }

    @Override
    public void delete(Long id) {
        AdmAdmin admAdmin = admAdminDao.findById(id).orElse(null);
        if (admAdmin != null){
            admAdmin.setLogicFlag(EnumLogicType.DELETE.getValue());
            admAdminDao.save(admAdmin);
        }
    }

}
