package me.nic.shiro.controller;

import com.shu.emergency.api.common.constants.HttpStatusConstants;
import com.shu.emergency.api.common.dto.AuthUser;
import com.shu.emergency.api.common.dto.Result;
import com.shu.emergency.api.modules.sys.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author nicahead@gmail.com
 * @date 2020/2/25 10:51
 */
@Api(tags = "认证授权接口")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @ApiOperation("用户登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "用户名"),
                        @ApiImplicitParam(name = "password", value = "密码")})
    @PostMapping(value = "login")
    public Result login(@RequestBody Map<String,String> loginForm){
        String username = loginForm.get("username");
        String password = loginForm.get("password");
        try {
            //1.构造登录令牌 UsernamePasswordToken
            //加密密码
            password = new Md5Hash(password,username,3).toString();  //1.密码，盐，加密次数
            UsernamePasswordToken upToken = new UsernamePasswordToken(username,password);
            //2.获取subject
            Subject subject = SecurityUtils.getSubject();
            //3.调用login方法，进入realm完成认证
            subject.login(upToken);
            //4.获取sessionId
            String sessionId = (String)subject.getSession().getId();
            // 登录被禁用
            PrincipalCollection principals = subject.getPrincipals();
            AuthUser authUser = (AuthUser)principals.getPrimaryPrincipal();
            if(!authUser.getLoginFlag()){
                subject.logout();
                return new Result(HttpStatusConstants.USERlOCKED);
            }
            //5.构造返回结果
            return new Result(HttpStatusConstants.OK,sessionId);
        }catch (Exception e) {
            return new Result(HttpStatusConstants.NAMEORPWDERROR);
        }
    }

    /**
     * 用户登录成功之后，获取用户信息
     *      1.获取用户id
     *      2.根据用户id查询用户
     *      3.构建返回值对象
     *      4.响应
     */
    @ApiOperation("获取用户信息")
    @ApiImplicitParams({})
    @GetMapping(value = "info")
    public Result profile(HttpServletRequest request) throws Exception {
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        //2.获取安全数据
        AuthUser result = (AuthUser)principals.getPrimaryPrincipal();
        return new Result(HttpStatusConstants.OK.getCode(),HttpStatusConstants.OK.getMsg(),result);
    }

    @ApiOperation("用户登出")
    @ApiImplicitParams({})
    @GetMapping(value = "logout")
    public Result logout(HttpServletRequest request) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return new Result(HttpStatusConstants.OK.getCode(),HttpStatusConstants.OK.getMsg(),null);
    }

    @ApiOperation("认证失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "类型")})
    @GetMapping(value = "error")
    public Result error(@RequestParam("code") String code){
        // 未登录
        if ("1".equals(code)) {
            return new Result(HttpStatusConstants.UNLOGINED.getCode(),HttpStatusConstants.UNLOGINED.getMsg(),null);
        }
        // 未授权
        return new Result(HttpStatusConstants.UNAUTHORIZED.getCode(),HttpStatusConstants.UNAUTHORIZED.getMsg(),null);
    }
}
