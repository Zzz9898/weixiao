package com.zjw.graduation.controller.post;


import com.zjw.graduation.service.post.PostSignService;
import com.zjw.graduation.model.post.PostSignCreateModel;
import com.zjw.graduation.model.post.PostSignUpdateModel;
import com.zjw.graduation.entity.post.PostSign;
import com.zjw.graduation.dto.post.PostSignDto;
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
 * 活动报名表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:09:07
 */
@RestController("PostSignController")
@RequestMapping("/post")
@Api(value = "post.PostSignController", tags = {"活动报名表"})
public class PostSignController {

    @Autowired
    private PostSignService postSignService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/postSigns")
    @ApiOperation("活动报名表列表")
    public JsonResult<PagingResult<PostSignDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<PostSign> page = postSignService.page(pageIndex, pageSize);
        PagingResult<PostSignDto> convert = page.convert(item -> {
            PostSignDto postSignDto = new PostSignDto();
            BeanUtils.copyProperties(item, postSignDto);
            return postSignDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/postSign/{id}")
    @ApiOperation("活动报名表详情")
    public JsonResult<PostSignDto> detail(@PathVariable("id") Long id) {

        PostSign postSign = postSignService.get(id);

        PostSignDto postSignDto = new PostSignDto();
        BeanUtils.copyProperties(postSign, postSignDto);

        return JsonResult.success(postSignDto);
    }

    /**
     * 新增
     *
     * @param postSignCreateModel
     * @return
     */
    @PostMapping("/postSign")
    @ApiOperation("活动报名表新增")
    public JsonResult<PostSignDto> create(@Validated @RequestBody PostSignCreateModel postSignCreateModel) {

        PostSign postSign = new PostSign();
        BeanUtils.copyProperties(postSignCreateModel, postSign);
        postSignService.save(postSign);

        PostSignDto postSignDto = new PostSignDto();
        BeanUtils.copyProperties(postSign, postSignDto);

        return JsonResult.success(postSignDto);

    }

    /**
     * 修改
     *
     * @param postSignUpdateModel
     * @return
     */
    @PutMapping("/postSign")
    @ApiOperation("活动报名表修改")
    public JsonResult<PostSign> update(@Validated @RequestBody PostSignUpdateModel postSignUpdateModel) {

        PostSign postSign = postSignService.get(postSignUpdateModel.getId());
        if (postSign.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(postSignUpdateModel, postSign, NullPropertyUtils.getNullPropertyNames(postSignUpdateModel));
        postSign.setUpdated(LocalDateTime.now());
        PostSign entity = postSignService.update(postSign);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/postSign/{id}")
    @ApiOperation("活动报名表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        postSignService.delete(id);

        return JsonResult.success("删除成功");
    }

}
