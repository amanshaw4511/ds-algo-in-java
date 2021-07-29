import java.util.Iterator;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Matrix {
    protected int[][] data;
    protected int csize;
    protected int rsize;

    Matrix(int csize, int rsize) {
        this.data = new int[csize][rsize];
        this.csize = csize;
        this.rsize = rsize;
    }

    Matrix(int[][] mat) {
        this.rsize = mat.length;
        this.csize = mat[0].length;
        this.data = mat;
    }

    protected void swap(int[][] data1, int i1, int j1, int[][] data2, int i2, int j2) {
        int temp = data1[i1][j1];
        data1[i1][j1] = data2[i2][j2];
        data2[i2][j2] = temp;

    }

    protected void map(Function<Integer, Integer> fun) {
        for (int y = 0; y < rsize; y++) {
            for (int x = 0; x < csize; x++) {
                this.data[y][x] = fun.apply(this.data[y][x]);
            }
        }
    }

    protected Matrix map(Matrix other, BiFunction<Integer, Integer, Integer> fun) {
        if (other.csize != this.csize && other.rsize != this.rsize) {
            throw new RuntimeException("invalid dimention for operation");
        }

        Matrix result = new Matrix(this.csize, this.rsize);
        for (int y = 0; y < rsize; y++) {
            for (int x = 0; x < csize; x++) {
                result.data[y][x] = fun.apply(this.data[y][x], other.data[y][x]);
            }
        }
        return result;
    }

    public Matrix dot(Matrix other) {
        return this.map(other, (a, b) -> a * b);
    }

    public Matrix cross(Matrix other) {
        if (other.rsize != this.csize) {
            throw new RuntimeException("invalid dimention for operation");
        }
        Matrix result = new Matrix(this.rsize, other.csize);
        for (int y = 0; y < this.rsize; y++) {
            for (int ox = 0; ox < other.csize; ox++) {
                for (int z = 0; z < this.csize; z++) { // z == x == oy
                    result.data[y][ox] = this.data[y][z] * other.data[z][ox];
                }
            }
        }
        return result;
    }


    public int[][] transpose(int[][] mat) {
        int rsize = mat.length;
        int csize = mat[0].length;

        if (csize == rsize) { // transpose inplace
            for (int y = 0; y < rsize; y++) {
                for (int x = y + 1; x < csize; x++) {
                    this.swap(mat, y, x, mat, x, y);
                }
            }
            return mat;
        }

        int[][] newMat = new int[rsize][csize];
        for (int y = 0; y < rsize; y++) {
            for (int x = 0; x < csize; x++) {
                newMat[x][y] = mat[y][x];
            }
        }
        return newMat;
    }

    public void hflip() {
        for (int y = 0; y < this.rsize; y++) {
            for (int x = 0; x < this.csize / 2; x++) {
                int xx = this.csize - 1 - x;
                this.swap(this.data, y, x, this.data, y, xx);
            }
        }
    }

    public void vflip() {
        for (int x = 0; x < this.csize; x++) {
            for (int y = 0; y < this.rsize / 2; y++) {
                int yy = this.rsize - 1 - y;
                this.swap(this.data, y, x, this.data, yy, x);
            }
        }
    }

    public void rotateLeft() {
        this.vflip();
        this.data = this.transpose(this.data);
    }

    public void rotateLeft(int n) {
        n %= 4;
        switch (n) {
            case 0 :  return;
            case 1 : this.rotateLeft();
                     return;
            case 2 : this.reverse();
                     return;
            case 3 : this.rotateRight();
                     return;
            default :
        }
    }

    public void rotateRight(int n) {
        n %= 4;
        switch (n) {
            case 0 :
                return;
            case 1 :
                this.rotateRight();
                return;
            case 2 :
                this.reverse();
                return;
            case 3 :
                this.rotateLeft();
                return;
            default :
        }
    }


    public void rotateRight() {
        this.hflip();
        this.data = this.transpose(this.data);
    }

    public void reverse() {
        this.hflip();
        this.vflip();
    }
    

    /**
     * inverse(mat) = adjoint(mat) / determinant(mat)
     * @param mat
     * @return
     */
    public double[][] inverse(int[][] mat) { // n * n * n^n
        int det = this.determinant(mat);
        if (det == 0) {
            return null;
        } 

        int rsize = mat.length;
        int csize = mat[0].length;
        double[][] dmat =  new double[rsize][csize];

        mat = this.adjoint(mat);

        for (int ri=0; ri<rsize; ri++) {
            for (int ci=0; ci<csize; ci++) {
                dmat[ri][ci] = ((double)mat[ri][ci]) / det;
            }
        }
        return dmat;
    }



    /**
     * transpose(mat) =  transpose(newMat)
     *           where newMat = matrix(cofactor(ci,ri) for each ci,ri);
     * @param mat
     * @return
     */
    public int[][] adjoint(int[][] mat) { // n * n * n^n
        int rsize = mat.length;
        int csize = mat[0].length;

        int[][] newMat = new int[rsize][csize];
        for (int ri=0; ri<rsize; ri++) {
            for (int ci=0; ci<csize; ci++) {
                newMat[ri][ci] = this.cofactor(mat, ri, ci);
            }
        }
        return this.transpose(newMat);
    }


    // det (A) = (nrow == 1, ncol == 1) 
    //      ? a[0][0]
    //      : a[0][0] * coFacotr(a[0][0]) + a[0][1] * coFacotr(a[0][1]) + ...
    //
    public int determinant(int[][] mat) { // n^n
        int csize = mat[0].length;
        int rsize = mat.length;

        if (csize == 1 && rsize == 1) {
            return mat[0][0];
        }

        int det = 0;
        for (int ci=0; ci<csize; ci++) {
            det += mat[0][ci] * this.cofactor(mat, 0, ci);
        }
        return det;
    }




    // Factorial
    // T(n) = 1                 n == 0
    //      = 1 + T(n-1)        else
    //      
    //  ->  = 1 + 1 + T(n-2)          => 2 + T(n-2)
    //  ->  = 1 + 1 + 1 + ... T(n-k)  => k + T(n-k)
    //      n-k = 0  =>   k = n
    //  ->  =  n + 0
    //  ->  =  n 

    //  Fibonacci
    //  T(n) = n               n = 0,1
    //       = T(n-1) + T(n-2)   other
    //       = 2 * T(n-1)
    //   ->  = 2 * 2 * T(n-2)        =>  2^2 * T(n-2)
    //   ->  = 2 * 2 * ... T(n-k)    =>  2^k * T(n-k)
    //      n-k <= 1  =>   k = n -1
    //   ->  = 2^(n-1) * n
    //   ->  = 2^n

    // Determinant
    //  d(x) = 1         ;  x = size == 1;
    //       = x * cf(x)
    //  
    // cf(x) =  d(x-1) 
    //
    //
    // ->  d(x) = x * d(x-1)
    // ->       = x * x * d(x-2)                   =>  x^2 * d(x-2)
    // ->       = x * x * ............  d(x-k)     =>  x^k * d(x-k)
    //    x-k <= 1   =>    k <= x -1
    //          = x^(x-1) * x-1
    //          = x^x
    //   
    //   NOTE :  time complexity in recursion = x^n 
    //               if call is decreamenting by constant -> T(n-1)
    //                      where x = no of recursive call made in one call
    
    //
    // cofactor(m[c][r]) = sign * minor(m[c,r])
    public int cofactor(int[][] mat, int rr, int cc) { // n^n
        int sign = ((rr + cc) % 2 == 0) ? 1 : -1;
        return sign * this.minor(mat, rr, cc);
    }

    // minor(m[c][r]) = determinant(m1)
    //              where m1 = matrix(removing col c and row r)
    public int minor(int[][] mat, int rr, int cc) { // n^n
        int csize = mat[0].length;
        int rsize = mat.length;

        int[][] newMat = new int[rsize-1][csize-1];
        int r = 0;
        int c = 0;
        for (int ri=0; ri<rsize; ri++) {
            for (int ci=0; ci<csize; ci++) {
                if (ri == rr || ci == cc) {
                    continue;
                }
                newMat[c][r] = mat[ri][ci];
                // increment index
                r += 1;
                if (r == csize-1) {
                    c += 1;
                    r = 0;
                }
            }
        }
        return this.determinant(newMat);

    }

    protected Iterator<Integer> listiterator() {
        return new Iterator<Integer>() {
            int ix = 0;
            int iy = 0;

            @Override
            public boolean hasNext() {
                return iy >= rsize;
            }

            @Override
            public Integer next() {
                Integer value = data[iy][ix];

                if (ix >= csize) {
                    iy += 1;
                    ix = 0;
                } else {
                    ix += 1;
                }

                return value;
            }

            public void set(Integer value) {
                data[iy][ix] = value;
            }
        };
    }
}
