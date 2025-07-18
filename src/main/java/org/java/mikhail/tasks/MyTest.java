package org.java.mikhail.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyTest {

    public static void main(String[] args) {
        double[] array = {2,1,4,-2,3};
        double[] result = MyTest.task(array);


        System.out.println(Arrays.toString(result));
    }

    // Последний раз
    public static double[] task(double[] a) {
        if (a.length == 0) return new double[0];

        for(double val : a) {
            if (val < 0)
                throw new IllegalArgumentException("Массив содержит отрицательное значение: " + val);
        }

        // put() перезапишет предыдущее, сохранится индекс посл.вх.
        // 2. Запомним последнее вхождение каждого элемента
        Map<Double, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            lastIndexMap.put(a[i], i);
        }

        // O(n)
        // 3. Добавим в результат только те элементы, чей индекс совпадает с последним вхождением
        List<Double> resultList = new ArrayList<>();
        Set<Double> added = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            double val = a[i];
            if (lastIndexMap.get(val) == i && !added.contains(val)) {
                resultList.add(val);
                added.add(val);
            }
        }

        // 4. Преобразуем в примитивный массив
        double[] result = new double[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}
