public class Sorter {

    // Cutoff value for mergesort
    private static final int CUTOFF = 7;
    
    public static <T extends Comparable<T>> void selectionSort(T[] items) {
        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i + 1; j < items.length; j++) {
                if (less(items[j], items[min])) {
                    min = j;
                }
            }
            exch(items, i, min);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void shellSort(T[] a) {
        // Knuth increment -> 3x + 1
        // From Sedgewick himself -> merge (9 x 4^i) - (9 x 2^i) + 1 and 4^i - (3 x 2^i) + 1
        int n = a.length;
        int h = 1;
        while (h < n/3) {
            h -= (3 * h) + 1;
        }

        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }

            h = h/3;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        T[] aux = (T[]) new Object[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] aux, int lo, int hi) {
/*        if (hi <= lo) {
            return;
        }*/

        // Improves performance, maybe 20%
        if (hi <= lo + CUTOFF - 1) {
            insertionSort(a, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid); // inverted to avoid aux copy
        sort(aux, a, mid, hi); // inverted to avoid aux copy
        // Second improvement - avoids another merge
        if (!less(a[mid + 1], a[mid])) {
            return;
        }
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

/*        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }*/

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                //a[k] = aux[j++];
                aux[k] = a[j++];
            } else if (j > hi) {
                //a[k] = aux[i++];
                aux[k] = a[i++];
            } else if (less(a[j], a[i])) {
                //a[k] = aux[j++];
                aux[k] = a[j++];
            } else {
                //a[k] = aux[i++];
                aux[k] = a[i++];
            }
        }

        assert isSorted(a, lo, hi);
    }


    private static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    private static <T> void exch(T[] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] a, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }
}