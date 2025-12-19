package org.java.mikhail.tasks;

import java.util.*;

/**
 * @author Mikhail
 * <p>Решение задачи №2.</p>
 */
public class Task2 {

    public static void main(String[] args) {
        List<OrderService.OrderData> orders = List.of(
                new OrderService.OrderData(OrderService.Type.DELIVERY, "EUR", 2000L),
                new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", 15L),
                new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", 200L),
                new OrderService.OrderData(OrderService.Type.PICKUP, "RUB", 1250L),
                new OrderService.OrderData(OrderService.Type.DELIVERY, "USD", 35L),
                new OrderService.OrderData(OrderService.Type.PICKUP, "USD", 55L),
                new OrderService.OrderData(OrderService.Type.DELIVERY, "RUB", 100L)
        );

        OrderService service = new OrderService();
        Map<String, Double> result = service.getMaxMinusMinDeliveryMapByCurrency(orders);

        result.forEach((currency, diff) -> System.out.println(currency + " -> " + diff));
    }
}

/**
 * Сервис, который содержит заказы пользователя.
 */
class OrderService {

    enum Type {DELIVERY, PICKUP}

    record OrderData(Type type, String currency, Long amount) {
    }

    /**
     * Возвращает map вида [валюта (в порядке возрастания разницы) – разница между самым большим и маленьким заказом типа DELIVERY].
     */
    Map<String, Double> getMaxMinusMinDeliveryMapByCurrency(List<OrderData> orderDataList) {
        // Currency
        Map<String, long[]> currencyToMinMax = new HashMap<>();

        for (OrderData order : orderDataList) {
            if (order.type() != Type.DELIVERY) continue;

            String currency = order.currency();
            long amount = order.amount();

            currencyToMinMax.compute(currency, (k, v) -> {
                if (v == null) {
                    return new long[]{amount, amount}; // если это первый заказ {min, max}
                }
                v[0] = Math.min(v[0], amount); // min
                v[1] = Math.max(v[1], amount); // max
                return v;
            });
        }

        // Переводим в Map<String, Double> и сортируем по разнице, сохранить порядок после сортировки
        return currencyToMinMax.entrySet()
                .stream()
                .sorted(Comparator.comparingLong(e -> (e.getValue()[1] - e.getValue()[0]))) // sort by (max - min)
                .collect(LinkedHashMap::new,
                        (map, e) -> map.put(e.getKey(), (double) (e.getValue()[1] - e.getValue()[0])),
                        LinkedHashMap::putAll);
    }
}
