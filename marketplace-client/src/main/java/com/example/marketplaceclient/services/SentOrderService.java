package com.example.marketplaceclient.services;

import com.example.marketplaceclient.model.SentOrder;

public interface SentOrderService {
    void getSentOrderAndUpdateQuantity(String id);
}
