/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iris;

/**
 *
 * @author aina Not iris but haberman(result of people die or survived after 5 years operation)
 */
import java.util.Random;
import java.util.Date;
import java.io.*;
import java.lang.Math;

import fuzzy.*; 
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Iris {

      private static final double switchProhability = 0.8; //parameter:switch probability between local to global pollination
      private static final Function function = Function.RASTRIGIN;
      
    public static void main(String[] args) throws Exception {
       
    int maxpopulation = 50;
        Rule rules = new Rule();
        String startingp=null;
        String endingd=null;
         String fuzzyrule=null;
        String inputstr = "";
        String input = "";
        String str="";
        String allrules = "";
        String conbit;
        int bit;
        String rulesTemp = "";
        Random random = new Random();
        Date time = new Date();
        String genomeA[] = new String[maxpopulation];
        String genomeB[] = new String[maxpopulation];
        String genomeD[] = new String[maxpopulation];
        String genomerulestr = "";
        String genomerule = "";
        String genomemsfstr = "";
        String genomed = "";
        String pstart = "";
        String dend = "";
          double fitness = 0;
        String name_file = "fold2.txt";
        int besar = 0;
        int bilrules = 0;
        int generation = 1;
        long bilRules = 1; //----> untuk set bil rules
        long genomebilRules = bilRules*4 ;
        long midGenomeBilRules = genomebilRules / 2;
        long GenomeRulesPart1 = midGenomeBilRules / 2;
        long GenomeRulesPart2 = (genomebilRules + midGenomeBilRules) / 2;
        int gmax = 100;
        int popsize=20; //parameter: initial population size(n) of flower
         
        //gmax = 1000 + (100 * bilRules);
        double highestFitt[] = new double[gmax];

        int samefitt1, samefitt2, samefitt3, samefitt4, samefitt5 = 0;
        int loopingGeneration = 0;

        int ct1 = 0;
        int ct2 = 0;
        int d1 = 0;

        int uc1 = 0;
        int uc2 = 0;
        int d2 = 0;

        int us1 = 0;
        int us2 = 0;
        int d3 = 0;

          int cn1 = 0;
        int cn2 = 0;
        int d10 = 0;
      

        //String displaytime = Integer.toString(time.getHours()) + "-" + Integer.toString(time.getMinutes()) + "-" + Integer.toString(time.getSeconds()) + "-" + Integer.toString(time.getDate()) + "-" + Integer.toString(time.getMonth() + 1) + "-" + Integer.toString(time.getYear() + 1900);
        String displaytime = Integer.toString(time.getYear() + 1900) + "-" + Integer.toString(time.getMonth() + 1) + "-" + Integer.toString(time.getDate()) + "-" + Integer.toString(time.getHours()) + "-" + Integer.toString(time.getMinutes()) + "-" + Integer.toString(time.getSeconds());
        System.out.println("Time : " + displaytime);

        //===== create the file==================//
        File f;
        String filename = displaytime + "_all_dataset_best_chromosome_no_rules_"+bilRules+ ".txt";
        f = new File(filename);
        f.createNewFile();
        //===== create the file==================//


        //================ to open and write to the file ==================//
        FileWriter fstream = new FileWriter(filename);
        BufferedWriter out = new BufferedWriter(fstream);
        out.write("Nama file data : " + name_file + "\n");
        //================ to open and write to the file ==================//

        for (int max_gen = 1; max_gen <= gmax; max_gen++) {
            System.out.println("******************************************************************************************************");
            System.out.println("Generation : " + generation);
            System.out.println("loopingGeneration : " + loopingGeneration);
            double temphighestFitt = 0;
            double highestacc = 0;
            String bestModelrule = null;
            String bestModelmsf = null;
            String bestModelrange = null;
            String logrange1 = null;
            String logrange2 = null;

            for (int w = 0; w < maxpopulation; w++) {
                
                if (generation == 1) {
                    //Create Lingustic variables and define membership functions
                    ct1 = random.nextInt(8) + 1;
                    ct2 = random.nextInt(8) + 1;
                    if (ct2 == ct1) {
                        ct2 = ct2 + 1;
                    }
                    d1 = Math.abs(ct2 - ct1);

                    uc1 = random.nextInt(8) + 1;
                    uc2 = random.nextInt(8) + 1;
                    if (uc2 == uc1) {
                        uc2 = uc2 + 1;
                    }
                    d2 = Math.abs(uc2 - uc1);

                    us1 = random.nextInt(8) + 1;
                    us2 = random.nextInt(8) + 1;
                    if (us2 == us1) {
                        us2 = us2 + 1;
                    }
                    d3 = Math.abs(us2 - us1);
                    
                  
                    logrange2 = Integer.toString(ct2) + Integer.toString(uc2) + Integer.toString(us2) ;
                    //System.out.println("logrange2:" +logrange2);

                   
                    //============================== for rules ==============================//
                   allrules = ""; //--> untuk reset balik rules..
                    besar = 0; //--> untuk reset balik bil variable bagi rules terpanjang..
                    
                    for (int x = 1; x <= bilRules; x++)
                    {
                        for (int y = 0; y < 4; y++) {
                            bit = random.nextInt(4);
                            conbit = Integer.toString(bit);
                            inputstr = inputstr + conbit;
                            if (bit == 0 || bit == 1 ) {
                                bilrules++;
                            }//if
                        }//for
                        if (bilrules > besar) {
                            besar = bilrules;
                               }//if
                        bilrules = 0;
                       // System.out.println("billlruless"+bilRules);
                        // nie untuk tentukan sama ada benign or malignant
                        bit = random.nextInt(2);
                        conbit = Integer.toString(bit);
                        inputstr = inputstr + conbit;
                        rules.setRule(inputstr);

                        //chromosome for rules..
                        genomerulestr = genomerulestr + inputstr;
                        //System.out.println(" inputstr" + inputstr);
                        //System.out.println("No. of generated rule = " + rules.getInput());
                        allrules = allrules + rules.getRule();
                        allrules = allrules + "\n";
                        inputstr = "";
                        rulesTemp = rulesTemp + rules.getInput();
                    }//for
                //out_first.write(genomerulestr + "\n");
                //============================== for rules ==============================//

                }//if generation == 1
                else {
                    ct1 = Integer.parseInt(genomeA[w].substring(0, 1));
                    d1 = Integer.parseInt(genomeD[w].substring(0, 1));
                    ct2 = ct1 + d1;
                    //System.out.println(ct1 + "--" + d1 + "--" + ct2);

                    uc1 = Integer.parseInt(genomeA[w].substring(1, 2));
                    d2 = Integer.parseInt(genomeD[w].substring(1, 2));
                    uc2 = uc1 + d2;
                    //System.out.println(uc1 + "--" + d2 + "--" + uc2);

                    us1 = Integer.parseInt(genomeA[w].substring(2, 3));
                    d3 = Integer.parseInt(genomeD[w].substring(2, 3));
                    us2 = us1 + d3;
                    //System.out.println(us1 + "--" + d3 + "--" + us2);
                    
                   
                    //System.out.println(cn1 + "--" + d10 + "--" + cn2);
                    logrange2 = Integer.toString(ct2) + Integer.toString(uc2) + Integer.toString(us2) ;

                   
                    //============================== for rules ==============================//
                    int beginIndex = 0;
                    int endIndex = 4;
                    allrules = ""; //--> untuk reset balik rules..
                    besar = 0; //--> untuk reset balik bil variable bagi rules terpanjang..
                    //*
                    bilrules = 0;
                    for (int b = 0; b < genomebilRules; b = b + 4) {
                        inputstr = genomeB[w].substring(beginIndex, endIndex);
                        //System.out.println("genomebilRules" +genomebilRules);
                        beginIndex = beginIndex + 4;
                        endIndex = endIndex + 4;
                        rules.setRule(inputstr);
                        //System.out.println(inputstr);
                        //*
                        for (int bilInputstr = 0; bilInputstr < (inputstr.length() - 1); bilInputstr++) {
                            if (inputstr.substring(bilInputstr, (bilInputstr + 1)).equals("1") || inputstr.substring(bilInputstr, (bilInputstr + 1)).equals("2")) {
                                bilrules++;
                            }//if
                        }//for
                        if (bilrules > besar) {
                            besar = bilrules;
                        }//if
                        bilrules = 0;
                        //*/
                        //chromosome for rules..
                        genomerulestr = genomerulestr + inputstr;
                        //System.out.println(".... genomerulestr... = "+genomerulestr);
                        allrules = allrules + rules.getRule();
                        allrules = allrules + "\n";
                        inputstr = "";
                        rulesTemp = rulesTemp + rules.getInput();
                    }//for
                //*/
                //============================== for rules ==============================//
                }//else generation == 1
                //*
                
                LinguisticVariable clumpthick = new LinguisticVariable("age");
                clumpthick.add("low", 0, 0, ct1, ct2);
                clumpthick.add("high", ct1, ct2, 10, 10);

                LinguisticVariable uniformcell = new LinguisticVariable("year");
                uniformcell.add("low", 0, 0, uc1, uc2);
                uniformcell.add("high", uc1, uc2, 10, 10);

                LinguisticVariable uniformshape = new LinguisticVariable("number");
                uniformshape.add("low", 0, 0, us1, us2);
                uniformshape.add("high", us1, us2, 10, 10);

                LinguisticVariable cancer = new LinguisticVariable("survival");
                cancer.add("survive", 0, 0, cn1, cn2);
                cancer.add("died", cn1, cn2, 10, 10);

                //encode for chromosome msf
                genomemsfstr = Integer.toString(ct1) + Integer.toString(uc1) + Integer.toString(us1) ;
                bestModelmsf = genomemsfstr;
                logrange1 = genomemsfstr + Integer.toString(cn1);

                //encode for chromosome d
                genomed = Integer.toString(d1) + Integer.toString(d2) + Integer.toString(d3) ;
                bestModelrange = genomed;
                //logrange2 = Integer.toString(ct2) + Integer.toString(uc2) + Integer.toString(us2) + Integer.toString(ma2) + Integer.toString(se2) + Integer.toString(bn2) + Integer.toString(bc2) + Integer.toString(nn2) + Integer.toString(mt2) + Integer.toString(cn2);

                //Create a fuzzy engine
                FuzzyEngine fuzzyEngine = new FuzzyEngine();

                String record = null;
                FileReader fr = new FileReader(name_file);
                BufferedReader br = new BufferedReader(fr);

                record = new String();
                int r = 0;
                double accB = 0;
                double accM = 0;
                int bilsurvive= 0;
                int bildied = 0;
                double accsurvive = 0;
                double accdied = 0;
                double middle = 5.0;

                middle = Math.abs((cn2 + cn1) / 2); // for determine the center point of survival msf
                
                //System.out.println(allrules + " hey \n");
                
                while ((record = br.readLine()) != null) {
                    //Create a block of rules
                    FuzzyBlockOfRules fuzzyBlockOfRules = new FuzzyBlockOfRules(allrules);
                    
                    // System.out.println(allrules);
                    //Register all LVs
                    
                    fuzzyEngine.register(clumpthick);
                    fuzzyEngine.register(uniformcell);
                    fuzzyEngine.register(uniformshape);
                    fuzzyEngine.register(cancer);

                    //Register the block
                    fuzzyEngine.register(fuzzyBlockOfRules);

                    fuzzyEngine.reset();

                    double result = 0.0;
                    r++;
                    String[] bits = record.replaceAll(",", "|$0|").split("\\|");
                     int x1, x2, x3, x4;

                    /* ================= take input file ========================*/
                    if (bits[0].equals("?")) {
                        x1 = 0;
                    } else {
                        x1 = Integer.parseInt(bits[0]);
                    }

                    if (bits[2].equals("?")) {
                        x2 = 0;
                    } else {
                        x2 = Integer.parseInt(bits[2]);
                    }

                    if (bits[4].equals("?")) {
                        x3 = 0;
                    } else {
                        x3 = Integer.parseInt(bits[4]);
                    }

                    if (bits[6].equals("?")) {
                        x4 = 0;
                    } else {
                        x4 = Integer.parseInt(bits[6]);
                    }

                 

                    //*
                    clumpthick.setInputValue(x1);
                    uniformcell.setInputValue(x2);
                    uniformshape.setInputValue(x3);
                     cancer.setInputValue(x4);
                   
                    //*/

                    //Parse the rules
                    //fuzzyBlockOfRules.parseBlock();

                    //Perform the evaluation
                    //fuzzyBlockOfRules.evaluateBlock();

                    try {
                        fuzzyBlockOfRules.parseBlock();
                        fuzzyBlockOfRules.evaluateBlock();
                        result = cancer.defuzzify();
                        //System.out.println("result = " + result);
                    } catch (RulesParsingException e) {
                        //System.err.println("Caught RulesParsingException: "
                        //+ e.getMessage());
                    } catch (NoRulesFiredException e) {
                        //System.err.println("Caught NoRulesFiredException: "
                        //+ e.getMessage());
                    }
                    //*/
                    //*
                   
                    if (x4 == 1) {
                        if (result <= middle) {
                            accB = accB + 1;
                        }
                        bilsurvive++;
                    }//if
                    else if (x4 == 2) {
                        if (result > middle) {
                            accM = accM + 1;
                        }
                        bildied++;
                    }//else
              
                }//while

                double acc;
                acc = (accB + accM) / r * 100;
                accsurvive = accB / bilsurvive * 100;
                accdied = accM / bildied * 100;
              
                fitness = Math.abs(acc - (besar * 0.0015));
                
                //termination condition
                if(acc==100){
                    max_gen = gmax;
                }//if
          
                bestModelrule = genomerulestr;

                genomemsfstr = genomemsfstr + 0 + fitness;
                genomerulestr = genomerulestr + 0 + fitness;
                genomed = genomed + 0 + fitness;

                /*
                System.out.println("Bilangan variable bagi rules terpanjang : " + besar);
                System.out.println("No. Kromosom : " + w);
                System.out.println("benign calssification rate : " + accbenign + "%");
                System.out.println("malingnant calssification rate : " + accmalignant + "%");
                System.out.println("Overall calssification rate : " + acc + "%");
                System.out.println("fitness : " + fitness + "\n");
                System.out.println("--------------------------------------------");
                accB = 0;
                accM = 0;
                //*/
            
                if (temphighestFitt <= fitness) {
                    temphighestFitt = fitness;
                    highestacc = acc;
                }//if temphighestFitt<=fitness

                /*
                if (highestacc <= acc) {
                //highestacc = acc;
                }//if temphighestFitt<=fitness
                //*/

                genomeA[w] = genomemsfstr;
                genomeB[w] = genomerulestr;
                genomeD[w] = genomed;
                genomerulestr = "";
            }//for w < maxpopulation

            //------------- write the fitness above 40% ------------------------------//
            if (highestacc > 40.00) {
                System.out.println("---------------------------\nGeneration : " + generation + "\nThe Model Rules : " + bestModelrule + "\nThe  Model Membership Function : " + pstart + "\nThe Model Range : " + dend + "\nThe Fitness : " + temphighestFitt + "\nAccuaracy : " + highestacc);
                System.out.println("Fuzzy rules : " + allrules);
                System.out.println("---------------------------");
            }//if highestacc>95
            //*/
            //------------- write the fitness above 95% ------------------------------//

            /*
            for (int g = 0; g < maxpopulation; g++) {
            System.out.println(genomeA[g]);
            }//for
            for (int g = 0; g < maxpopulation; g++) {
            System.out.println(genomeB[g]);
            }//for

            for (int g = 0; g < maxpopulation; g++) {
            System.out.println(genomeD[g]);
            }//for
            //*/
                //----- here start to generate next population -----//

              ///BEST MODEL RULE VALUE CHANGE TO DOUBLE////PASS TO FPA.JAVA
               
                String rulevalue = bestModelrule ;
            double rule = Double.parseDouble(rulevalue);
              
               //System.out.println("rule:" +rule);
                 
               ///BEST MODEL MEMBERSHIP FUNCTION START(P) CHANGE TO DOUBLE////PASS TO FPA.JAVA
              
                 String P = logrange1 ;
            double pvalue = Double.parseDouble(P);
                
                 // System.out.println("pvalue:" +pvalue); 
                  
              
               ///BEST MODEL MEMBERSHIP FUNCTION END(D) CHANGE TO DOUBLE////PASS TO FPA.JAVA
              
                 String D = logrange2 ;
            double dvalue = Double.parseDouble(D);
             
                 // System.out.println("dvalue :" +dvalue); 
                
               //----- here start to FPA CODE -----//
               
               
               FPA fpa = new FPA();
              
	       fpa.run(generation,popsize, switchProhability, function,rule, dvalue,pvalue,highestacc);    
             
               fpa.setrule(rule); 
               fpa.setstartmf(pvalue);
               fpa.setendmf(dvalue); 
               fpa.setaccuracy(highestacc );
               fpa.setpopsize(popsize);
               
            double fmin = fpa.getfmin();
            double pdouble =fpa.getmin();
            double d = fpa.getmax();
            double rulesfpadouble = fpa.getrulessent();
            
            double ruletry= fpa.getrule();
           // System.out.println("Rulesfpa in double" + rulesfpa);
            
           String value;
           value=Long.toBinaryString(Double.doubleToRawLongBits(d));
           String values = ("0000000000000000000000000000000000000000000000000000000000000000" + value).substring(value.length());
           System.out.println("Bit length: "+values.length());
           System.out.println("Best candidate solution in binary:" + values);
           
           String k=values.substring(0,10) + "," + values.substring(10, 37) + "," + values.substring(37, 64);
           //System.out.println("Split:" + k);
           String[] datavalues = k.split(",");
        
           for (int i = 0; i <datavalues.length; i++){
               
           System.out.println("Split:" + datavalues[i]);
           fuzzyrule= datavalues[0];
           startingp= datavalues[1];
           endingd= datavalues[2];
           }
          
           List<Integer> resultrule = Arrays.stream(fuzzyrule.split("(?<=\\G..)"))
                .map(s -> Integer.parseInt(s, 2)) // parse each string in the array
                .collect(Collectors.toList());    // collect the result in the end
           System.out.println("Splitrule(change to integer for every two binarystring):" + resultrule);
           str=(resultrule.toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",", "").replaceAll("\\s+",""));
           System.out.println("Rule of next gen:"+ str);
          // str = str.substring(0, 4);  
           
           /*
           List<Integer> resultstart = Arrays.stream(startingp.split("(?<=\\G..)"))
                .map(s -> Integer.parseInt(s, 2)) // parse each string in the array
                .collect(Collectors.toList());    // collect the result in the end
           System.out.println("Splitstart(change to integer for every two binarystring):" + resultstart);
            pstart=(resultstart.toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",", "").replaceAll("\\s+",""));
           System.out.println("Starting point:"+ pstart);
           */
            int foo=Integer.parseInt(startingp, 2);
            pstart=Integer.toString(foo);   
            System.out.println("Starting point(P):"+ pstart);
            pstart = pstart.substring(0, 4);
                
            /*
           List<Integer> resultend = Arrays.stream(endingd.split("(?<=\\G..)"))
                .map(s -> Integer.parseInt(s, 2)) // parse each string in the array
                .collect(Collectors.toList());    // collect the result in the end
           System.out.println("Splitend(change to integer for every two binarystring):" + resultend);
            dend=(resultend.toString().replaceAll("\\[","").replaceAll("\\]","").replaceAll(",", "").replaceAll("\\s+",""));
           System.out.println("Length of overlap:"+ dend);
            dend=dend.substring(0, 4);
             */     
            int calcend=Integer.parseInt(endingd, 2);
            dend=Integer.toString(calcend);   
             System.out.println("Length of overlap(D):"+ dend);
             dend = dend.substring(0, 4);    
             
          
            //----- here start to generate next population -----//
            for (int h = 0; h < (maxpopulation ); h++) {
                 
                String fminvalue = Double.toString(fmin); 
                
                //System.out.println("fminvalue:" +fminvalue);
               /*
                 String part_A_B1= fminvalue.substring(0,genomebilRules);
                  System.out.println("ab1111:" + part_A_B1);
                 String part_A_A1= fminvalue.substring(0,9);
                    System.out.println("aa1111:" + part_A_A1);
                 String part_A_D1= fminvalue.substring(0,9);
                    System.out.println("a1111:" + part_A_D1);
               */
           
               /* test value
                double a= 5.818161786E9;
                String cthstart=Double.toString(a);genomemsfstr
               double b= 8.125275427E9;
                String cthd=Double.toString(b);genomed
                 */    
    
                for (int g = 0; g < maxpopulation; g++) {
                    
                       ct1 = Integer.parseInt(genomeA[g].substring(0, 1));
                    d1 = Integer.parseInt(genomeD[g].substring(0, 1));
                    ct2 = ct1 + d1;
                    //System.out.println(ct1 + "--" + d1 + "--" + ct2);

                    uc1 = Integer.parseInt(genomeA[g].substring(1, 2));
                    d2 = Integer.parseInt(genomeD[g].substring(1, 2));
                    uc2 = uc1 + d2;
                    //System.out.println(uc1 + "--" + d2 + "--" + uc2);

                    us1 = Integer.parseInt(genomeA[g].substring(2, 3));
                    d3 = Integer.parseInt(genomeD[g].substring(2, 3));
                    us2 = us1 + d3;
                    //System.out.println(us1 + "--" + d3 + "--" + us2);

                    cn1 = Integer.parseInt(genomeA[g].substring(3, 4));
                    d10 = Integer.parseInt(genomeD[g].substring(3, 4));
                    cn2 = cn1 + d10;
                    //System.out.println(cn1 + "--" + d10 + "--" + cn2);
                     
                
                     //rules.setRule(str); 
                  //System.out.println("Pstart : " + pstart);
                  //  System.out.println("Dend : " + dend);
                    genomeA[g]=pstart; // from line 636
                    genomeD[g]=dend;// from line 639
                    genomeB[g]= str; //
    
                    
                             }

            }//----- here end to generate next population -----//

            System.out.println("Generation number : " + generation+ "\t\t Highest fitness.." + temphighestFitt + "\t Highest Classification : " + highestacc + "\n");
            highestFitt[(gmax - 1)] = temphighestFitt;
            out.write("Generation number : " + generation + "\t\t Highest fitness.." + temphighestFitt + "\t Highest Classification : " + highestacc + "\n");

            generation++;
            
             //System.out.println("Generation number : " + generation + "\t\t Highest fitness.." + temphighestFitt + "\t Highest Classification : " + highestacc + "\n");
        }//for max_gen or while max_gen

       //==================== close the file =======================//
        out.close();
       //==================== close the file =======================//
    }//main
    }//class


   
        


