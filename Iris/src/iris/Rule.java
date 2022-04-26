/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iris;

/**
 *
 * @author aina
 */
public class Rule{       
    
    String input1;
    String cantum = "";

    public void setRule(String input1) {
        this.input1 = input1;
    }//public void setRule()

    public String getInput() {
        return input1;
    }//getInput

    public String getRule() {
        cantum = "if";
        String and = "";

        if (input1.charAt(0) == '0') {
            cantum = cantum + and + " age of patient at time of operation is low ";
            and = "and";
        } else if (input1.charAt(0) == '1') {
            cantum = cantum + and + " age of patient at time of operation is high ";
            and = "and";
        } else {
            cantum = cantum + "";
        //and = "";
        }


        if (input1.charAt(1) == '0') {
            cantum = cantum + and + " patient's year of operation is low ";
            and = "and";
        } else if (input1.charAt(1) == '1') {
            cantum = cantum + and + " patient's year of operation is high ";
            and = "and";
        } else {
            cantum = cantum + "";
        //and = "";
        }


        if (input1.charAt(2) == '0') {
            cantum = cantum + and + " number of positive axillary nodes detected is low ";
            and = "and";
        } else if (input1.charAt(2) == '1') {
            cantum = cantum + and + " number of positive axillary nodes detected is high ";
            and = "and";
        } else {
            cantum = cantum + "";
        //and = "";
        }


          if(input1.charAt(3)=='0')
                cantum = cantum + "then the patient survived 5 years or longer";
           else if (input1.charAt(3)=='1')   
               cantum = cantum + "then the patient died within 5 year";
            else if (input1.charAt(3)=='2')   
               cantum = cantum + "then the patient survived 5 years or longer";
            else if (input1.charAt(3)=='3')   
               cantum = cantum + "then the patient died within 5 year";
            else{
               cantum = cantum +" then result is eror.";
            }    

        return cantum;
    }//getRule()  
}    
