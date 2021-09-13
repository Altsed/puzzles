package com.altsed.stream;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Main {

    static int[] data = {-1, -1, 2, 2, 0, 11, 54, 34, 0, 22};

    public static void main(String[] args) {
        // write your code here

        System.out.println(getSum(data));
        System.out.println(Arrays.toString(getMaxValueAndIndex(data)));
        System.out.println(Arrays.toString(getMinValueAndIndex(data)));
        System.out.println(getAverageValue(data));
        System.out.println(getQuantityOfEqualElements(33, data));
        System.out.println(getCountForNull(data));
        System.out.println(Arrays.toString(multiplyData(1, data)));
        System.out.println(Arrays.toString(resetOddData(data)));
        System.out.println(Arrays.toString(resetDataWithEvenIndex(data)));
        System.out.println(getFirstPositiveNumber(data));
        System.out.println(getLastNegativeNumber(data));
        System.out.println(countEqualData(0, data));
//        System.out.println(Arrays.toString(shiftRight(data, 2)));
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.println(envName + " ===== " + env.get(envName));
        }


    }


    //Найти сумму элементов массива
    public static int getSum(int[] data) {

//        return Arrays.stream(data).sum();
//        return Arrays.stream(data).reduce(0, Integer::sum);
        return Arrays.stream(data).reduce((a, b) -> a + b).orElse(0);


    }

    //    Найти максимальный элемент, значение и индекс
    public static int[] getMaxValueAndIndex(int[] data) {
        int[] result = new int[2];
        IntStream.range(0, data.length)
                .reduce((a, b) -> data[a] > data[b] ? a : b)
                .ifPresent(x -> {
                    result[0] = data[x];
                    result[1] = x;
                });

        return result;
    }
//    Найти минимальный элемент, значение и индекс

    public static int[] getMinValueAndIndex(int[] data) {

        int[] result = new int[2];
        IntStream.range(0, data.length)
                .reduce((a, b) -> data[a] < data[b] ? a : b)
                .ifPresent(x -> {
                    result[0] = data[x];
                    result[1] = x;
                });
        return result;
    }

    //    Найти среднее значение элементов массива
    public static double getAverageValue(int[] data) {
        return Arrays.stream(data).average().orElseThrow();
    }

    //    Посчитать количество элементов равных заданному
    public static int getQuantityOfEqualElements(int value, int[] data) {

//        return Collections.frequency(Arrays.asList(data), value);
        return Arrays.stream(data).boxed().filter(f -> f == value).collect(toList()).size();

    }

    //    Посчитать количество элементов равных нулю
    public static int getCountForNull(int[] data) {
        return Arrays.stream(data)
                .boxed()
                .filter(f -> f == 0)
                .collect(toList())
                .size();
    }

    //    Помножить элементы массива на число
    public static int[] multiplyData(int number, int[] data) {
        IntStream.range(0, data.length).forEach(x -> data[x] = data[x] * number);
        return data;
    }

    //    Прибавить к элементам массива их индекс
    public static int[] addData(int number, int[] data) {
        IntStream.range(0, data.length).forEach(x -> data[x] = data[x] + number);
        return data;
    }

    //    Обнулить четные по значению элементы массива
    public static int[] resetOddData(int[] data) {

        IntStream.range(0, data.length).filter(f -> data[f] % 2 == 0).forEach(x -> data[x] = 0);

        return data;
    }

    //    Обнулить элементы  с нечетным индексом.
    public static int[] resetDataWithEvenIndex(int[] data) {

        IntStream.range(0, data.length).filter(f -> f % 2 != 0).forEach(x -> data[x] = 0);
        return data;

    }

    //    Найти первый положительный элемент
    public static int getFirstPositiveNumber(int[] data) {
        return Arrays.stream(data).filter(x -> x > 0).findFirst().orElse(0);
    }

    //    Найти последний отрицательный элемент
    public static int getLastNegativeNumber(int[] data) {
        return Arrays.stream(data).boxed().filter(x -> x < 0).reduce((a, b) -> b).orElseThrow();
    }

    public static long countEqualData(int number, int[] data) {
        return Arrays.stream(data).filter(x -> x == number).count();

    }

    //    Найти индексы вхождения элемента в массив
    public static int[] getIndexesForElements(int number, int[] data) {

        return IntStream.range(0, data.length)
                .boxed().filter(f -> data[f] == number).mapToInt(Integer::intValue).toArray();
    }

//    Проверить массив на упорядоченность элементов по возрастанию

    public static boolean checkOrderAsc(int[] data) {
        return IntStream.range(0, data.length - 1).noneMatch(i -> data[i] > data[i + 1]);

    }

    //    Проверить массив на упорядоченность элементов по спаданию

    public static boolean checkOrderDesc(int[] data) {
        return IntStream.range(0, data.length - 1).noneMatch(i -> data[(data.length - 1) - i] > data[(data.length - 1) - i - 1]);

    }

    //удалить дубликаты из листа
    public static List<Integer> removeDuplicates (ArrayList<Integer> list) {
        return list.stream().distinct().collect(toList());
    }

    //Циклический сдвиг элементов массива на k- позиций вправо
    public static int[] shiftRight (int[] data, int positionsToShift) {
        return IntStream.range(0, data.length + positionsToShift).boxed().mapToInt(x -> x < positionsToShift ? 0 : data[x - positionsToShift]).toArray();
    }


}
