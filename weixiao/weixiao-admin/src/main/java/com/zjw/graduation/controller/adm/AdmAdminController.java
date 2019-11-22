package com.zjw.graduation.controller.adm;


import com.zjw.graduation.data.NullPropertyUtils;
import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.dto.adm.AdmAdminDto;
import com.zjw.graduation.entity.adm.AdmAdmin;
import com.zjw.graduation.entity.adm.AdmPermission;
import com.zjw.graduation.model.adm.AdmAdminCreateModel;
import com.zjw.graduation.model.adm.AdmAdminLoginModel;
import com.zjw.graduation.model.adm.AdmAdminUpdateModel;
import com.zjw.graduation.mvc.JsonResult;
import com.zjw.graduation.service.adm.AdmAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    private final static Logger LOGGER = LoggerFactory.getLogger(AdmAdminController.class);

    @Autowired
    private AdmAdminService admAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "添加管理员")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('adm:admin:create')")
    public JsonResult<AdmAdminDto> adminAdd(@RequestBody AdmAdminCreateModel model, BindingResult result) {
        AdmAdmin admAdmin = admAdminService.adminAdd(model);
        if (admAdmin == null) {
            return JsonResult.error("register fail");
        }
        AdmAdminDto admAdminDto = new AdmAdminDto();
        BeanUtils.copyProperties(admAdmin, admAdminDto);
        return JsonResult.success(admAdminDto);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public JsonResult login(@RequestBody AdmAdminLoginModel model, BindingResult result) {
        LOGGER.info("model.getUsername() = {}, " , model.getUsername());
        String token = admAdminService.login(model.getUsername(), model.getPassword());
        if (token == null) {
            return JsonResult.error("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return JsonResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @GetMapping("/permission/{adminId}")
    public JsonResult<List<AdmPermission>> getPermissionList(@PathVariable Long adminId) {
        List<AdmPermission> permissionList = admAdminService.getPermissionList(adminId);
        return JsonResult.success(permissionList);
    }

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
