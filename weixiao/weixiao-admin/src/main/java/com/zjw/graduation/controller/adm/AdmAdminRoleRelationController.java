package com.zjw.graduation.controller.adm;


import com.zjw.graduation.service.adm.AdmAdminRoleRelationService;
import com.zjw.graduation.model.adm.AdmAdminRoleRelationCreateModel;
import com.zjw.graduation.model.adm.AdmAdminRoleRelationUpdateModel;
import com.zjw.graduation.entity.adm.AdmAdminRoleRelation;
import com.zjw.graduation.dto.adm.AdmAdminRoleRelationDto;
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
 * 后台用户和角色关系表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2019-11-20 17:23:16
 */
@RestController("AdmAdminRoleRelationController")
@RequestMapping("/adm")
@Api(value = "adm.AdmAdminRoleRelationController", tags = {"后台用户和角色关系表"})
public class AdmAdminRoleRelationController {

    @Autowired
    private AdmAdminRoleRelationService admAdminRoleRelationService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/admAdminRoleRelations")
    @ApiOperation("后台用户和角色关系表列表")
    public JsonResult<PagingResult<AdmAdminRoleRelationDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<AdmAdminRoleRelation> page = admAdminRoleRelationService.page(pageIndex, pageSize);
        PagingResult<AdmAdminRoleRelationDto> convert = page.convert(item -> {
            AdmAdminRoleRelationDto admAdminRoleRelationDto = new AdmAdminRoleRelationDto();
            BeanUtils.copyProperties(item, admAdminRoleRelationDto);
            return admAdminRoleRelationDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/admAdminRoleRelation/{id}")
    @ApiOperation("后台用户和角色关系表详情")
    public JsonResult<AdmAdminRoleRelationDto> detail(@PathVariable("id") Long id) {

        AdmAdminRoleRelation admAdminRoleRelation = admAdminRoleRelationService.get(id);

        AdmAdminRoleRelationDto admAdminRoleRelationDto = new AdmAdminRoleRelationDto();
        BeanUtils.copyProperties(admAdminRoleRelation, admAdminRoleRelationDto);

        return JsonResult.success(admAdminRoleRelationDto);
    }

    /**
     * 新增
     *
     * @param admAdminRoleRelationCreateModel
     * @return
     */
    @PostMapping("/admAdminRoleRelation")
    @ApiOperation("后台用户和角色关系表新增")
    public JsonResult<AdmAdminRoleRelationDto> create(@Validated @RequestBody AdmAdminRoleRelationCreateModel admAdminRoleRelationCreateModel) {

        AdmAdminRoleRelation admAdminRoleRelation = new AdmAdminRoleRelation();
        BeanUtils.copyProperties(admAdminRoleRelationCreateModel, admAdminRoleRelation);
        admAdminRoleRelationService.save(admAdminRoleRelation);

        AdmAdminRoleRelationDto admAdminRoleRelationDto = new AdmAdminRoleRelationDto();
        BeanUtils.copyProperties(admAdminRoleRelation, admAdminRoleRelationDto);

        return JsonResult.success(admAdminRoleRelationDto);

    }

    /**
     * 修改
     *
     * @param admAdminRoleRelationUpdateModel
     * @return
     */
    @PutMapping("/admAdminRoleRelation")
    @ApiOperation("后台用户和角色关系表修改")
    public JsonResult<AdmAdminRoleRelation> update(@Validated @RequestBody AdmAdminRoleRelationUpdateModel admAdminRoleRelationUpdateModel) {

        AdmAdminRoleRelation admAdminRoleRelation = admAdminRoleRelationService.get(admAdminRoleRelationUpdateModel.getId());
        if (admAdminRoleRelation.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(admAdminRoleRelationUpdateModel, admAdminRoleRelation, NullPropertyUtils.getNullPropertyNames(admAdminRoleRelationUpdateModel));
        admAdminRoleRelation.setUpdated(LocalDateTime.now());
        AdmAdminRoleRelation entity = admAdminRoleRelationService.save(admAdminRoleRelation);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/admAdminRoleRelation/{id}")
    @ApiOperation("后台用户和角色关系表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        admAdminRoleRelationService.delete(id);

        return JsonResult.success("删除成功");
    }

}
