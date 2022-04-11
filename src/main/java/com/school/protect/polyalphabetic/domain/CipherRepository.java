package com.school.protect.polyalphabetic.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CipherRepository extends JpaRepository<Cipher,Long> {
}
