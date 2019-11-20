package com.zjw.graduation.controller.adm;


import com.zjw.graduation.service.adm.AdmAdminService;
import com.zjw.graduation.model.adm.AdmAdminCreateModel;
import com.zjw.graduation.model.adm.AdmAdminUpdateModel;
import com.zjw.graduation.entity.adm.AdmAdmin;
import com.zjw.graduation.dto.adm.AdmAdminDto;
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
 * 后台用户表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-20 17:23:15
 */
@RestController("AdmAdminController")
@RequestMapping("/adm")
@Api(value = "adm.AdmAdminController", tags = {"后台用户表"})
public class AdmAdminController {

    @Autowired
    private AdmAdminService admAdminService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/admAdmins")
    @ApiOperation("后台用户表列表")
    public JsonResult<PagingResult<AdmAdminDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<AdmAdmin> page = admAdminService.page(pageIndex, pageSize);
        PagingResult<AdmAdminDto> convert = page.convert(item -> {
            AdmAdminDto admAdminDto = new AdmAdminDto();
            BeanUtils.copyProperties(item, admAdminDto);
            return admAdminDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/admAdmin/{id}")
    @ApiOperation("后台用户表详情")
    public JsonResult<AdmAdminDto> detail(@PathVariable("id") Long id) {

        AdmAdmin admAdmin = admAdminService.get(id);

        AdmAdminDto admAdminDto = new AdmAdminDto();
        BeanUtils.copyProperties(admAdmin, admAdminDto);

        return JsonResult.success(admAdminDto);
    }

    /**
     * 新增
     *
     * @param admAdminCreateModel
     * @return
     */
    @PostMapping("/admAdmin")
    @ApiOperation("后台用户表新增")
    public JsonResult<AdmAdminDto> create(@Validated @RequestBody AdmAdminCreateModel admAdminCreateModel) {

        AdmAdmin admAdmin = new AdmAdmin();
        BeanUtils.copyProperties(admAdminCreateModel, admAdmin);
        admAdminService.save(admAdmin);

        AdmAdminDto admAdminDto = new AdmAdminDto();
        BeanUtils.copyProperties(admAdmin, admAdminDto);

        return JsonResult.success(admAdminDto);

    }

    /**
     * 修改
     *
     * @param admAdminUpdateModel
     * @return
     */
    @PutMapping("/admAdmin")
    @ApiOperation("后台用户表修改")
    public JsonResult<AdmAdmin> update(@Validated @RequestBody AdmAdminUpdateModel admAdminUpdateModel) {

        AdmAdmin admAdmin = admAdminService.get(admAdminUpdateModel.getId());
        if (admAdmin.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(admAdminUpdateModel, admAdmin, NullPropertyUtils.getNullPropertyNames(admAdminUpdateModel));
        admAdmin.setUpdated(LocalDateTime.now());
        AdmAdmin entity = admAdminService.save(admAdmin);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/admAdmin/{id}")
    @ApiOperation("后台用户表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        admAdminService.delete(id);

        return JsonResult.success("删除成功");
    }

}
