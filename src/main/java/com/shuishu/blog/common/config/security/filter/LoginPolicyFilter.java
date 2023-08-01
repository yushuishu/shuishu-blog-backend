package com.shuishu.blog.common.config.security.filter;


import com.alibaba.fastjson2.JSONObject;
import com.shuishu.blog.business.user.service.UserService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.security.LoginPolicyConfig;
import com.shuishu.blog.common.config.security.SpringSecurityUtils;
import com.shuishu.blog.common.config.security.service.SecurityUserService;
import com.shuishu.blog.common.domain.user.entity.dto.AuthTokenCacheDto;
import com.shuishu.blog.common.domain.user.entity.vo.UserInfoVo;
import com.shuishu.blog.common.enums.UserEnum;
import com.shuishu.blog.common.utils.RequestWrapper;
import com.shuishu.blog.common.utils.ResponseUtils;
import com.shuishu.blog.common.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：谁书-ss
 * @Date ：2023-03-12 12:57
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：登录策略过滤器
 * <p></p>
 * {@link LoginPolicyConfig}
 * {@link UserEnum.LoginPolicy}
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginPolicyFilter extends OncePerRequestFilter {
    private final LoginPolicyConfig loginPolicyConfig;
    private final TokenUtils tokenUtils;
    private final SecurityUserService securityUserService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("【LoginPolicyFilter 过滤器】执行doFilterInternal()方法");
        RequestWrapper requestWrapper = new RequestWrapper(request);
        // 获取URI
        String requestUri = request.getRequestURI();
        if (SpringSecurityUtils.LOGIN_URL_LOCAL.contains(requestUri) ||
                SpringSecurityUtils.LOGIN_URL_EMAIL.contains(requestUri) ||
                SpringSecurityUtils.LOGIN_URL_PHONE.contains(requestUri)) {
            if (HttpMethod.POST.matches(request.getMethod())) {
                // 没有token，不是当前客户端
                if (!tokenUtils.hasAuthToken(request)) {
                    // 获取用户
                    JSONObject jsonObject = JSONObject.parseObject(requestWrapper.getBody());
                    Object usernameObj = jsonObject.get(SpringSecurityUtils.LOGIN_USERNAME_FRONT_KEY);
                    String username = usernameObj == null ? null : String.valueOf(usernameObj);
                    UserInfoVo userInfoVo = securityUserService.findByUserAuthIdentifier(username, SpringSecurityUtils.getAuthType(requestUri));
                    if (userInfoVo != null) {
                        // 当前用户最多登录的客户端人数
                        Integer userMaxLoginClientNumber = userInfoVo.getUserMaxLoginClientNumber();
                        userMaxLoginClientNumber = userMaxLoginClientNumber == null ? 1 : userMaxLoginClientNumber;
                        log.info("用户【{}】，最多登录的客户端为【{}】个", userInfoVo.getNickname(), userMaxLoginClientNumber);
                        // 已经登录的客户端人数 token
                        List<AuthTokenCacheDto> authTokenCacheDtoList = tokenUtils.getUserAllAuthTokenList(userInfoVo.getUserId());
                        // 当前用户可以登录的最多人数 <= 当前用户已经登录的人数
                        if (!ObjectUtils.isEmpty(authTokenCacheDtoList) && userMaxLoginClientNumber <= authTokenCacheDtoList.size()) {
                            log.info("用户【{}】，已经登录的客户端为【{}】个", userInfoVo.getNickname(), authTokenCacheDtoList.size());
                            // 获取 用户登录策略
                            String loginPolicy = loginPolicyConfig.getLoginPolicy();
                            if (UserEnum.LoginPolicy.ONE.name().equals(loginPolicy)) {
                                log.info("用户【{}】登录，执行策略：达到最大客户端登录人数，将最早登录的客户端给踢掉（默认登录策略配置）", userInfoVo.getNickname());
                                // 达到最大客户端登录人数，将最早登录的客户端给踢掉（默认登录策略配置）
                                // 根据时间排序token
                                authTokenCacheDtoList = authTokenCacheDtoList.stream().sorted(Comparator.comparing(AuthTokenCacheDto::getCreateTime)).collect(Collectors.toList());
                                // 最早登录的 token
                                String authToken = authTokenCacheDtoList.get(0).getAuthToken();
                                // 删除缓存 token，强制退出
                                tokenUtils.logout(authToken, null);
                                // 重新设置 当前用户登录的 所有客户端token缓存
                                authTokenCacheDtoList = authTokenCacheDtoList.stream().filter(t -> !t.getAuthToken().equals(authToken)).collect(Collectors.toList());
                                tokenUtils.setUserAuthTokenList(userInfoVo.getUserId(), authTokenCacheDtoList);
                                // TODO 发送消息通知给前端 websocket


                            } else if (UserEnum.LoginPolicy.TWO.name().equals(loginPolicy)) {
                                log.info("用户【{}】登录，执行策略：达到最大客户端登录人数，不允许登录", userInfoVo.getNickname());
                                // 达到最大客户端登录人数，不允许登录
                                ResponseUtils.responseJson(response, new ApiResponse<>(401, "登录失败，当前用户【" + userInfoVo.getNickname() + "】登录客户端的数量最多为【" + userMaxLoginClientNumber + "】个"));
                                return;
                            }
                        }
                    }
                }
            }
        }
        filterChain.doFilter(requestWrapper, response);
    }

}
