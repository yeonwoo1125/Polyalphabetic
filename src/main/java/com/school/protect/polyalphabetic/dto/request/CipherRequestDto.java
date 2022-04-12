package com.school.protect.polyalphabetic.dto.request;

import com.school.protect.polyalphabetic.domain.Cipher;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public class CipherRequestDto {
    private String key;
    private String value;

    @Builder
    public CipherRequestDto(String key, String value){
        this.key = key;
        this.value = value;
    }

    public Cipher toEntity(){
        return Cipher.builder()
                .key(key)
                .value(value)
                .build();
    }
}
