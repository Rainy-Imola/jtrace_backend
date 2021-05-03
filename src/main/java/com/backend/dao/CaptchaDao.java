package com.backend.dao;

import com.backend.entity.Captcha;

public interface CaptchaDao {
    Captcha findByEmail(String email);
    Captcha addCaptcha(Captcha captcha);
}
