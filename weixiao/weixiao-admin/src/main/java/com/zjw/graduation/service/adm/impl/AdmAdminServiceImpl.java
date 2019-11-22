package com.zjw.graduation.service.adm.impl;

import com.zjw.graduation.data.PagingResult;
import com.zjw.graduation.entity.adm.AdmAdmin;
import com.zjw.graduation.entity.adm.AdmAdminRoleRelation;
import com.zjw.graduation.entity.adm.AdmPermission;
import com.zjw.graduation.entity.adm.AdmRolePermissionRelation;
import com.zjw.graduation.enums.EnumLogicType;
import com.zjw.graduation.model.adm.AdmAdminCreateModel;
import com.zjw.graduation.repository.adm.AdmAdminDao;
import com.zjw.graduation.repository.adm.AdmAdminRoleRelationDao;
import com.zjw.graduation.repository.adm.AdmPermissionDao;
import com.zjw.graduation.repository.adm.AdmRolePermissionRelationDao;
import com.zjw.graduation.service.adm.AdmAdminService;
import com.zjw.graduation.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service("admAdminService")
public class AdmAdminServiceImpl implements AdmAdminService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdmAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

//    @Value("${spring.redis.expiration}")
//    private Long redisExpiration;

    @Autowired
    private AdmAdminDao admAdminDao;

    @Autowired
    private AdmAdminRoleRelationDao admAdminRoleRelationDao;

    @Autowired
    private AdmRolePermissionRelationDao admRolePermissionRelationDao;

    @Autowired
    private AdmPermissionDao admPermissionDao;

//    @Autowired
//    private RedisTemplate redisTemplate;

    public PagingResult<AdmAdmin> page(int pageIndex, int pageSize){
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        Page<AdmAdmin> page = admAdminDao.findAll(pageable);

        PagingResult<AdmAdmin> pagingResult = new PagingResult<>();
        pagingResult.setPageIndex(pageIndex);
        pagingResult.setPageSize(pageSize);
        pagingResult.setEntities(page.getContent());
        pagingResult.setTotalRecords(page.getTotalElements());

        return pagingResult;
    }

    @Override
    public AdmAdmin get(Long id) {
        return admAdminDao.findById(id).orElse(new AdmAdmin());
    }

    @Override
    public AdmAdmin save(AdmAdmin admAdmin) {
        return admAdminDao.save(admAdmin);
    }

    @Override
    public void delete(Long id) {
        AdmAdmin admAdmin = admAdminDao.findById(id).orElse(null);
        if (admAdmin != null){
            admAdmin.setLogicFlag(EnumLogicType.DELETE.getValue());
            admAdminDao.save(admAdmin);
        }
    }

    @Override
    public AdmAdmin getAdminByUsername(String username) {
        return admAdminDao.findByUsername(username);
    }

    @Override
    public List<AdmPermission> getPermissionList(Long id) {
        List<AdmAdminRoleRelation> roleRelations =  admAdminRoleRelationDao.findAllByAdminIdAndLogicFlagIs(id,EnumLogicType.NORMAL.getValue());
        List<Long> roleList = roleRelations.stream().map(AdmAdminRoleRelation::getRoleId).collect(Collectors.toList());
        List<AdmRolePermissionRelation> rolePermissionRelations =
                admRolePermissionRelationDao.findAllByRoleIdInAndLogicFlagIs(roleList, EnumLogicType.NORMAL.getValue());
        List<Long> permissionList = rolePermissionRelations.stream().map(AdmRolePermissionRelation::getPermissionId).collect(Collectors.toList());
        return admPermissionDao.findAllByIdInAndLogicFlagIs(permissionList,EnumLogicType.NORMAL.getValue());
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            AdmAdmin entity = admAdminDao.findByUsername(username);
            entity.setLoginTime(LocalDateTime.now());
            admAdminDao.save(entity);
            token = jwtTokenUtil.generateToken(userDetails);
//            redisTemplate.opsForValue().set(username, token, redisExpiration, TimeUnit.SECONDS);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public AdmAdmin adminAdd(AdmAdminCreateModel model) {
        AdmAdmin admAdmin = new AdmAdmin();
        BeanUtils.copyProperties(model, admAdmin);
        admAdmin.setPassword(passwordEncoder.encode(admAdmin.getPassword()));
        return admAdminDao.save(admAdmin);
    }

}
