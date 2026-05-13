package com.akhilesh.project.HiddenUkWeb.strategy;

import com.akhilesh.project.HiddenUkWeb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
