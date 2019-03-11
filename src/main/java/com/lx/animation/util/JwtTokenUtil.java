package com.lx.animation.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(JwtTokenUtil.class);
    public static String getToken(String name){
        String token=null;
        Map<String,Object> map=new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        try {
            token= JWT.create().withHeader(map).withClaim("user",name).sign(Algorithm.HMAC256("salt"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.toString());
        }
        return token;
    }
    public static String getContext(String token){
        JWT jwt=JWT.decode(token);
        Claim user=jwt.getClaim("user");
        Claim alg=jwt.getClaim("alg");
        return user.asString();
    }
}
