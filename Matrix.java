/**
* Class Description: represents a matrix in the mathematical sense.
* @author Jovanni Mosca
* @version 11-06-2021
*/
import java.util.ArrayList;

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
      int cell = 3;
      String mString = "";
      for (int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            mString += String.format("%-"+cell+"s ", data.get(row).get(col));
         }
         mString += "\n";
      }
      return mString;
   }

   public boolean equals(Matrix other) {
      return data.equals(other.getData());
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

   public boolean isPivot(int row, int col) {
     if (this.getVal(row, col) != 0) {  // Pivot must be nonzero.
       // Determine if it really is a pivot...
     }
     return false;
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

   public Matrix toEchelon() {
     Matrix aMatrix = this;  // Alter copy of matrix.
     for (int col = 0; col < cols; col++) {
       if (this.nonzero(this.getCol(col)) != -1) {  // Find column w/ nonzero.
         this.rowSwap(0, this.nonzero(this.getCol(col)));  // 1st row nonzero.
         for (int row = this.nonzero(this.getCol(col)); row < rows; row++) {
           this.rowAdd
         }
       }
     }

   }
}
