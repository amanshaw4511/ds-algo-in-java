package com.aman.ds;

public class UnionFind {
    private int size;
    private int nComponents ;
    private int[] parent;
    private int[] rank;
    private int[] sz;

    UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("size <= 0 not allowed");
        this.size = size;
        this.nComponents = size;
        this.parent = new int[size];
        this.rank = new int[size];
        this.sz = new int[size];
        for (int i=0; i<size; i++) {
            this.parent[i] = i;
            this.rank[i] = 1;
            this.sz[i] = 1;
        }
    }

    public int size() {
        return this.size;
    }
    
    public int components() {
        return this.nComponents;
    }

    public int find(int i) {
        if (this.parent[i] == i) {
            return i;
        }
        int iparent  = find(this.parent[i]);
        this.parent[i] = iparent;
        return iparent;
    }
    
    public void union(int i, int j) {
        int irep = this.find(i);
        int jrep = this.find(j);

        if (irep == jrep) return;

        if (this.rank[irep] > this.rank[jrep]) {
            this.parent[jrep] = irep;
            this.sz[irep] += this.sz[jrep];
        } else if (this.rank[irep] < this.rank[jrep]) {
            this.parent[irep] = jrep;
            this.sz[jrep] += this.sz[irep];
        } else {
            this.parent[irep] = jrep;
            this.sz[jrep] += this.sz[irep];
            this.rank[jrep] += 1;
        }

        this.nComponents -= 1;
    }

    public boolean connected(int i, int j) {
        return this.find(i) == this.find(j);
    }

    public int componentSize(int comp) {
        return this.sz[this.find(comp)];
    }
}
