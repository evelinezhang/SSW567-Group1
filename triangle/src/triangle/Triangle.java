package triangle;

import java.util.Scanner;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Triangle
{
private static Scanner sc;

public static void main(String[] args) 
   {
   
   double[] side = new double[3];
   
   if (args.length < 1) {
        boolean quit; 
	 do {
            side = getSides();
            quit = side == null;
            //print out the result
            if (side != null) {
                System.out.println(Judge(side[0],side[1],side[2]) + "\n");
            }
        } while (!quit);			
   } else {
       String entry = args[0];
       String[] token = entry.split(",");
       int len = token.length;
       if (len != 3) {
            System.out.println("Triangle must have 3 sides, " + len + " provided");
       } else {
           boolean sidesOk = true;
           for(int i=1; i<4 ; i++) {
                String newside = EvalNumbericString(token[i-1]);
                side[i-1] = ValidSide(newside, i);
                if (side[i-1] <= 0)
                 sidesOk = false;
            }
           if (sidesOk) {
                System.out.println(Judge(side[0],side[1],side[2]) + "\n");
          }
       }
   }
    
   }

private static double[] getSides() {
    
    sc = new Scanner(System.in);
    double[] side = new double[3];
    boolean quit = false;
    
    for(int i=1; i<4 &&(!quit); i++) {
        boolean sideOk = false;
        do {
            //Prompt user for the length of side
            System.out.print("Please input Length of side" + i + ": " );
            String entry = sc.next();
            if (entry.equals("quit")) {
                quit = true;
                side = null;
            } else {
                side[i-1] = ValidSide(entry, i);
                if (side[i-1] > 0)
                 sideOk = true;
            }

       } while (!sideOk && !quit);
}
    
    return side;
}

private static String EvalNumbericString(String entry) {
   
   String numbericString;
   try {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        entry = entry.replace("sqrt", "Math.sqrt");
        entry = entry.replace("log", "Math.log");
        entry = entry.replace("sin", "Math.sin");
        entry = entry.replace("cos", "Math.cos");
        entry = entry.replace("tan", "Math.tan");
        
        numbericString = engine.eval(entry).toString();
    } catch (ScriptException ex) {
        return "";
    }
  
   return numbericString;
   
}
 // Return length if Valid Side
  private static double ValidSide(String entry, int sideNum) {
          boolean sideOk;
          double side;
                  
          try {
                side = Double.parseDouble(entry);
                sideOk = side > 0;
                if (!sideOk) {
                    System.out.println("Illegal Input Detected, side" + sideNum + "  must be greater than Zero");
                    side = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("Illegal Input Detected, side" + sideNum + " not numeric or too large");
                side= 0;
            }
          return  side;
  }
  
  private static boolean IsRightTriangle(double side1, double side2,  double side3) {
      
           BigDecimal bigside1 = new BigDecimal(side1);
           BigDecimal bigside2 = new BigDecimal(side2);
           BigDecimal bigside3 = new BigDecimal(side3);
        
           BigDecimal side1Sq = bigside1.multiply(bigside1).round(new MathContext(3, RoundingMode.HALF_UP));  //represents (side1)^2
	   BigDecimal side2Sq = bigside2.multiply(bigside2).round(new MathContext(3, RoundingMode.HALF_UP));  //represents (side2)^2
	   BigDecimal side3Sq = bigside3.multiply(bigside3).round(new MathContext(3, RoundingMode.HALF_UP));  //represents (side3)^2
           
           //  if a^2 = b^2 + c^2, where a,b,c represent sides of the triangle, then return "right triangle" (Pythagoras Theorem)	
	    //	must check this first or it will be reported as other type before evaluation 
	   boolean compare1 = side1Sq.add(side2Sq).compareTo(side3Sq) == 0;
           boolean compare2 = side2Sq.add(side3Sq).compareTo(side1Sq) == 0;
           boolean compare3 = side3Sq.add(side1Sq).compareTo(side2Sq) == 0;
           
           if (compare1 || compare2 || compare3) {
	       return true; 
	    } else {
                return false;
            }
  }

// Triangle judgment function
   private static String Judge(double side1, double side2, double side3) {
    
	    //check if this is a triangle, triangle must meet the formula: a < b + c, where a,b,c represents sides of the triangle
	    if(!(side1 < side2 + side3 && side2 < side1 + side3 && side3 < side1 + side2)) {
	    	 return "Not a triangle"; //if the sides cannot meet the requirement a < b + c, then it's not a triangle
	    }
	    
	    // this is a triangle
	    // now evaluate sides for equality
            
            String typeTriangle = "";
            
            if (IsRightTriangle(side1, side2, side3))
                typeTriangle = "Right ";
          
	    //if side1,side2,side3 are all different values ,then return "scalene"
            if((side1 != side2) && (side2 != side3) && (side1 != side3)){ 
                typeTriangle += "Scalene";
		//if side1=side2=side3 then return "equilateal"	
            }else if(side1==side2 && side2 == side3){ 
                typeTriangle += "Equilateral";
	   //if only two of the three sides are the same,then display "isosceles"
            }else if(((side1==side2)&&(side2!=side3))||((side2==side3)&&(side3!=side1))||((side1==side3)&&(side2!=side1))){
                typeTriangle += "Isosceles"; 
	    } else {
	    	typeTriangle = "Rrror"; //should not be here since all triangle have 1, 2, or 3 equal sides
	    }
            
            return typeTriangle;
   	}
}
