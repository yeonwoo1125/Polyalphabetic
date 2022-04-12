package com.school.protect.polyalphabetic.domain;

import com.school.protect.polyalphabetic.dto.response.CipherFindByKeyDto;
import org.springframework.stereotype.Repository;

@Repository
public interface CipherCustomRepository {
    CipherFindByKeyDto findByKey(String key);
}
