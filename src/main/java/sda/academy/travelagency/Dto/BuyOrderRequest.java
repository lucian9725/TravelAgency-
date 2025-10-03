package sda.academy.travelagency.Dto;

import lombok.Data;

@Data
public class BuyOrderRequest {
    private Integer tourId;
    private int numAdults;
    private int numChildren;
    private String customerName;
}
