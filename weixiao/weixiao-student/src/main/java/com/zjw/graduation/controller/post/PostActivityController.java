package com.zjw.graduation.controller.post;


import com.zjw.graduation.service.post.PostActivityService;
import com.zjw.graduation.model.post.PostActivityCreateModel;
import com.zjw.graduation.model.post.PostActivityUpdateModel;
import com.zjw.graduation.entity.post.PostActivity;
import com.zjw.graduation.dto.post.PostActivityDto;
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
 * 活动发布表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:15:39
 */
@RestController("PostActivityController")
@RequestMapping("/post")
@Api(value = "post.PostActivityController", tags = {"活动发布表"})
public class PostActivityController {

    @Autowired
    private PostActivityService postActivityService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/postActivitys")
    @ApiOperation("活动发布表列表")
    public JsonResult<PagingResult<PostActivityDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<PostActivity> page = postActivityService.page(pageIndex, pageSize);
        PagingResult<PostActivityDto> convert = page.convert(item -> {
            PostActivityDto postActivityDto = new PostActivityDto();
            BeanUtils.copyProperties(item, postActivityDto);
            return postActivityDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/postActivity/{id}")
    @ApiOperation("活动发布表详情")
    public JsonResult<PostActivityDto> detail(@PathVariable("id") Long id) {

        PostActivity postActivity = postActivityService.get(id);

        PostActivityDto postActivityDto = new PostActivityDto();
        BeanUtils.copyProperties(postActivity, postActivityDto);

        return JsonResult.success(postActivityDto);
    }

    /**
     * 新增
     *
     * @param postActivityCreateModel
     * @return
     */
    @PostMapping("/postActivity")
    @ApiOperation("活动发布表新增")
    public JsonResult<PostActivityDto> create(@Validated @RequestBody PostActivityCreateModel postActivityCreateModel) {

        PostActivity postActivity = new PostActivity();
        BeanUtils.copyProperties(postActivityCreateModel, postActivity);
        postActivityService.save(postActivity);

        PostActivityDto postActivityDto = new PostActivityDto();
        BeanUtils.copyProperties(postActivity, postActivityDto);

        return JsonResult.success(postActivityDto);

    }

    /**
     * 修改
     *
     * @param postActivityUpdateModel
     * @return
     */
    @PutMapping("/postActivity")
    @ApiOperation("活动发布表修改")
    public JsonResult<PostActivity> update(@Validated @RequestBody PostActivityUpdateModel postActivityUpdateModel) {

        PostActivity postActivity = postActivityService.get(postActivityUpdateModel.getId());
        if (postActivity.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(postActivityUpdateModel, postActivity, NullPropertyUtils.getNullPropertyNames(postActivityUpdateModel));
        postActivity.setUpdated(LocalDateTime.now());
        PostActivity entity = postActivityService.update(postActivity);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/postActivity/{id}")
    @ApiOperation("活动发布表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        postActivityService.delete(id);

        return JsonResult.success("删除成功");
    }

}
