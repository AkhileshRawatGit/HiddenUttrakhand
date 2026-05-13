package com.akhilesh.project.HiddenUkWeb.strategy;

import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class BasePricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getPricePerNight();
    }
}
