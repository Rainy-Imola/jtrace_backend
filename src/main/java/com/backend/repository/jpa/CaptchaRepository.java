package com.backend.repository.jpa;

import com.backend.entity.Captcha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaptchaRepository extends JpaRepository<Captcha, Integer> {
    Captcha findByEmail(String email);
}
