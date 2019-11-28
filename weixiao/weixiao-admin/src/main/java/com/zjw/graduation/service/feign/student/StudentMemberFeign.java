package com.zjw.graduation.service.feign.student;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.dto.student.StudentMemberDto;
import com.zjw.graduation.model.student.StudentMemberCreateModel;
import com.zjw.graduation.mvc.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "weixiao-student", fallback = StudentMemberFeignFallback.class)
public interface StudentMemberFeign {

    @GetMapping("/student/studentMembers")
    public JsonResult<PagingResult<StudentMemberDto>> list(@RequestHeader("Authorization")String token,
                                                           @RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                           @RequestParam(value = "pagesize",defaultValue = "10")int pageSize);

    @GetMapping("/student/studentMember/{id}")
    public JsonResult<StudentMemberDto> detail(@RequestHeader("Authorization")String token,
                                               @PathVariable("id") Long id);

    @DeleteMapping("/student/studentMember/{id}")
    public JsonResult delete(@RequestHeader("Authorization")String token,
                             @PathVariable("id") Long id);

    @PutMapping("/student/studentMember/disable/{id}")
    public JsonResult disable(@RequestHeader("Authorization")String token,
                              @PathVariable("id") Long id);

    @PostMapping("/student/register")
    @ApiOperation("学生注册")
    public JsonResult<StudentMemberDto> create(@RequestHeader("Authorization")String token,
                                               @Validated @RequestBody StudentMemberCreateModel studentMemberCreateModel);
}
