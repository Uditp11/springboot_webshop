package com.ecomweb.online.da.DA.service;

import com.ecomweb.online.da.DA.model.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Service class for handling price calculations.
 * Provides utility methods such as rounding prices to two decimal places,
 * converting currencies, and applying percentage-based vouchers.
 */
@Service
public class PriceCalculationService {

    private final Currency defaultCurrency;

    // Inject the discount percentage from application.properties, defaulting to 10 if not provided
    private final BigDecimal voucherPercentage;

    /**
     * Constructor-based injection of the default currency and voucher percentage.
     */
    public PriceCalculationService(
            @Value("${app.currency.default}") String defaultCurrencyStr,
            @Value("${app.discount.percentage:10}") BigDecimal voucherPercentage) {
        // Convert the string (e.g., "EURO") to an enum constant
        this.defaultCurrency = Currency.valueOf(defaultCurrencyStr.toUpperCase());
        this.voucherPercentage = voucherPercentage;
    }

    /**
     * Expose the default currency, so other services can use it.
     */
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

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

    /**
     * Converts an amount from one currency to another using a hardcoded conversion rate.
     * Rounds the result to two decimal places using HALF_UP rounding mode.
     *
     * @param amount       The amount to convert.
     * @param fromCurrency The currency of the amount.
     * @param toCurrency   The target currency.
     * @return The converted amount, rounded to two decimals.
     */
    public BigDecimal convertCurrency(BigDecimal amount, Currency fromCurrency, Currency toCurrency) {
        if (fromCurrency == toCurrency) {
            return roundPrice(amount);
        }
        BigDecimal conversionRate;
        if (fromCurrency == Currency.EURO && toCurrency == Currency.DOLLAR) {
            conversionRate = BigDecimal.valueOf(1.10);
        } else if (fromCurrency == Currency.DOLLAR && toCurrency == Currency.EURO) {
            conversionRate = BigDecimal.valueOf(0.90);
        } else {
            conversionRate = BigDecimal.ONE;
        }
        BigDecimal convertedAmount = amount.multiply(conversionRate);
        return roundPrice(convertedAmount);
    }

    /**
     * Applies a percentage voucher discount to the given price.
     * Rounds the final price to two decimal places using HALF_UP rounding mode.
     *
     * @param price             The original price as BigDecimal.
     * @param percentageVoucher The discount percentage to apply (e.g., 10 for 10%).
     * @return The discounted price, rounded to two decimals.
     */
    public BigDecimal applyPercentageVoucher(BigDecimal price, BigDecimal percentageVoucher) {
        if (price == null) {
            throw new IllegalArgumentException("Price cannot be null");
        }
        if (percentageVoucher == null) {
            throw new IllegalArgumentException("Percentage voucher cannot be null");
        }
        BigDecimal discount = price.multiply(percentageVoucher)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal discountedPrice = price.subtract(discount);
        return roundPrice(discountedPrice);
    }

    /**
     * Overload for convenience if you want to apply the configurable voucher percentage.
     *
     * @param price The original price as BigDecimal.
     * @return The discounted price, using the configured voucher percentage.
     */
    public BigDecimal applyPercentageVoucher(BigDecimal price) {
        return applyPercentageVoucher(price, voucherPercentage);
    }

    public BigDecimal getVoucherPercentage() {
        return voucherPercentage;
    }

}
