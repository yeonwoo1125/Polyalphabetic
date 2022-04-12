package com.school.protect.polyalphabetic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cipher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255, nullable = false)
    private String key;

    @Column(length = 255, nullable = false)
    private String value;

    //암호키
    @Column(length = 255)
    private String cryptogram;
    
    //암호화된 문자열
    @Column
    private String encryption;


    @Builder
    public Cipher(String key, String value){
        this.key = key;
        this.value = value;
    }

    public void setCryptogram(String cryptogram, String encryption){
        this.cryptogram = cryptogram;
        this.encryption = encryption;
    }
}
