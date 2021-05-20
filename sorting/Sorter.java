import java.util.Random;

public abstract class Sorter {

    protected static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected static <T> void exch(T[] a, int i, int j) {
        T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static <T extends Comparable<T>> boolean isSorted(T[] a, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    // Knuth shuffle
    protected static <T extends Comparable<T>> void shuffle(T[] a) {
        int n = a.length;
        var rand = new Random();
        for (int i = 0; i < n; i++) {
            int index = rand.nextInt(i + 1);
            exch(a, i, index);
        }
    }
}
