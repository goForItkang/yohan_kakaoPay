package com.team.yohan_kakaopay.domain;

import lombok.Data;

@Data
public class Amount {
    private String total;
    private String tax_free;
    private String vat;
    private String point;
    private String discount;
    private String green_deposit;

}
