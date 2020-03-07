package com.august.oauth.config;
import com.august.core.bean.Resp;
import com.august.oauth.entity.UserJwt;
import com.august.oauth.feign.UserFeign;
import com.august.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/*****
 * 自定义授权认证类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /****
     * 自定义授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new UsernameNotFoundException("用户名或密码错误!");
        }

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名或密码错误!");
        }

        //根据用户名查询用户信息
        Resp<User> resp = userFeign.findByUserName(username);
        //创建User对象
        String permissions = "goods_list,seckill_list";
        UserJwt userDetails = new UserJwt(username,resp.getData().getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        userDetails.setComny("阿里巴巴");
        return userDetails;
//        return User.withUsername(username)
//                .password(passwordEncoder.encode("123456"))
//                .authorities("ROLE_ADMIN")
//                .build();
    }
}
