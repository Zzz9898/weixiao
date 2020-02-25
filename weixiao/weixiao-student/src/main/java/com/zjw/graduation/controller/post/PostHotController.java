package com.zjw.graduation.controller.post;


import com.zjw.graduation.service.post.PostHotService;
import com.zjw.graduation.model.post.PostHotCreateModel;
import com.zjw.graduation.model.post.PostHotUpdateModel;
import com.zjw.graduation.entity.post.PostHot;
import com.zjw.graduation.dto.post.PostHotDto;
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
 * 热点内容表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:02:40
 */
@RestController("PostHotController")
@RequestMapping("/post")
@Api(value = "post.PostHotController", tags = {"热点内容表"})
public class PostHotController {

    @Autowired
    private PostHotService postHotService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/postHots")
    @ApiOperation("热点内容表列表")
    public JsonResult<PagingResult<PostHotDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<PostHot> page = postHotService.page(pageIndex, pageSize);
        PagingResult<PostHotDto> convert = page.convert(item -> {
            PostHotDto postHotDto = new PostHotDto();
            BeanUtils.copyProperties(item, postHotDto);
            return postHotDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/postHot/{id}")
    @ApiOperation("热点内容表详情")
    public JsonResult<PostHotDto> detail(@PathVariable("id") Long id) {

        PostHot postHot = postHotService.get(id);

        PostHotDto postHotDto = new PostHotDto();
        BeanUtils.copyProperties(postHot, postHotDto);

        return JsonResult.success(postHotDto);
    }

    /**
     * 新增
     *
     * @param postHotCreateModel
     * @return
     */
    @PostMapping("/postHot")
    @ApiOperation("热点内容表新增")
    public JsonResult<PostHotDto> create(@Validated @RequestBody PostHotCreateModel postHotCreateModel) {

        PostHot postHot = new PostHot();
        BeanUtils.copyProperties(postHotCreateModel, postHot);
        postHotService.save(postHot);

        PostHotDto postHotDto = new PostHotDto();
        BeanUtils.copyProperties(postHot, postHotDto);

        return JsonResult.success(postHotDto);

    }

    /**
     * 修改
     *
     * @param postHotUpdateModel
     * @return
     */
    @PutMapping("/postHot")
    @ApiOperation("热点内容表修改")
    public JsonResult<PostHot> update(@Validated @RequestBody PostHotUpdateModel postHotUpdateModel) {

        PostHot postHot = postHotService.get(postHotUpdateModel.getId());
        if (postHot.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(postHotUpdateModel, postHot, NullPropertyUtils.getNullPropertyNames(postHotUpdateModel));
        postHot.setUpdated(LocalDateTime.now());
        PostHot entity = postHotService.update(postHot);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/postHot/{id}")
    @ApiOperation("热点内容表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        postHotService.delete(id);

        return JsonResult.success("删除成功");
    }

}
