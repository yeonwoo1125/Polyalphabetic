package com.school.protect.polyalphabetic.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CipherFindByKeyDto {
    private String encryption;
    private String cryptogram;

}
