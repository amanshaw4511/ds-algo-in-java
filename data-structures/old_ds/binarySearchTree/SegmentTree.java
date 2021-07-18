
class SegmentTree {
    int[] tree;
    private int arrLen;

    public SegmentTree(int[] arr) {

        // 2 * 2^ceil(log2(n)) -1
        int ceilLog2n = (int)Math.ceil(Math.log10(arr.length)/ Math.log10(2));
        int treeLen =(int)( 2 * Math.pow(2, ceilLog2n) -1);
        // int treeLen = 4 * arr.length + 1;
        this.tree = new int[treeLen];

        this.arrLen = arr.length;
        buildTree(arr, 1, 0, arrLen -1);
        
    }
    private int buildTree(int[] arr, int pos, int l, int r) {
        // base condition
        if (l == r) {
            tree[pos] = arr[l];
            return tree[pos];
        }

        // induction
        int mid = l + (r - l) / 2;
        tree[pos] = Integer.min(
                    buildTree(arr, 2*pos, l, mid),         // left subTree
                    buildTree(arr, 2*pos +1, mid+1, r)       // right subTree
                    );

        return tree[pos];
    }

    public int queryMin(int qstart, int qend) {
        return queryHelper(1, 0, arrLen-1, qstart, qend);
    }

    private int queryHelper(int pos, int l, int r, int qstart, int qend) {
        // no overlap
        if (qstart > r || qend < l) 
            return Integer.MAX_VALUE;

        // complete overlap
        if (qstart <= l && qend >= r)
            return tree[pos];

        // partial ovelap
        int mid = l + (r-l)/2;
        return Integer.min(
                queryHelper(pos*2, l, mid, qstart, qend),
                queryHelper(pos*2 +1, mid+1, r, qstart, qend)
                );
    }
    public int updateQuery(int qstart, int qend) {
        return updateQueryHelper(1, 0, arrLen-1, qstart, qend);
    }

    private int updateQueryHelper(int pos, int l, int r, int qstart, int qend) {
        // no overlap
        if (qstart > r || qend < l)
            return tree[pos];

        if (l == r) {
            tree[pos]++;
            return tree[pos];
        }

        // complete overlap
        // partial overlap
        int mid = l + (r-l)/2;
        tree[pos] = Integer.min(
                updateQueryHelper(pos*2, l, mid, qstart, qend),
                updateQueryHelper(pos*2 +1, mid+1, r, qstart, qend)
            );
        return tree[pos];
    }
}

