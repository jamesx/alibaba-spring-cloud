package com.august.oauth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParseJwtTest {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdXRob3JpdGllcyI6Ii91c2VyL3VzZXJpbmZvIn0.cm1WJfCUin2TqRpwQe3R5tINaREN1hPjpQrFncoL0KHx3GjyCz6WwLPbLdIkB_ntGpVv40Jl8PpkNccNMJu_Mo6ZzKmF6xzreqEFKYIX_0PAvUqsse3RCUNa4T6pYX94R4rtGWtKHYBLS1O_6Y7eQkt2ZA9j4HPU1eVn44DShB4M7tJqqccFerd50--rAm_KBwFfYPPv0WqNiDopdg6mzGNIE5hihfaHuuqKQ2ijlLCygULiAsoY134CJ76GuVFATpJs3D_Lq8Ed52CGnL5hoe01PdKfj49jdlo4tmHbrlUUIENl5Mdl66K9PTifgjCbydBmXjGH0s3NS0X6ZF0bGg";       //公钥
        String publickey="-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxGRVLfRTz4MfS0dS3DJheK8Xd5vnLKuFuRsNxvVo0+dfho763WtR/saan60Dt9TYYYN7yS6PoMq3XmRA95LWDmkZooXvTHDDBeYW+4M6zefqya7WKPT6AjLXGZHsxYVB6gp4tSI0d/NJJzXYzaicj4XcwZXkDv/sE2Mp+wtPHjFpGnkecCHRzUzXO/R1adNZ/Cc6SQKW4fmAWV+SwArVuEC0QCWBRZIDSfcrFmS9xtEEXglHPZ99HT7Lmy9F61bAKpyWWhUKjpDPpmZUEuTNa+wG8xAMuVYO8jYyzjeE3J8dobPLAj/vHq8y/lLou1mXy9JNOZEU80xGY7Z2acZQtwIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容 载荷
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
