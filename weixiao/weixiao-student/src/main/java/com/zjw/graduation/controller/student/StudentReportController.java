package com.zjw.graduation.controller.student;


import com.zjw.graduation.service.student.StudentReportService;
import com.zjw.graduation.model.student.StudentReportCreateModel;
import com.zjw.graduation.model.student.StudentReportUpdateModel;
import com.zjw.graduation.entity.student.StudentReport;
import com.zjw.graduation.dto.student.StudentReportDto;
import com.zjw.graduation.data.NullPropertyUtils;
import com.zjw.graduation.mvc.JsonResult;
import com.zjw.graduation.data.PagingResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;


/**
 * 举报表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:30:19
 */
@RestController("StudentReportController")
@RequestMapping("/student")
@Api(value = "student.StudentReportController", tags = {"举报表"})
public class StudentReportController {

    @Autowired
    private StudentReportService studentReportService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/studentReports")
    @ApiOperation("举报表列表")
    public JsonResult<PagingResult<StudentReportDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<StudentReport> page = studentReportService.page(pageIndex, pageSize);
        PagingResult<StudentReportDto> convert = page.convert(item -> {
            StudentReportDto studentReportDto = new StudentReportDto();
            BeanUtils.copyProperties(item, studentReportDto);
            return studentReportDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/studentReport/{id}")
    @ApiOperation("举报表详情")
    public JsonResult<StudentReportDto> detail(@PathVariable("id") Long id) {

        StudentReport studentReport = studentReportService.get(id);

        StudentReportDto studentReportDto = new StudentReportDto();
        BeanUtils.copyProperties(studentReport, studentReportDto);

        return JsonResult.success(studentReportDto);
    }

    /**
     * 新增
     *
     * @param studentReportCreateModel
     * @return
     */
    @PostMapping("/studentReport")
    @ApiOperation("举报表新增")
    public JsonResult<StudentReportDto> create(@Validated @RequestBody StudentReportCreateModel studentReportCreateModel) {

        StudentReport studentReport = new StudentReport();
        BeanUtils.copyProperties(studentReportCreateModel, studentReport);
        studentReportService.save(studentReport);

        StudentReportDto studentReportDto = new StudentReportDto();
        BeanUtils.copyProperties(studentReport, studentReportDto);

        return JsonResult.success(studentReportDto);

    }

    /**
     * 修改
     *
     * @param studentReportUpdateModel
     * @return
     */
    @PutMapping("/studentReport")
    @ApiOperation("举报表修改")
    public JsonResult<StudentReport> update(@Validated @RequestBody StudentReportUpdateModel studentReportUpdateModel) {

        StudentReport studentReport = studentReportService.get(studentReportUpdateModel.getId());
        if (studentReport.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(studentReportUpdateModel, studentReport, NullPropertyUtils.getNullPropertyNames(studentReportUpdateModel));
        studentReport.setUpdated(LocalDateTime.now());
        StudentReport entity = studentReportService.update(studentReport);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/studentReport/{id}")
    @ApiOperation("举报表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        studentReportService.delete(id);

        return JsonResult.success("删除成功");
    }

}
