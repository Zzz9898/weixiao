package com.zjw.graduation.service.feign.student;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.dto.student.StudentMemberDto;
import com.zjw.graduation.model.student.StudentMemberCreateModel;
import com.zjw.graduation.mvc.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StudentMemberFeignFallback implements StudentMemberFeign {

    private final static Logger logger = LoggerFactory.getLogger(StudentMemberFeignFallback.class);

    @Override
    public JsonResult<PagingResult<StudentMemberDto>> list(String token, int pageIndex, int pageSize) {
        logger.info("服务没有启动成功...");
        return JsonResult.error("服务没有启动成功...");
    }

    @Override
    public JsonResult<StudentMemberDto> detail(String token, Long id) {
        logger.info("服务没有启动成功...");
        return JsonResult.error("服务没有启动成功...");
    }

    @Override
    public JsonResult delete(String token, Long id) {
        logger.info("服务没有启动成功...");
        return JsonResult.error("服务没有启动成功...");
    }

    @Override
    public JsonResult disable(String token, Long id) {
        logger.info("服务没有启动成功...");
        return JsonResult.error("服务没有启动成功...");
    }

    @Override
    public JsonResult<StudentMemberDto> create(String token, StudentMemberCreateModel studentMemberCreateModel) {
        logger.info("服务没有启动成功...");
        return JsonResult.error("服务没有启动成功...");
    }

    @Override
    public boolean check(String token, String username) {
        logger.info("服务没有启动成功...");
        return false;
    }
}
