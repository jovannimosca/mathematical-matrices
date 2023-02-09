/**
* Class Description: represents a matrix in the mathematical sense.
* @author Jovanni Mosca
* @version 11-06-2021
*/
import java.util.ArrayList;
import java.lang.Math;

public class Matrix {
   private int rows;
   private int cols;
   protected ArrayList<ArrayList<Double>> data;

   /* EXAMPLE DECLARATION OF MATRIX DATA:
   ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
   data.add(Arrays.asList(a, b, c));
   data.add(Arrays.asList(d, e, f));
   data.add(Arrays.asList(g, h, i));
   ...
   */

   // ------------------------- CONSTRUCTOR -------------------------
   Matrix(ArrayList<ArrayList<Double>> data) {
      this.rows = data.size();
      this.cols = data.get(0).size();
      this.data = new ArrayList<ArrayList<Double>>(data);
      // Check if # elements in each row is the same, otherwise throw an exception.
   }

   // ------------------- ACCESSOR/MUTATOR METHODS -------------------

   public int getRows() {
      return rows;
   }

   public int getCols() {
      return cols;
   }

   public ArrayList<ArrayList<Double>> getData() {
      return this.data;
   }

   public ArrayList<Double> getRow(int row) {
      try {
         return data.get(row);
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid row index! " + e.getMessage());
         return null;
      }
   }

   public void setRow(int row, ArrayList<Double> data) {
      try {
         this.data.set(row, data);
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid row entry or index! " + e.getMessage());
         System.out.println("Matrix not modified.");
      }
   }

   public ArrayList<Double> getCol(int col) {
      ArrayList<Double> colList = new ArrayList<Double>();
      try {
         for (int row = 0; row < rows; row++) {
            colList.add(data.get(row).get(col));
         }
         return colList;
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid column index! " + e.getMessage());
         return null;
      }
   }

   public void setCol(int col, ArrayList<Double> data) {
      try {
         for (int row = 0; row < rows; row++) {
            this.data.get(row).set(col, data.get(row));
         }
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid column entry or index! " + e.getMessage());
         System.out.println("Matrix not modified.");
      }
   }

   public Double getVal(int row, int col) {
      return data.get(row).get(col);
   }

   public void setVal(int row, int col, Double val) {
      data.get(row).set(col, val);
   }

   // -------------------------------- DEFAULTS ---------------------------------

   public String toString() {
      int cell = 4;
      String mString = "";
      for (int row = 0; row < rows; row++) {
         mString += "|";
         for (int col = 0; col < (cols - 1); col++) {
            mString += String.format("%-"+cell+"s ", this.getVal(row, col));
         }
         mString += String.format("%s", this.getVal(row, (cols-1))); // No extra spacing for last.
         mString += "|\n";
      }
      return mString;
   }

   public boolean equals(Matrix other) {
      return data.equals(other.getData());
   }
   
   public Matrix clone() {  // Deep copy of Matrix.
      ArrayList<ArrayList<Double>> clData = new ArrayList<ArrayList<Double>>();
      for (int row = 0; row < rows; row++) {
         ArrayList<Double> tmpRow = new ArrayList<Double>();
         for (int col = 0; col < cols; col++) {
            tmpRow.add((Double) data.get(row).get(col).doubleValue());
         }
         clData.add(tmpRow);
      }
      return new Matrix(clData);
   }

   // -------------------------------- UTILITIES --------------------------------
   public void rowSwap(int src, int dst) {
      ArrayList<Double> srcRow = this.getRow(src);
      ArrayList<Double> dstRow = this.getRow(dst);
      this.setRow(src, dstRow);
      this.setRow(dst, srcRow);
   }

   public void colSwap(int src, int dst) {
     ArrayList<Double> srcCol = this.getCol(src);
     ArrayList<Double> dstCol = this.getCol(dst);
     this.setCol(src, dstCol);
     this.setCol(dst, srcCol);
   }

   public void rowMultiply(int row, Double scalar) {
     ArrayList<Double> aRow = this.getRow(row);
     for (int i = 0; i < cols; i++) {
       this.data.get(row).set(i, (scalar * this.data.get(row).get(i)));
     }
     this.setRow(row, aRow);
   }

   public void rowAdd(int src, int dst) {
     ArrayList<Double> aRow = this.getRow(dst);
     for (int i = 0; i < cols; i++) {
       aRow.set(i, (this.getRow(src).get(i) + this.getRow(dst).get(i)));
     }
     this.setRow(dst, aRow);
   }

   public void rowDel(int row) {
     this.data.remove(row);
     this.rows = this.data.size();
   }

   public void colDel(int col) {
     for (int row = 0; row < rows; row++) {
       this.data.get(row).remove(col);
     }
     this.cols = this.data.get(0).size();
   }

   // Helper function: searches an ArrayList and returns index of nonzero
   // element, or -1.
   private int nonzero(ArrayList<Double> list) {
     for (int i = 0; i < list.size(); i++) {
       if (list.get(i) != 0) {
         return i;
       }
     }
     return -1;
   }
   
   public boolean isPivot(int row, int col) {
      // Zero element cannot be a pivot.
      if (this.getVal(row, col) != 0) {
         // Special Case: item in first column & first row:
         if ((col == 0) && (row == rows - 1)) {
            return true;
         // Special Case: item in first column:
         } else if ((col == 0) &&  (this.getVal(row + 1, col) == 0)) {
            return true;
         // Special Case: item in first row:
         } else if ((row == rows - 1) && (this.getVal(row, col - 1) == 0)) {
            return true;
         // General Case:
         } else if ((this.getVal(row + 1, col) == 0) && (this.getVal(row, col - 1) == 0)) {
            return true;
         }
      }
      return false;
   }

   // public Matrix toEchelon() {
   //   Matrix aMatrix = this;  // Alter copy of matrix.
   //   for (int col = 0; col < cols; col++) {
   //     if (this.nonzero(this.getCol(col)) != -1) {  // Find column w/ nonzero.
   //       this.rowSwap(0, this.nonzero(this.getCol(col)));  // 1st row nonzero.
   //       for (int row = this.nonzero(this.getCol(col)); row < rows; row++) {
   //         this.rowAdd
   //       }
   //     }
   //   }
   // }

   public Double det() {
     Double det;  // Store determinant as it is recursively modified (or not).
     if ((cols == 1) && (rows == 1)) {
       det = this.getVal(0, 0);  // det() of 1x1 matrix [a] is just a.
     } else if ((cols == 2) && (rows == 2)) {
       // det() of 2x2 matrix follows formula ad-bc.
       det = ((this.getVal(0, 0) * this.getVal(1, 1)) - (this.getVal(0, 1) * this.getVal(1, 0)));
     } else if (cols == rows) {
       det = 0.0;
       for (int col = 0; col < cols; col++) {  // det() of an nxn matrix is (-1)^col * a * Aij in first row.
         Matrix tmpMatrix = this.clone();
         tmpMatrix.rowDel(0);
         tmpMatrix.colDel(col);
         det += ((Math.pow(-1, col) * this.getVal(0, col)) * tmpMatrix.det());
       }
     } else {
       System.out.println("A determinant cannot be computed for a non-square matrix!");
       det = null;
     }
     return det;
   }

}
