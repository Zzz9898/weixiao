package com.zjw.graduation.service.student;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.student.StudentReport;

/**
 * 举报表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-27 10:50:34
 */
public interface StudentReportService {
    PagingResult<StudentReport> page(int pageIndex, int pageSize);

    StudentReport get(Long id);

    StudentReport save(StudentReport Admin);

    StudentReport update(StudentReport Admin);

    void delete(Long id);
}

