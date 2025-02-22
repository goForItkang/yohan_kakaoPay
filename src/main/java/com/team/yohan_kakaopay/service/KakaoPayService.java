package com.team.yohan_kakaopay.service;

import com.team.yohan_kakaopay.domain.KakaoApprove;
import com.team.yohan_kakaopay.domain.KakaoPay;
import com.team.yohan_kakaopay.domain.KakaoResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoPayService {
    private static final Logger log = LoggerFactory.getLogger(KakaoPayService.class);
    private final KakaoPay kakaoPay;
    private final RestTemplate restTemplate = new RestTemplate();
    private KakaoResponse kakaoResponse;
    private KakaoApprove kakaoApprove;
    private String tid;

    public HttpHeaders kakaoPayHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","SECRET_KEY "+kakaoPay.getSecretKey());
        return headers;
    }
    public KakaoResponse kakaoRequest() {
        HttpHeaders headers = kakaoPayHeaders();

        //파라미터 값
        Map<String, String> params = new HashMap<>();
        params.put("cid", kakaoPay.getClientID());
        params.put("partner_order_id", "1"); //가맹점 주문번호 수정
        params.put("partner_user_id", "1"); // 가맹점 user번호
        params.put("item_name","초코파이"); //아이템 이름
        params.put("quantity","1"); // 수량
        params.put("total_amount","2200");//총가격 -> 이후 수정
        params.put("vat_amount","200"); //부가세 -> 수정
        params.put("tax_free_amount","0");// 상품 비과세 금액
        params.put("approval_url","http://localhost:8080/success"); // 결재 성공 url
        params.put("fail_url","http://localhost:8080/fail"); // 결제 실패 url
        params.put("cancel_url","http://localhost:8080/cancel"); // 결재 취소 url

        HttpEntity<Map> httpEntity = new HttpEntity<>(params, headers);
        kakaoResponse = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/ready",
                httpEntity,
                KakaoResponse.class
        );
        tid = kakaoResponse.getTid();
        log.info("결과 요청{}",kakaoResponse);
        return kakaoResponse;
    }

    public void kakaoApprove(String pgToken) {
        HttpHeaders httpHeaders = kakaoPayHeaders();
       //파라미터
        Map<String, String> params = new HashMap<>();
        params.put("cid", kakaoPay.getClientID());
        params.put("tid", tid);
        params.put("partner_order_id", "1");
        params.put("partner_user_id", "1");
        params.put("pg_token",pgToken);

        HttpEntity<Map> httpEntity = new HttpEntity<>(params, httpHeaders);
        kakaoApprove = restTemplate.postForObject(
                "https://open-api.kakaopay.com/online/v1/payment/approve",
                httpEntity,
                KakaoApprove.class
        );
        log.info("승인 요청 값{}",kakaoApprove.toString());
    }
}
