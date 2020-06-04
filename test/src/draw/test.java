package draw;

class DrawFunction {
	   //1. field scr[80][80]
	   
	   int start;
	   int end;
	   char[][] scr = new char[80][80];
	   
	   //2. constructor
	   DrawFunction() {
	      
	   }
	   
	   DrawFunction(int start, int end) {
	      this.start = start;
	      this.end = end;
	   }
	   
	   
//	   3. method
	   void setAxe() {
	      int x;
	      int y;
	      for(x = 0; x < 10; x++) {
	         for(y = 0; y < 10; y++) {
	            scr[x][y] = '-';
	         }
	      }
	   }
	   
	   int f( int x ) {
	      
	      return x * x;
	   
	   }
	   
	   int g(int x) {
	      return x * x - 5;
	   }

	   void writeFunction() {
	      for(int i = start; i < end; i++) {
	         System.out.println(i + "\t\t" + g(i));
	      }
	   }
	   
	   void set_scr() {
	      for(int i = start; i < end; i++) {
	         scr[i][f(i)] = '*';
	      }
	   }
	   
	   void move_scr(int xMove, int yMove) {
	      for(int i = start; i <= end; i++) {
	         scr[i+xMove][g(i)+yMove] = '*';
	      }
	   }
	   
	   void drawGraph(String functionName) {
	      int x;
	      int y;
	      System.out.println("This is the graph of " + functionName);
	      for (x = 0; x < 80; x++) {
	         for (y = 0; y < 80; y++) {
	            System.out.print(scr[x][y]);
	         }
	         
	         System.out.println();
	      }
	   }


	   void clearGraph() {
	      int x;
	      int y;
	      for (x = 0; x < 80; x++) {
	         for (y = 0; y < 80; y++) {
	            scr[x][y] = ' ';
	         }
	      }
	   }
}


public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      DrawFunction df = new DrawFunction(-8, 8);
	      int xMove = 8;
	      int yMove = 5;
//	      df.setAxe();
	      df.writeFunction();
	      df.move_scr(xMove, yMove);
	      df.drawGraph("F(x)");
	      
	      
	      df.clearGraph();
	      df.drawGraph("ClearGraph");
	      
	}

}
