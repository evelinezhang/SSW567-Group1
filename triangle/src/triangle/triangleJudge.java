package triangle;

import java.util.Scanner;
public class triangleJudge
{
private static Scanner sc;

public static void main(String[] args) 
   {
    
    sc = new Scanner(System.in);
    double[] side = new double[3];
   
    for(int i=1; i<4; i++) {
    	boolean sideOk = false;
	    do {
	    	//Prompt user for the length of side
	    	System.out.print("Please input Length of side" + i + ": " );
	        try {
	            side[i-1] = Double.parseDouble(sc.next());
	            sideOk = side[i-1] > 0;
	            if (!sideOk)
	            	System.out.println("Illegal Input Detected, side" + i + " must be greater than Zero");
	        } catch (NumberFormatException e) {
	            System.out.println("Illegal Input Detected, side" + i + " not numeric");
	        }
	   } while (!sideOk);
    }
    //print out the result
    System.out.println(Judge(side[0],side[1],side[2]));
   
   }
    

   // Triangle judgment function
   public static String Judge(double side1, double side2, double side3) {
            
	   double side1Sq = side1*side1;  //represents (side1)^2
	   double side2Sq = side2*side2;  //represents (side2)^2
	   double side3Sq = side3*side3;  //represents (side3)^2
    
	    //check if this is a triangle, triangle must meet the formula: a < b + c, where a,b,c represents sides of the triangle
	    if(!(side1 < side2 + side3 && side2 < side1 + side3 && side3 < side1 + side2)) {
	    	 return "Not triangle"; //if the sides cannot meet the requirement a < b + c, then it's not a triangle
	    }
	    
	    // this is a triangle
	    
	    //  if a^2 = b^2 + c^2, where a,b,c represent sides of the triangle, then return "right triangle" (Pythagoras Theorem)	
	    //	must check this first or it will be reported as other type before evaluation 
	    if(side1Sq + side2Sq==side3Sq || side2Sq + side3Sq == side1Sq || side3Sq + side1Sq == side2Sq){
	       return "right triangle"; 
	    }
	   
	    // now evaluate sides for equality
	    
		//if side1,side2,side3 are all different values ,then return "scalene"
		if((side1 != side2) && (side2 != side3) && (side1 != side3)){ 
			return "scalene";
		//if side1=side2=side3 then return "equilateal"	
		}else if(side1==side2 && side2 == side3){ 
	        return "equilateral";
	   //if only two of the three sides are the same,then display "isosceles"
		}else if(((side1==side2)&&(side2!=side3))||((side2==side3)&&(side3!=side1))||((side1==side3)&&(side2!=side1))){
			return "isosceles"; 
	    } else {
	    	return "error"; //should not be here since all triangle have 1, 2, or 3 equal sides
	    }

   	}
    
}