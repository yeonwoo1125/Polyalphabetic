package com.school.protect.polyalphabetic.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CipherRepositoryTest {
    @Autowired
    CipherRepository cipherRepository;

    @AfterEach
    public void clean(){
        cipherRepository.deleteAll();
    }

    @Test
    void 암호문불러오기(){
        //given
        cipherRepository.save(Cipher.builder()
                        .key("테스트 키")
                        .value("테스트 평문")
                .build());

        //when
        List<Cipher> cipherList = cipherRepository.findAll();

        //then
        Cipher cipher = cipherList.get(0);
        assertThat(cipher.getKey()).isEqualTo("테스트 키");
        assertThat(cipher.getValue()).isEqualTo("테스트 평문");
    }
}
