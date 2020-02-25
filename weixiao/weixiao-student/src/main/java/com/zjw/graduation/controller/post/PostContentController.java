package com.zjw.graduation.controller.post;


import com.zjw.graduation.service.post.PostContentService;
import com.zjw.graduation.model.post.PostContentCreateModel;
import com.zjw.graduation.model.post.PostContentUpdateModel;
import com.zjw.graduation.entity.post.PostContent;
import com.zjw.graduation.dto.post.PostContentDto;
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
 * 发布内容表
 *
 * @author zjw
 * @email zhangjw9898@qq.com
 * @date 2020-02-25 17:09:07
 */
@RestController("PostContentController")
@RequestMapping("/post")
@Api(value = "post.PostContentController", tags = {"发布内容表"})
public class PostContentController {

    @Autowired
    private PostContentService postContentService;

    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/postContents")
    @ApiOperation("发布内容表列表")
    public JsonResult<PagingResult<PostContentDto>> list(@RequestParam(value = "pageindex",defaultValue = "0")int pageIndex,
                                                          @RequestParam(value = "pagesize",defaultValue = "10")int pageSize) {

        PagingResult<PostContent> page = postContentService.page(pageIndex, pageSize);
        PagingResult<PostContentDto> convert = page.convert(item -> {
            PostContentDto postContentDto = new PostContentDto();
            BeanUtils.copyProperties(item, postContentDto);
            return postContentDto;
        });
        return JsonResult.success(convert);
    }


    /**
     * 详情
     *
     * @param id
     * @return
     */
    @GetMapping("/postContent/{id}")
    @ApiOperation("发布内容表详情")
    public JsonResult<PostContentDto> detail(@PathVariable("id") Long id) {

        PostContent postContent = postContentService.get(id);

        PostContentDto postContentDto = new PostContentDto();
        BeanUtils.copyProperties(postContent, postContentDto);

        return JsonResult.success(postContentDto);
    }

    /**
     * 新增
     *
     * @param postContentCreateModel
     * @return
     */
    @PostMapping("/postContent")
    @ApiOperation("发布内容表新增")
    public JsonResult<PostContentDto> create(@Validated @RequestBody PostContentCreateModel postContentCreateModel) {

        PostContent postContent = new PostContent();
        BeanUtils.copyProperties(postContentCreateModel, postContent);
        postContentService.save(postContent);

        PostContentDto postContentDto = new PostContentDto();
        BeanUtils.copyProperties(postContent, postContentDto);

        return JsonResult.success(postContentDto);

    }

    /**
     * 修改
     *
     * @param postContentUpdateModel
     * @return
     */
    @PutMapping("/postContent")
    @ApiOperation("发布内容表修改")
    public JsonResult<PostContent> update(@Validated @RequestBody PostContentUpdateModel postContentUpdateModel) {

        PostContent postContent = postContentService.get(postContentUpdateModel.getId());
        if (postContent.getId() == null){
            return JsonResult.error("Not find entity");
        }
        BeanUtils.copyProperties(postContentUpdateModel, postContent, NullPropertyUtils.getNullPropertyNames(postContentUpdateModel));
        postContent.setUpdated(LocalDateTime.now());
        PostContent entity = postContentService.update(postContent);

        return JsonResult.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteMapping("/postContent/{id}")
    @ApiOperation("发布内容表删除")
    public JsonResult delete(@PathVariable("id") Long id) {

        postContentService.delete(id);

        return JsonResult.success("删除成功");
    }

}
