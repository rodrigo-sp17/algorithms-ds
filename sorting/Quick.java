public class Quick extends Sorter {

     /**
      * Quick Select implementation
      * Linear time on average
      * Quadratic in worst case, but chances are low
      *
      * Use when quicksort itself is not needed
      */
    public static <T extends Comparable<T>> T select(T[] a, int k) {
        int lo = 0, hi = a.length - 1;

        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    /**
     * Quick Sort implementation
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int i = lo, j = hi;

        while (true) {
            while (less(a[++i], a[lo])) {
                if (i == hi) {
                    break;
                }
            }
            while (less(a[lo], a[--j])) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }


    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        // 3-way partitioning (Dijkstra)
        // Reduces running time from linearithmic to linear in lots of applications!
        // N log N when all distinct, linear when only a constant number of distinct keys
        int lt = lo,gt = hi;
        T v = a[lo];
        int i = lo;

        while (i <= gt) {   // When we reach gt, all to the right are greater than our partitioning element
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);

        // This is the original part
        /*int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);*/
    }





}
