package sda.academy.travelagency.Service.service;

import sda.academy.travelagency.Entity.Order;


import java.util.List;

public interface OrderService {

    Order buyTour(Integer tourId, int numAdults, int numChildren, String customerName);



    List<Order> getOrderByCustomer(String name);

    List<Order> getOrderByTour(Integer tourId);

}
