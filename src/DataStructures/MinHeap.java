package DataStructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Min heap implementation based on array list.
 * Insertion: O(log n)
 * Delete Min: O(log n)
 * Get Min: O(1)
 */
public class MinHeap<T extends Comparable<T>> {

    private List<T> elements;


    /**
     * Default Constructor
     */
    public MinHeap() {
        elements = new ArrayList<T>();
        // Add a null value so that the first element can be inserted at index 1.
        elements.add(null);
    }

    /**
     * Insertion happens at the end of the list. moveUp() shifts the element up until its value is lower that the
     * parent or until it becomes the root. Which ever happens sooner.
     * parent index = 1/2 * current index
     * @param idx
     */
    private void moveUp(int idx) {
        if (idx <= 1) return;
        T element = elements.get(idx);
        T parent = elements.get(idx / 2);
        if (parent.compareTo(element) > 0) {
            elements.set(idx / 2, element);
            elements.set(idx, parent);
            moveUp(idx / 2);
        }
    }

    /**
     * Inserts an element into the min heap
     * @param val
     */
    public void insert(T val) {
        elements.add(val);
        moveUp(elements.size() - 1);
    }

    /**
     * When the root is deleted, it is replaced by the last element in the list. This is then shifted down until
     * it reaches the position where the both children are larger.
     *
     * left child index = 2 * current index
     * right child index = 2 * current index + 1
     * @param idx
     */
    private void moveDown(int idx) {
        if (2 * idx >= elements.size()) return;
        T element = elements.get(idx);
        T left = elements.get(2 * idx);
        T right = (2 * idx + 1 >= elements.size()) ? null : elements.get(2 * idx + 1);

        // Swap with the smallest child if applicable
        if (right == null || left.compareTo(right) < 1) {
            if (left.compareTo(element) < 0) {
                elements.set(2 * idx, element);
                elements.set(idx, left);
                moveDown(2 * idx);
            } else {
                return;
            }
        } else {
            if (right.compareTo(element) < 0) {
                elements.set(2 * idx + 1, element);
                elements.set(idx, right);
                moveDown(2 * idx + 1);
            } else {
                return;
            }
        }
    }

    /**
     * Deletes the root
     * @return root
     */
    public T deleteMin() {
        if (elements.size() < 2) return null;
        T val = elements.get(1);
        elements.set(1, elements.get(elements.size() - 1));
        elements.remove(elements.get(elements.size() - 1));
        moveDown(1);
        return val;
    }

    /**
     * Returns the root
     * @return root
     */
    public T getMin() {
        if (elements.size() < 2) return null;
        T val = elements.get(1);
        return val;
    }

    /**
     * Displays all elements in the heap
     */
    public void displayElements() {
        Iterator<T> itr = elements.iterator();
        itr.next();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println("");

    }

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        System.out.println(heap.getMin());
        System.out.println(heap.deleteMin());
        heap.insert(5);
        heap.displayElements();
        heap.insert(10);
        heap.displayElements();
        heap.insert(3);
        heap.displayElements();
        System.out.println(heap.getMin());
        System.out.println(heap.deleteMin());
        heap.displayElements();
        System.out.println(heap.deleteMin());
        System.out.println(heap.deleteMin());
        System.out.println(heap.deleteMin());
    }

}
