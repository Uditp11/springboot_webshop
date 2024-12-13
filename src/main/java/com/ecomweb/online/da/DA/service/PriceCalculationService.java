package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service class for handling price calculations.
 * Provides utility methods such as rounding prices to two decimal places.
 *
 * This service ensures consistent rounding behavior across the application,
 * reducing code duplication and centralizing pricing logic.
 */
@Service
public class PriceCalculationService {

    /**
     * Rounds a price to two decimal places using HALF_UP rounding mode.
     *
     * @param price The original price as BigDecimal.
     * @return The rounded price as BigDecimal.
     */
    public BigDecimal roundPrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }
}
