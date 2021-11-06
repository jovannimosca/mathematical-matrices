/**
* Class Description: represents a matrix in the mathematical sense.
* @author Jovanni Mosca
* @version 11-06-2021
*/
import java.util.ArrayList;

public class Matrix<N extends Number> {
   private int rows;
   private int cols;
   protected ArrayList<ArrayList<N>> data;

   /* EXAMPLE DECLARATION OF MATRIX DATA:
   ArrayList<ArrayList<N>> data = new ArrayList<ArrayList<N>>();
   data.add(Arrays.asList(a, b, c));
   data.add(Arrays.asList(d, e, f));
   data.add(Arrays.asList(g, h, i));
   ...
   */

   // ------------------------- CONSTRUCTOR -------------------------
   Matrix(ArrayList<ArrayList<N>> data) {
      this.rows = data.size();
      this.cols = data.get(0).size();
      this.data = new ArrayList<ArrayList<N>>(data);
      // Check if # elements in each row is the same, otherwise throw an exception.
   }

   // ------------------- ACCESSOR/MUTATOR METHODS -------------------

   public int getRows() {
      return rows;
   }

   public int getCols() {
      return cols;
   }

   public ArrayList<ArrayList<N>> getData() {
      return this.data;
   }

   public ArrayList<N> getRow(int row) {
      try {
         return data.get(row);
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid row index! " + e.getMessage());
         return null;
      }
   }

   public void setRow(int row, ArrayList<N> data) {
      try {
         this.data.set(row, data);
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid row entry or index! " + e.getMessage());
         System.out.println("Matrix not modified.");
      }
   }

   public ArrayList<N> getCol(int col) {
      ArrayList<N> colList = new ArrayList<N>();
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

   public void setCol(int col, ArrayList<N> data) {
      try {
         for (int row = 0; row < rows; row++) {
            this.data.get(row).set(col, data.get(row));
         }
      } catch (IndexOutOfBoundsException e) {
         System.out.println("Invalid column entry or index! " + e.getMessage());
         System.out.println("Matrix not modified.");
      }
   }

   public N getVal(int row, int col) {
      return data.get(row).get(col);
   }

   public void setVal(int row, int col, N val) {
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
      ArrayList<N> srcRow = this.getRow(src);
      ArrayList<N> dstRow = this.getRow(dst);
      this.setRow(src, dstRow);
      this.setRow(dst, srcRow);
   }

   public void colSwap(int src, int dst) {
     ArrayList<N> srcCol = this.getCol(src);
     ArrayList<N> dstCol = this.getCol(dst);
     this.setCol(src, dstCol);
     this.setCol(dst, srcCol);
   }

   // public void rowMultiply(int row, N scalar){
   //   ArrayList<N> aRow = this.getRow(row);
   //   // Since we are using N generic type, we must make several cases for
   //   // multiplication depending on known types:
   //   if (scalar.instanceOf(Integer)) {
   //     for (int i = 0; i < aRow.size(); i++){
   //       aRow.set(i, (N) ((Integer) scalar * (Integer) aRow.get(i)));
   //     }
   //   }
   //   this.setRow(row, aRow);
   // }
}
