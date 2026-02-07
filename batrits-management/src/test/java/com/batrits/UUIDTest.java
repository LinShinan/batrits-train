package com.batrits;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UUIDTest {

    @Test
    public void testUUID(){
        for(int i=0;i<10000;i++){
            UUID uuid = UUID.randomUUID();
            System.out.println(uuid);
        }

    }
}
