public class SucessDelete {
    private int[] id;
    private boolean[] connected;
    
    public SucessDelete(int n) {
        id = new int[n];
        connected = new boolean[n];

        for (int i = 0; i < n; i++) {
            id[i] = i + 1 < n ? i + 1 : i;
            connected[i] = true;
        }
    }

    public void remove(int p) {
        connected[p] = false;
    }

    public int find(int p) {
        int next = p;
        while (next < id.length) {
            if (connected[next]) {
                return next;
            } else {
                next++;
            }
        }
        return -1;
    }

}
