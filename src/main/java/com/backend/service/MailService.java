package com.backend.service;

import net.sf.json.JSONObject;

public interface MailService {
    JSONObject sendMimeMail(String email);
    String randomCode();
    boolean checkCode(JSONObject checkJson, JSONObject jsonObject);
}
