package com.zjw.graduation.service.student;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.student.StudentMember;
import com.zjw.graduation.entity.student.StudentPermission;

import java.util.List;

/**
 * 学生表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-25 10:30:03
 */
public interface StudentMemberService {
    PagingResult<StudentMember> page(int pageIndex, int pageSize);

    StudentMember get(Long id);

    StudentMember save(StudentMember Admin);

    void delete(Long id);

    StudentMember getStudentByUsername(String username);

    List<StudentPermission> getPermissionList(Long id);

    String login(String username, String password);

    StudentMember update(StudentMember studentMember);

    void disable(Long id);
}

