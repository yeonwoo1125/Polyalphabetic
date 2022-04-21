package com.school.protect.polyalphabetic.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CipherJPARepository extends JpaRepository<Cipher,Long> {
}
