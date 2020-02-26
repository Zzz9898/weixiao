package com.zjw.graduation.controller.post;


import com.zjw.graduation.service.post.PostCommantService;
import com.zjw.graduation.model.post.PostCommantCreateModel;
import com.zjw.graduation.model.post.PostCommantUpdateModel;
import com.zjw.graduation.entity.post.PostCommant;
import com.zjw.graduation.dto.post.PostCommantDto;
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
 * 
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-26 16:27:41
 */
@RestController("PostCommantController")
@RequestMapping("/post")
@Api(value = "post.PostCommantController", tags = {""})
public class PostCommantController {

    @Autowired
    private PostCommantService postCommantService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/postCommants")
    @ApiOperation("列表")
    public JsonResult<PagingResult<PostCommantDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<PostCommant> page = postCommantService.page(pageIndex, pageSize);
        PagingResult<PostCommantDto> convert = page.convert(item -> {
            PostCommantDto postCommantDto = new PostCommantDto();
            BeanUtils.copyProperties(item, postCommantDto);
            return postCommantDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/postCommant/{id}")
    @ApiOperation("详情")
    public JsonResult<PostCommantDto> detail(@PathVariable("id") Long id) {

        PostCommant postCommant = postCommantService.get(id);

        PostCommantDto postCommantDto = new PostCommantDto();
        BeanUtils.copyProperties(postCommant, postCommantDto);

        return JsonResult.success(postCommantDto);
    }

    /**
     * 新增
     *
     * @param postCommantCreateModel
     * @return
     */
    @PostMapping("/postCommant")
    @ApiOperation("新增")
    public JsonResult<PostCommantDto> create(@Validated @RequestBody PostCommantCreateModel postCommantCreateModel) {

        PostCommant postCommant = new PostCommant();
        BeanUtils.copyProperties(postCommantCreateModel, postCommant);
        postCommantService.save(postCommant);

        PostCommantDto postCommantDto = new PostCommantDto();
        BeanUtils.copyProperties(postCommant, postCommantDto);

        return JsonResult.success(postCommantDto);

    }

    /**
     * 修改
     *
     * @param postCommantUpdateModel
     * @return
     */
    @PutMapping("/postCommant")
    @ApiOperation("修改")
    public JsonResult<PostCommant> update(@Validated @RequestBody PostCommantUpdateModel postCommantUpdateModel) {

        PostCommant postCommant = postCommantService.get(postCommantUpdateModel.getId());
        if (postCommant.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(postCommantUpdateModel, postCommant, NullPropertyUtils.getNullPropertyNames(postCommantUpdateModel));
        postCommant.setUpdated(LocalDateTime.now());
        PostCommant entity = postCommantService.update(postCommant);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/postCommant/{id}")
    @ApiOperation("删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        postCommantService.delete(id);

        return JsonResult.success("删除成功");
    }

}
