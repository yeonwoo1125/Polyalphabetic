package com.school.protect.polyalphabetic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(length = 255)
    private String cryptogram;

    @Builder
    public Cipher(String key, String value, String cryptogram){
        this.cryptogram=cryptogram;
        this.key = key;
        this.value = value;
    }

    @Builder
    public Cipher(String key, String value){
        this.key = key;
        this.value = value;
    }
}
