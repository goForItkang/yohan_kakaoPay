package com.team.yohan_kakaopay.domain;

import lombok.Data;

@Data
public class Item {
    private String id; // 식별 번호
    private String name; // 이름
    private int price;// 가격
    private int stock;// 수령
}
