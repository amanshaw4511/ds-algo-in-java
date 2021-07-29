
public class SqMatrix extends Matrix {

	SqMatrix(int size) {
		super(size, size);
	}

    SqMatrix(int[][] mat) {
        super(mat);
    }

    // fn(r2, r0) =   R2 -  m[r2][r0]/m[r0][r0] * R0
    private void op(double[][] ident, double[][] mat, int destRow, int sourceRow){
        int size = mat[0].length;
        double multiplier = mat[destRow][sourceRow]/mat[sourceRow][sourceRow];
        for (int ci=0; ci<size; ci++) {
            mat[destRow][ci] -= multiplier * mat[sourceRow][ci];
            ident[destRow][ci] -= multiplier * ident[sourceRow][ci];
        }
    }


    public double[][] inverseGauss(double[][] mat) {
        int size = mat.length;
        double[][] ident = new double[size][size];
        for (int i=0; i<size; i++) {
            ident[i][i] = 1;
        }
        // x  x  x
        // x  x  x
        // x  x  x

        // R1 -> R1 - R0 * m[1][0]/m[0][0]
        // R2 -> R2 - R0 * m[2][0]/m[0][0]
        //
        // R2 -> R2 - R1 * mat[2][1]/mat[1][1]
        for (int ri=0; ri<size-1; ri++) {
            for (int rj=ri+1; rj<size; rj++) {
                this.op(ident, mat, rj, ri);
            }
        }
        // x  x  x
        // 0  x  x
        // 0  0  x

        // R0 -> R0 - R2 * mat[0][2]/mat[2][2] 
        // R1 -> R1 - R2 * mat[1][2]/mat[2][2]
        //
        // R0 -> R0 - R1 * mat[0][1]/mat[1][1]
        for (int ri=size-1; ri>0; ri--) {
            for (int rj=ri-1; rj>=0; rj--) {
                this.op(ident, mat, rj, ri);
            }
        }
        // x  0  0
        // 0  x  0
        // 0  0  x


        // make identity matrix
        // R0 -> R0 / mat[0][0];
        // R1 -> R1 / mat[1][1];
        // R2 -> R2 / mat[2][2];
        for (int ri=0; ri<size; ri++) {
            double divider = mat[ri][ri];
            for (int ci=0; ci<size; ci++) {
                mat[ri][ci] /= divider;
                ident[ri][ci] /= divider;
            }
        }
        // 1  0  0
        // 0  1  0
        // 0  0  1

        return ident;
    }

    public int rank(double[][] mat) {
        int size = mat.length;

        for (int ri=0; ri<size-1; ri++) {
            for (int rj=1; rj<size; rj++) {
                // operation
                double multiplier = mat[rj][ri]/mat[ri][ri];
                for (int ci=0; ci<size; ci++) {
                    mat[rj][ci] -= multiplier * mat[ri][ci];
                }

            }
        }

        // count number of nonZero diagonal element
        int rank = 0;
        for (int i=0; i<size; i++) {
            rank += mat[i][i] == 0 ? 0 : 1;
        }
        return rank;

    }

    public void print(double[][] mat) {
        if (true) return;
        for (int r=0; r<mat.length; r++) {
            for (int c=0; c<mat[0].length; c++) {
                System.out.print(mat[r][c] + " ");
            }
            System.out.println();
        }
    }



}
