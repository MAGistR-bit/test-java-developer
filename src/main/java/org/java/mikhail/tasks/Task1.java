package org.java.mikhail.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mikhail.
 * <p>Решение задачи №1.</p>
 */
public class Task1 {

    /**
     * Точка входа в программу
     *
     * @param args список аргументов командной строки.
     */
    public static void main(String[] args) {
        double[] array = {2, 1, 4, 2, 3, 18, 10, 18};
        double[] result = Task1.task(array);

        System.out.println(Arrays.toString(result));
    }

    /**
     * Удаляет дубликаты из исходного массива.
     *
     * @param a массив чисел, длина массива может быть больше 10 млн.
     * @return массив чисел, в которых отсутствуют дубликаты.
     * Порядок чисел в оригинальном массиве должен быть сохранён.
     * Из дубликатов нужно оставлять последний элемент, например, для {@code {2,1,4,2,3}}
     * <b>правильное решение - {@code {1,4,2,3}}</b>,
     * а не {@code {2,1,4,3}}
     * @throws IllegalArgumentException исключение, которое возникает, когда во входном массиве
     *                                  есть элемент меньше нуля.
     */
    public static double[] task(double[] a) {
        if (a.length == 0) return new double[0];

        for (double val : a) {
            if (val < 0) {
                throw new IllegalArgumentException("Массив содержит отрицательное значение: " + val);
            }
        }

        final List<Double> resultList = getDoubleList(a);

        double[] result = new double[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private static List<Double> getDoubleList(double[] a) {
        // Метод put() перезапишет предыдущее, сохранится индекс последнего вхождения.
        // Запоминаем последнее вхождение каждого элемента
        Map<Double, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            lastIndexMap.put(a[i], i);
        }

        // O(n)
        // Добавляем в результат только те элементы, чей индекс совпадает с последним вхождением
        List<Double> resultList = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            double val = a[i];
            if (lastIndexMap.get(val) == i && !resultList.contains(val)) {
                resultList.add(val);
            }
        }
        return resultList;
    }
}
