package com.akhilesh.project.HiddenUkWeb.strategy;

import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
    public BigDecimal calculateDyanmicPricing(Inventory inventory){
        PricingStrategy pricingStrategy=new BasePricingStrategy();

        //apply the adition strategies
        pricingStrategy=new SurgePricingStrategy(pricingStrategy);
        pricingStrategy=new OccupancyPricingStrategy(pricingStrategy);
        pricingStrategy=new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy=new HolidayPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);

    }
}
