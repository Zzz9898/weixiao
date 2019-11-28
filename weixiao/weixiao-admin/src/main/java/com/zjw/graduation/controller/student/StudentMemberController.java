package com.zjw.graduation.controller.student;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.dto.student.StudentMemberDto;
import com.zjw.graduation.mvc.JsonResult;
import com.zjw.graduation.service.feign.student.StudentMemberFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "student.StudentMemberController", tags = "学生管理")
public class StudentMemberController {

    @Autowired
    private StudentMemberFeign studentMemberFeign;

    @GetMapping("/student/studentMembers")
    @ApiOperation("学生列表")
    public JsonResult<PagingResult<StudentMemberDto>> list(@RequestHeader("Authorization")String token,
                                                           @RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                           @RequestParam(value = "pagesize",defaultValue = "10")int pageSize){
        return studentMemberFeign.list(token, pageIndex, pageSize);
    }

    @GetMapping("/studentMember/{id}")
    @ApiOperation("学生详情")
    public JsonResult<StudentMemberDto> detail(@RequestHeader("Authorization")String token,
                                               @PathVariable("id") Long id) {
        return studentMemberFeign.detail(token, id);
    }

    @DeleteMapping("/studentMember/{id}")
    @ApiOperation("学生删除")
    public JsonResult delete(@RequestHeader("Authorization")String token,
                             @PathVariable("id") Long id){
        return studentMemberFeign.delete(token, id);
    }

    @PutMapping("/studentMember/disable/{id}")
    @ApiOperation("学生禁用")
    public JsonResult disable(@RequestHeader("Authorization")String token,
                              @PathVariable("id") Long id){
        studentMemberFeign.disable(token, id);
        return JsonResult.success("禁用成功");
    }
}
