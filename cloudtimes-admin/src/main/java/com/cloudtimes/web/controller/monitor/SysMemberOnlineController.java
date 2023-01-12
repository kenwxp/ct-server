package com.cloudtimes.web.controller.monitor;

import com.cloudtimes.common.annotation.Log;
import com.cloudtimes.common.constant.CacheConstants;
import com.cloudtimes.common.core.controller.BaseController;
import com.cloudtimes.common.core.domain.AjaxResult;
import com.cloudtimes.common.core.domain.model.AppLoginUser;
import com.cloudtimes.common.core.domain.model.LoginUser;
import com.cloudtimes.common.core.page.TableDataInfo;
import com.cloudtimes.common.core.redis.RedisCache;
import com.cloudtimes.common.enums.BusinessType;
import com.cloudtimes.common.utils.StringUtils;
import com.cloudtimes.system.domain.SysMemberOnline;
import com.cloudtimes.system.domain.SysUserOnline;
import com.cloudtimes.system.service.ISysMemberOnlineService;
import com.cloudtimes.system.service.ISysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 在线会员监控
 * 
 * @author tank
 */
@RestController
@RequestMapping("/monitor/memberonline")
public class SysMemberOnlineController extends BaseController
{
    @Autowired
    private ISysMemberOnlineService memberOnlineService;

    @Autowired
    private RedisCache redisCache;

    @PreAuthorize("@ss.hasPermi('monitor:member:online:list')")
    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName)
    {
        Collection<String> keys = redisCache.keys(CacheConstants.APP_LOGIN_TOKEN_KEY + "*");
        List<SysMemberOnline> userOnlineList = new ArrayList<SysMemberOnline>();
        for (String key : keys)
        {
            AppLoginUser user = redisCache.getCacheObject(key);
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(memberOnlineService.selectOnlineByInfo(ipaddr, userName, user));
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    userOnlineList.add(memberOnlineService.selectOnlineByIpaddr(ipaddr, user));
                }
            }
            else if (StringUtils.isNotEmpty(userName) && StringUtils.isNotNull(user.getUser()))
            {
                if (StringUtils.equals(userName, user.getUsername()))
                {
                    userOnlineList.add(memberOnlineService.selectOnlineByUserName(userName, user));
                }
            }
            else
            {
                userOnlineList.add(memberOnlineService.loginUserToUserOnline(user));
            }
        }
        Collections.reverse(userOnlineList);
        userOnlineList.removeAll(Collections.singleton(null));
        return getDataTable(userOnlineList);
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@ss.hasPermi('monitor:member:online:forceLogout')")
    @Log(title = "在线会员", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId)
    {
        redisCache.deleteObject(CacheConstants.APP_LOGIN_TOKEN_KEY + tokenId);
        return AjaxResult.success();
    }
}
