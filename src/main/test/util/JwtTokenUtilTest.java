package util;

import com.auth0.jwt.algorithms.Algorithm;
import com.lx.animation.util.JwtTokenUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class JwtTokenUtilTest {
    @Test
    public void JwtTest() throws UnsupportedEncodingException {
        String token= JwtTokenUtil.getToken("admin");
        String user=JwtTokenUtil.getContext(token);
        System.out.println(Algorithm.HMAC256("salt"));
        System.out.println(token);
        System.out.println(user);
    }
}
