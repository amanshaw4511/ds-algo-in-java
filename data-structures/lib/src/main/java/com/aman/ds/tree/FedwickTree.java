import java.util.Arrays;

/**
 * Construction         n
 * Point Update         log(n)
 * Range Sum            log(n)
 * Range Update         log(n)
 * Adding index          NA
 * Removing index        NA
 * */


class FedwichTree{
    private int size;
    private int[] data;

    FedwichTree(int size) {
        this.data = new int[size + 1];
        this.size = size;
    }

    FedwichTree(int[] array) {
        this.size = array.length;
        this.constructTree(array);
    }

    public int size() {
        return this.size;
    }

    private void constructTree(int[] array) {
        this.data = array.clone();
        for (int i=1; i<array.length; i++) {
            int j = i + lsb(i);
            if (j < array.length) {
                this.data[j] += this.data[i];
            }
        }
    }

    public long sum(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start index >= end index");
        }
        return prefixSum(end) - prefixSum(start - 1);
    }

    public void set(int index, long value) {
        long prevValue = this.sum(index, index);
        this.add(index, value - prevValue);
    }

    public void add(int index, long value) {
        while (index < this.data.length) {
            this.data[index] += value;
            index += lsb(index);
        }
    }

    private int lsb(int index) {
        return index & -1; // Integer.lowestOneBit(index)
    }

    private long prefixSum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += this.data[index];
            index &= ~lsb(index); // equivalent to: index -= lsb(index)
        }
        return sum;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.data);
    }

}

