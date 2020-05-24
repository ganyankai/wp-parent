package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwtTest {

    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("itcast")
                .parseClaimsJws("" +
                        "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_pqawiLCJpYXQiOjE1ODkxMjExMjIsImV4cCI6MTU4OTEyMTcyMiwicm9sZSI6ImFkbWluIn0.wKf1f-As8utJqgSDP4s0HBXnMW59pTSztXnEsriM-Do"
                )
                .getBody();
        System.out.println("用户ID："+claims.getId());
        System.out.println("用户名："+claims.getSubject());
        System.out.println("IssuedAt:"+new SimpleDateFormat("yyyy-MM-dd").format(claims.getIssuedAt()));
        System.out.println("过期时间:"+new SimpleDateFormat("yyyy-MM-dd").format(claims.getExpiration()));
        System.out.println("角色:"+claims.get("role"));
    }

}
