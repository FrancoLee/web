package com.lx.animation.util;

import org.springframework.stereotype.Component;

/**
 * Created by root on 17-12-11.
 */
@Component
public class HashPassUtil {
     char[] arr={'0', '3', 'F', 'G', 'H','o', 'p', 'q', 'O', '4', '5', '6', '7', '8', '9','$','%','*',
            '1', '2', 'c','^','&',  'g', 'h', '(','!','@','#', 'I', 'J', 'K', 'W', 'M', 'N','i', 'j', 'k', 'l', 'm', 'n',  'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'd', 'e', 'f', 'A', 'B',  'a', 'b','C', 'D', 'E', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'Y', 'Z',')','L','X','.'};
     public String gethash(String hash) {
        int length = hash.length();
        StringBuffer hashcode = new StringBuffer(length);
        int num = 1;
        for (int i = 0; i < length; i++) {
            num *= hash.charAt(i) * (i + 1);
            hashcode.append(arr[(num & 2147483647) % 73]);
        }
        return hashcode.toString();
    }
}
