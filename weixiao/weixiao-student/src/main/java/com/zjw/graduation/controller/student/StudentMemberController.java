package com.zjw.graduation.controller.student;


import com.zjw.graduation.data.NullPropertyUtils;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.dto.student.StudentMemberDto;
import com.zjw.graduation.entity.student.StudentMember;
import com.zjw.graduation.model.student.StudentMemberCreateModel;
import com.zjw.graduation.model.student.StudentMemberLoginModel;
import com.zjw.graduation.model.student.StudentMemberUpdateModel;
import com.zjw.graduation.mvc.JsonResult;
import com.zjw.graduation.service.student.StudentMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


/**
 * 学生表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-25 10:30:03
 */
@RestController("StudentMemberController")
@RequestMapping("/student")
@Api(value = "student.StudentMemberController", tags = {"学生表"})
public class StudentMemberController {

    private final static Logger LOGGER = LoggerFactory.getLogger(StudentMemberController.class);

    @Autowired
    private StudentMemberService studentMemberService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "登录以后返回token")
    @PostMapping("/login")
    public JsonResult login(@RequestBody StudentMemberLoginModel model, BindingResult result) {
        LOGGER.info("model.getUsername() = {}, " , model.getUsername());
        String token = studentMemberService.login(model.getUsername(), model.getPassword());
        if (token == null) {
            return JsonResult.error("用户名或密码错误");
        }
        if (token.equals("fail")){
            return JsonResult.error("账号被禁用");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return JsonResult.success(tokenMap);
    }
    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/studentMembers")
    @ApiOperation("学生表列表")
    public JsonResult<PagingResult<StudentMemberDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<StudentMember> page = studentMemberService.page(pageIndex, pageSize);
        PagingResult<StudentMemberDto> convert = page.convert(item -> {
            StudentMemberDto studentMemberDto = new StudentMemberDto();
            BeanUtils.copyProperties(item, studentMemberDto);
            return studentMemberDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/studentMember/{id}")
    @ApiOperation("学生表详情")
    public JsonResult<StudentMemberDto> detail(@PathVariable("id") Long id) {

        StudentMember studentMember = studentMemberService.get(id);

        StudentMemberDto studentMemberDto = new StudentMemberDto();
        BeanUtils.copyProperties(studentMember, studentMemberDto);

        return JsonResult.success(studentMemberDto);
    }

    /**
     * 新增
     *
     * @param studentMemberCreateModel
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("学生注册")
    public JsonResult<StudentMemberDto> create(@Validated @RequestBody StudentMemberCreateModel studentMemberCreateModel) {

        StudentMember studentMember = new StudentMember();
        BeanUtils.copyProperties(studentMemberCreateModel, studentMember, NullPropertyUtils.getNullPropertyNames(studentMemberCreateModel));
        StudentMember save = studentMemberService.save(studentMember);
        if (save == null){
            return JsonResult.error("用户名已存在！！！");
        }

        StudentMemberDto studentMemberDto = new StudentMemberDto();
        BeanUtils.copyProperties(studentMember, studentMemberDto);

        return JsonResult.success(studentMemberDto);

    }

    @PostMapping("/canregister")
    @ApiOperation("判断用户名是否存在")
    public JsonResult canregister(@RequestParam("username")String username){
        StudentMember studentByUsername = studentMemberService.getStudentByUsername(username);
        if (studentByUsername == null){
            return JsonResult.success("可以注册！！！");
        }
        return JsonResult.error("用户已存在！！！");
    }

    /**
     * 修改
     *
     * @param studentMemberUpdateModel
     * @return
     */
    @PutMapping("/studentMember")
    @ApiOperation("学生表修改")
    public JsonResult<StudentMember> update(@Validated @RequestBody StudentMemberUpdateModel studentMemberUpdateModel) {

        StudentMember studentMember = studentMemberService.get(studentMemberUpdateModel.getId());
        if (studentMember.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(studentMemberUpdateModel, studentMember, NullPropertyUtils.getNullPropertyNames(studentMemberUpdateModel));
        studentMember.setUpdated(LocalDateTime.now());
        StudentMember entity = studentMemberService.update(studentMember);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/studentMember/{id}")
    @ApiOperation("学生表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        studentMemberService.delete(id);

        return JsonResult.success("删除成功");
    }

    @PutMapping("/studentMember/disable/{id}")
    @ApiOperation("学生表禁用")
    public JsonResult disable(@PathVariable("id") Long id){
        studentMemberService.disable(id);
        return JsonResult.success("禁用成功");
    }
}
