package com.company.fpPractice;


import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements simple sort algorithms for
 * <a href = "https://docs.oracle.com/javase/7/docs/api/java/util/Collection.html">Collection interface</a>
 * to the provided {@code Comparator} with creating new List.
 * To implement these algorithms, recursion and foreach were used. {@code Stream} was not used.<br>
 * <strong>Methods</strong>
 * <ul>
 *     <li>{@link FunctionalSort#sectionSort(Collection, Comparator)}</li>
 *     <li>{@link FunctionalSort#insertionSort(Collection, Comparator)}</li>
 *     <li>{@link FunctionalSort#bubbleSort(Collection, Comparator)} </li>
 * </ul>
 * @author Maksym Sadovenko
 * @version 1.0
 */

public class FunctionalSort {

    /**
     * Implements section sort.
     * @param unsorted the Collection to be sorted
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return Sorted List
     * @see FunctionalSort#sectionSortInner(List, List, Comparator)
     * @see FunctionalSort#getMinElement(List, Comparator)
     */
     public static <T> List<T> sectionSort(@NotNull Collection<T> unsorted, Comparator<T> comparator){
         return sectionSortInner(new LinkedList<>(), new LinkedList<>(unsorted), comparator);
     }

    /**
     *  Inner method for section sort. It searches min element in unsorted part and adds it to sorted part
     * @param sorted sorted part of collection
     * @param unsorted unsorted part of collection
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return Sorted List
     */
     private static <T> List<T> sectionSortInner(List<T> sorted, List<T> unsorted, Comparator<T> comparator){
        if(unsorted.size() == 0){
            return sorted;
        }else {
            T minElement = getMinElement(unsorted, comparator);
            sorted.add(0, minElement);
            unsorted.remove(minElement);
            return sectionSortInner(sorted, unsorted, comparator);
        }
     }

    /**
     * Returns min element for input list
     * @param lst input list
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return Min element in input list
     */
     private static <T> T getMinElement(List<T> lst, Comparator<T> comparator){
        T min = lst.get(0);
        for (T el : lst){
            if(comparator.compare(min, el) < 0)
                min = el;
        }
        return min;
     }


    /**
     * Implements insertion sort.
     * @param unsorted the Collection to be sorted
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return Sorted List
     * @see FunctionalSort#insertionSortInner(List, List, Comparator)
     * @see FunctionalSort#getIndexForInsert(Object, List, Comparator)
     */
    public static <T> List<T> insertionSort(@NotNull Collection<T> unsorted, Comparator<T> comparator) {
         return insertionSortInner(new LinkedList<>(), new LinkedList<>(unsorted), comparator);
    }

    /**
     * Inner method for insertion sort. Gets first element unsorted part and puts It in sorted part with
     * right index
     * @param sorted sorted part of collection
     * @param unsorted unsorted part of collection
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return sorted LinkedList
     */
    private static <T> List<T> insertionSortInner(List<T> sorted, List<T> unsorted, Comparator<T> comparator) {
         if(unsorted.size() == 0){
             return sorted;
         }

         T elementForInsert = unsorted.remove(0);
         sorted.add(getIndexForInsert(elementForInsert, sorted, comparator), elementForInsert);
         return insertionSortInner(sorted, unsorted, comparator);
    }


    /**
     * Additional method for insertion sort
     * @param elementForInsert element we want to insert in sorted part
     * @param sorted sorted part of collection
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return right index in sorted part for {@code elementForInsert}
     */
    private static <T> int getIndexForInsert(T elementForInsert, List<T> sorted, Comparator<T> comparator){
        int index = 0;
        for (T el: sorted) {
            if(comparator.compare(el, elementForInsert) >= 0){
                break;
            }
            index++;
        }

        return index;
    }

    /**
     * Implements bubble sort.
     * @param unsorted the Collection to be sorted
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return sorted List
     * @see FunctionalSort#bubbleSortInner(List, List, Comparator)
     * @see FunctionalSort#bubbleSortIteration(Object, List, Comparator)
     */
    public static <T> List<T> bubbleSort(@NotNull Collection<T> unsorted, Comparator<T> comparator) {
        return bubbleSortInner(new LinkedList<>(unsorted), new LinkedList<>(unsorted), comparator);
    }


    /**
     * Inner method for bubble sort.
     * @param unsorted the Collection to be sorted
     * @param sorted sorted part of Collection
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return sorted List
     */
    private static <T> List<T> bubbleSortInner(List<T> unsorted, List<T> sorted, Comparator<T> comparator) {
        if(unsorted.size() == 0){
            return sorted;
        }
        unsorted.remove(0);
        return bubbleSortInner(unsorted, bubbleSortIteration(sorted.remove(0), sorted, comparator), comparator);
    }

    /**
     * Additional method for bubble sort. Removes first element from unsorted part of collection and puts it
     * in current position at sorted part
     * @param element element to be check
     * @param unsorted unsorted part of collection
     * @param comparator {@code Comparator} to be used to compare collection elements
     * @param <T> the type of the collection elements
     * @return unsorted list with current position of {@code element}
     */
    private static <T> List<T> bubbleSortIteration(T element, List<T> unsorted, Comparator<T> comparator){
         if (unsorted.size() == 0){
             List<T> temp = new LinkedList<>();
             temp.add(element);
             return temp;
         }

         T firstElement = unsorted.remove(0);
         List<T> temp;
         if(comparator.compare(element, firstElement) < 0){
             temp = bubbleSortIteration(firstElement, unsorted, comparator);
             temp.add(0, element);
         }else {
             temp = bubbleSortIteration(element, unsorted, comparator);
             temp.add(0, firstElement);
        }
        return temp;
    }
}
