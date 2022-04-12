package com.school.protect.polyalphabetic.domain;

import com.school.protect.polyalphabetic.dto.response.CipherFindByKeyDto;

public class CipherCustomRepoImpl implements CipherCustomRepository{
    @Override
    public CipherFindByKeyDto findByKey(String key) {
        //키 값으로 찾기 구현
        return null;
    }
}
