package com.school.protect.polyalphabetic.service;

import com.school.protect.polyalphabetic.dto.request.CipherRequestDto;
import com.school.protect.polyalphabetic.domain.CipherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CipherService {

    private CipherRepository cipherRepository;

    @Transactional
    public Long save(CipherRequestDto dto){
        return cipherRepository.save(dto.toEntity()).getId();
    }
}
