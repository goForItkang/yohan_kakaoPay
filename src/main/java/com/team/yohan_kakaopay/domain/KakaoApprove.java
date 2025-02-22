package com.team.yohan_kakaopay.domain;

import lombok.Data;

@Data
public class KakaoApprove {
        private String aid;
    	private String cid;
        private String tid;
        private String sid;
        private String partner_order_id;
        private String partner_user_id;
        private String payment_method_type;
        private Amount amount;
        private String item_name;
        private String item_code;
        private String quantity;
        private String created_at;
        private String approved_at;
        private String payload;
}
