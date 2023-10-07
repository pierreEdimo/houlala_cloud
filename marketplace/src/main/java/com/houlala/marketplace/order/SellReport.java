package com.houlala.marketplace.order;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellReport {
    private String productSku;
    private int total;
}
