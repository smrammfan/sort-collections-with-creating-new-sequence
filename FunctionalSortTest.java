package com.company.fpPractice;


import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FunctionalSortTest {

    private List<Integer> unsorted;
    private List<Integer> sorted;
    private List<Integer> reverse;
    private List<Integer> emptyList;

    @Before
    public void init() {
        unsorted = Stream.of(1, 4, 1, 4, 2, 2, 0, 9, 10, 3).collect(Collectors.toList());
        reverse = unsorted.stream().sorted((a, b) -> b - a).collect(Collectors.toList());
        sorted = unsorted.stream().sorted().collect(Collectors.toList());
        emptyList = List.of();
    }

    @Test(expected = IllegalArgumentException.class)
    public void selectionSortTest() {
        FunctionalSort.sectionSort(null, Integer::compareTo);
        assertEquals(emptyList, FunctionalSort.sectionSort(emptyList, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.sectionSort(reverse, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.sectionSort(unsorted, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.sectionSort(sorted, Integer::compareTo));

    }

    @Test(expected = IllegalArgumentException.class)
    public void insertionSortTest() {
        FunctionalSort.insertionSort(null, Integer::compareTo);
        assertEquals(emptyList, FunctionalSort.insertionSort(emptyList, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.insertionSort(reverse, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.insertionSort(unsorted, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.insertionSort(sorted, Integer::compareTo));
    }

    @Test(expected = IllegalArgumentException.class)
    public void bubbleSortTest() {
        FunctionalSort.bubbleSort(null, Integer::compareTo);
        assertEquals(emptyList, FunctionalSort.bubbleSort(emptyList, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.bubbleSort(reverse, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.bubbleSort(unsorted, Integer::compareTo));
        assertEquals(sorted, FunctionalSort.bubbleSort(sorted, Integer::compareTo));
    }

}
