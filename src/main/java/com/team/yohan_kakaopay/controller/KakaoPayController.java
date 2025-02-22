package com.team.yohan_kakaopay.controller;

import com.team.yohan_kakaopay.domain.KakaoPay;
import com.team.yohan_kakaopay.domain.KakaoResponse;
import com.team.yohan_kakaopay.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class KakaoPayController {
    private final KakaoPay kakaoPay;
    private final KakaoPayService kakaoPayService;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @PostMapping("/kakao/pay/item")
    public String kakaoPayRequest() {
        return "redirect:"+kakaoPayService.kakaoRequest().getNext_redirect_pc_url();
    }
    @GetMapping("/success")
    public String kakaoPaySuccess(@RequestParam String pg_token) {
        System.out.println(pg_token);
        kakaoPayService.kakaoApprove(pg_token);
        return "redirect:/";
    }

}
