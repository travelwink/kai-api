package com.travelwink.kai;

import com.travelwink.kai.framework.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KaiApplicationTests {

    @Test
    void contextLoads() {
        String jwtToken = JwtUtil.createToken("admin", 60 * 60);
        System.out.println(jwtToken);
    }

}
