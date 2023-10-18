package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private OrderDao orderDao = new OrderDao();

    OrderValidator orderValidator = new OrderValidator();

    public List<Order> getAllOrders() throws FailedToGetOrdersException {

        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }

        //Update your `OrderService` and `Order` classes to print out the `OrderID`, `CustomerID` and `OrderDate` of all orders
        //orderList.stream().forEach(System.out::println);

        //Update your `OrderService` and `Order` classes to print out order the list by order date descending
        //Collections.sort(orderList, Collections.reverseOrder);

        //orderList.stream().forEach(System.out::println);

        //Update your `OrderService` to only show orders from the last week
        /* My solution
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate sevenDays = LocalDate.now().minusDays(7);
        Date sevenDaysDate = Date.from(sevenDays.atStartOfDay(defaultZoneId).toInstant());
        List<Order> ordersLastWeek = new ArrayList<>();

        for (Order order : orderList){
            if (order.getOrderDate().after(sevenDaysDate)){
                ordersLastWeek.add(order);
            }
        }
        */
        //ordersLastWeek.stream().forEach(System.out::println);
        //Glenn solution
//        orderList.stream().filter(order -> order.getOrderDate()
//                        .after(Date.from(Instant.now().minus(Duration.ofDays(7)))))
//                        .forEach(System.out::println);


        //Update your `OrderService` to only show orders from customer with `CustomerID` 1 My solution
//        List<Order> specificCustomerOrder = new ArrayList<>();
//
//        for (Order order : orderList){
//            if (order.getCustomerID() == 1){
//                specificCustomerOrder.add(order);
//            }
//        }

        //Glenn solution
        orderList.stream().filter(order -> order.getCustomerID() == 1).forEach(System.out::println);

        //specificCustomerOrder.stream().forEach(System.out::println);

        //Update your `OrderService` to only show the most recent order
        System.out.print(Collections.max(orderList));

        //Update your `OrderService` to only show the oldest order
        System.out.print(Collections.min(orderList));

        //Update your `OrderService` to show the total count of all orders
        System.out.println("Total orders: " + orderList.size());

        //Update your `OrderService` to show the customer ID with the most orders
        Map<Integer, Long> orderMap = orderList
                .stream()
                .collect(Collectors.groupingBy(Order::getCustomerID, Collectors.counting()));
        System.out.println(Collections.max(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
        //Update your `OrderService` to show the customer ID with the least orders
        System.out.println(Collections.min(orderMap.entrySet(), Map.Entry.comparingByValue()).getKey());


        return orderList;

    }

    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderById(id);

            if (order == null) {
                throw new OrderDoesNotExistException();
            }

            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            int id = orderDao.createOrder(order);
            if (orderValidator.isValidOrder(order) == null) {
                return id;
            }
            if (id == -1) {
                throw new FailedToCreateOrderException();
            }
            return -1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateOrderException();
        }

    }

    public void updateOrder(int id, OrderUpdateRequest order) throws InvalidOrderException,
            OrderDoesNotExistException, FailedToUpdateOrderException {

        try {

            if (orderValidator.isValidOrderUpdate(order)){
                throw new InvalidOrderException();
            }

            Order orderToUpdate = orderDao.getOrderById(id);

            if (orderToUpdate == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);

        } catch (SQLException e){

            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();

        }

    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {

        try{
            Order orderToDelete = orderDao.getOrderById(id);

            if(orderToDelete == null){

                throw new OrderDoesNotExistException();

            }

            orderDao.deleteOrder(id);

        }catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToDeleteOrderException();
        }
    }
}
