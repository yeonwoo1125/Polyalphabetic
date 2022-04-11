package com.school.protect.polyalphabetic.controller;

import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.service.CipherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CipherRestController {
    private CipherService cipherService;

    @PostMapping
    public Long saveCipher(@RequestBody CipherRequestDto dto){
        return cipherService.save(dto);
    }
}
