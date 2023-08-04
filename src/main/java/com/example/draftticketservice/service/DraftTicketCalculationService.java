package com.example.draftticketservice.service;

import com.example.draftticketservice.context.DraftTicketContext;
import com.example.draftticketservice.context.TicketCalculationData;
import com.example.draftticketservice.enums.AgeGroupEnum;
import com.example.draftticketservice.enums.DestinationEnum;
import com.example.draftticketservice.enums.TaxTypeEnum;
import com.example.draftticketservice.model.TaxRate;
import org.springframework.stereotype.Service;

@Service
public class DraftTicketCalculationService {

    private static final double LUGGAGE_RATE = 0.3;
    private static final double DECIMAL_PCT_100 = 1d;
    private static final double DECIMAL_PCT_1 = 0.01;
    private static final double DECIMAL_PCT_50 = 0.5;

    private final TaxRateService taxRateService;
    private final BasePriceService basePriceService;

    public DraftTicketCalculationService(TaxRateService taxRateService, BasePriceService basePriceService) {
        this.taxRateService = taxRateService;
        this.basePriceService = basePriceService;
    }

    /**
     * @param context - initial context before any calculations containing only the data received from request
     * @return Updated DraftTicketContext with all calculation information
     */
    public DraftTicketContext resolveDraftPrices(DraftTicketContext context) {

        context.setVat(getVatFromTaxService());
        context.setBasePrice(getBasePriceFromService(context.getDestination()));

        context.getCalculationDataList()
                .forEach(data -> calculateAndSetDraftPrices(data, context.getVat(), context.getBasePrice()));

        return context.setTotalPrice(calculateTotalPrice(context));
    }


    /***
     * @return value for VAT tax as a decimal number. Defaults to 0.21 if VAT is not available from TaxRateService
     * (assumed to be existing service)
     */
    private Double getVatFromTaxService() {
        var taxRateList = taxRateService.getTaxRates();

        return taxRateList.stream()
                .filter(it -> TaxTypeEnum.VAT.equals(it.getTaxType()))
                .findFirst()
                .map(TaxRate::getRate)
                .map(it -> it * DECIMAL_PCT_1)
                .orElse(0.21);
    }

    private Double getBasePriceFromService(DestinationEnum destination) {
        return basePriceService.getBasePriceForDestination(destination);
    }

    /***
     * Method to calculate and set into context values for draft ticket and luggage prices
     * @param data initial data containing age group and number of bags
     * @param vat tax rate received from existing tax rate service
     * @param basePrice base price for destination received from existing base price service
     */
    private void calculateAndSetDraftPrices(TicketCalculationData data, double vat, double basePrice) {
        var passengerCalculations = data.getPassengerCalculations();
        var ticketPrice = calculateTicketPriceForPassenger(basePrice, vat, passengerCalculations.getAgeGroup());
        passengerCalculations.setTicketPrice(ticketPrice);

        var luggageCalculations = data.getLuggageCalculations();
        var luggagePricePerItem = calculateLuggagePricePerItem(basePrice, vat);
        luggageCalculations.setLuggagePrice(luggagePricePerItem * luggageCalculations.getNumberOfBags());
    }

    private double calculateTicketPriceForPassenger(double basePrice, double vat, AgeGroupEnum ageGroup) {
        double passengerDiscount = AgeGroupEnum.CHILD.equals(ageGroup) ? DECIMAL_PCT_50 : DECIMAL_PCT_100;

        return basePrice * passengerDiscount * (DECIMAL_PCT_100 + vat);
    }

    private double calculateLuggagePricePerItem(double basePrice, double vat) {
        return basePrice * LUGGAGE_RATE * (DECIMAL_PCT_100 + vat);
    }

    private double calculateTotalPrice(DraftTicketContext context) {
        return context.getCalculationDataList().stream()
                .map(this::getTotalPriceForPassenger)
                .reduce(0d, Double::sum);
    }

    private double getTotalPriceForPassenger(TicketCalculationData data) {
        var luggageCalculations = data.getLuggageCalculations();
        return data.getPassengerCalculations().getTicketPrice() + luggageCalculations.getLuggagePrice();
    }
}
