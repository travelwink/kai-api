package com.travelwink.kai.system.service.impl;

import com.travelwink.kai.system.service.VerifyCodeService;
import org.springframework.stereotype.Service;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public boolean send(String recipient) {
        return false;
    }
}
