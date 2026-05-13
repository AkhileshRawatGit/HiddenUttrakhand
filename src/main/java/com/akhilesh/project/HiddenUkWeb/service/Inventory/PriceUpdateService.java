package com.akhilesh.project.HiddenUkWeb.service.Inventory;

import com.akhilesh.project.HiddenUkWeb.entity.Hotel;
import com.akhilesh.project.HiddenUkWeb.entity.HotelMinPrice;
import com.akhilesh.project.HiddenUkWeb.entity.Inventory;
import com.akhilesh.project.HiddenUkWeb.repository.HotelMinPriceRepo;
import com.akhilesh.project.HiddenUkWeb.repository.HotelRepo;
import com.akhilesh.project.HiddenUkWeb.repository.InventoryRepo;
import com.akhilesh.project.HiddenUkWeb.strategy.PricingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PriceUpdateService {

    private final HotelRepo hotelRepo;
    private final InventoryRepo inventoryRepo;
    private final HotelMinPriceRepo hotelMinPriceRepo;
    private final PricingService pricingService;

    @Scheduled(cron = "*/5 * * * * *")
    public void updatePrices(){
        int page=0;
        int batchSize=100;
        while(true){
            Page<Hotel> hotelPage=hotelRepo.findAll(PageRequest.of(page,batchSize));
            if(hotelPage.isEmpty()){
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPrice);
            page++;
        }
    }

    private void updateHotelPrice(Hotel hotel){
        LocalDate startDate=LocalDate.now();
        LocalDate endDate=LocalDate.now().plusYears(1);

        List<Inventory> inventoryList=inventoryRepo.findByHotelAndDateBetween(hotel,startDate,endDate);
        updateInventoryPrice(inventoryList);

        updateHotelMinPrice(hotel,inventoryList,startDate,endDate);
    }

    private void updateHotelMinPrice(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {

        Map<LocalDate ,BigDecimal>dailyMinPrice=inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice,Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,e->e.getValue().orElse(BigDecimal.ZERO)));
        List<HotelMinPrice>hotelMinPrices=new ArrayList<>();
        dailyMinPrice.forEach((date,price)->{
            HotelMinPrice hotelMinPrice=hotelMinPriceRepo.findByHotelAndDate(hotel,date).orElse(new HotelMinPrice(hotel,date));
            hotelMinPrice.setPrice(price);
            hotelMinPrices.add(hotelMinPrice);
        });
        hotelMinPriceRepo.saveAll(hotelMinPrices);
    }

    private void updateInventoryPrice(List<Inventory> inventoryList){

        inventoryList.forEach(inventory -> {
            BigDecimal dynamicPrice=pricingService.calculateDyanmicPricing(inventory);
            inventory.setPrice(dynamicPrice);
        });
        inventoryRepo.saveAll(inventoryList);
    }

}
