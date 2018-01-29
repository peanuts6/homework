package functionjava.hw04;


import functionjava.hw01.SortUtil;
import functionjava.hw02.Result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xqy on 18/1/24.
 */
public class StreamDemo {
    public static void main(String[] args){

        //peek
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

//        //collect
//        Map<String, Map<String, List<Salary>>> s
//                = Stream.collect(Collectors.groupingBy(Salary::getName,
//                Collectors.groupingBy(Salary::getBouns)));

        //parallelStream
        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8 };
        List<Integer> listOfIntegers = new ArrayList<>(Arrays.asList(intArray));

//        System.out.println("listOfIntegers:");
//        listOfIntegers
//                .stream()
//                .forEach(e -> System.out.print(e + " "));
//        System.out.println("");
//
//        System.out.println("listOfIntegers sorted in reverse order:");
//        Comparator<Integer> normal = Integer::compare;
//        Comparator<Integer> reversed = normal.reversed();
//        Collections.sort(listOfIntegers, reversed);
//        listOfIntegers
//                .stream()
//                .forEach(e -> System.out.print(e + " "));
//        System.out.println("");
//
//        System.out.println("Parallel stream");
//        listOfIntegers
//                .parallelStream()
//                .forEach(e -> System.out.print(e + " "));
//        System.out.println("");
//
//        System.out.println("Another parallel stream:");
//        listOfIntegers
//                .parallelStream()
//                .forEach(e -> System.out.print(e + " "));
//        System.out.println("");

        System.out.println("With forEachOrdered:");
        listOfIntegers
                .parallelStream()
                .forEachOrdered(e -> System.out.print(e + " "));
        System.out.println("");


//        Stream.of("d2", "a2", "b1", "b3", "c")
//                .filter(s -> {
//                    System.out.println("filter: " + s);
//                    return true;
//                })
//                .forEach(s -> System.out.println("forEach: " + s));

        String s = Stream.of("A", "B", "C", "D", "E")
                .collect(Collectors.joining(","));
        System.out.println(s);

        int sum = listOfIntegers.stream().reduce(0,Integer::sum);
        System.out.println(sum);
    }


}
