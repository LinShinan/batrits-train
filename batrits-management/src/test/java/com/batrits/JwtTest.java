package com.batrits;

import com.batrits.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @BeforeAll
    public static void setup() {
        JwtUtils.setSecretKey("test-secret-key-for-unit-tests");
    }

    @Test
    public void testGenerateJwt() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "10");
        map.put("username", "testuser");
        String jwt = JwtUtils.generateToken(map);
        System.out.println(jwt);
    }

    @Test
    public void testParseJwt() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "12");
        map.put("username", "testuser");
        String token = JwtUtils.generateToken(map);
        Claims body = JwtUtils.parseToken(token);
        System.out.println(body);
    }
}