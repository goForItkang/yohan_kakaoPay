package com.team.yohan_kakaopay.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kakaopay")
@Data
public class KakaoPay {
    public String secretKey;
    public String clientID;

}
