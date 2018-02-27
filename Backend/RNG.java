package TestClass;

import java.util.Random;

public class TestClass {
    
    
    public static String randomDate(){
        String date = "";
        int month = new Random().nextInt(12) + 1;
        int day = new Random().nextInt(31) + 1;
        int year = new Random().nextInt(50) + 1970;
        int yearShort = new Random().nextInt(50) + 70;
        if (yearShort >= 100)
        {
            yearShort -= 100;
        }
        int type = new Random().nextInt(6);
        switch (type){
            case 0: // 2/2/18
            {
                date = month+"/"+day+"/";
                if (yearShort < 10)
                {
                    date += "0";
                }
                date += yearShort;
                break;
            }
            case 1: //  02/02/18
            {
                if (month < 10)
                    date = "0";
                date += month+"/";
                if (day < 10)
                    date += "0";
                date += day+"/";
                if (yearShort < 10)
                {
                    date += "0";
                }
                date += yearShort;
                break;
            }
            case 2: 
            { // 2/2/2018
                date = month+"/"+day+"/"+year;
                break;
            }
            case 3: // 2-2-18
            {
                date = month+"-"+day+"-";
                if (yearShort < 10)
                {
                    date += "0";
                }
                date += yearShort;
                break;
            }
            case 4: // 02-02-18
            {
                if (month < 10)
                    date = "0";
               date += month+"-";
                if (day < 10)
                    date += "0";
               date += day+"-";
                if (yearShort < 10)
                {
                    date += "0";
                }
                date += yearShort;
                break;
            }
            case 5: // 2-2-2018
            {
                date = month+"-"+day+"-"+year;
                break;
            }
        }
        return date;
    }
    
    public static String randomNumber(){
        String number = "";
        int type = new Random().nextInt(4)+4;
        number += new Random().nextInt(9)+1;
        for (int i = 0; i < type-1;i++){
            number += new Random().nextInt(10);
        }
        return number;
    }
    
    public static String randomDigit(){
        String digit = "";
        int type = new Random().nextInt(2)+2;
        int dec = new Random().nextInt(3)+1;
        digit += new Random().nextInt(9)+1;
        for (int i = 0; i < type-1;i++){
            digit += new Random().nextInt(10);
        }
        digit += ".";
        for (int i = 0; i < dec;i++){
            digit += new Random().nextInt(10);
        }
        return digit;
    }
    
    public static void main(String[] args) {
        
        System.out.println(randomDate());
        System.out.println(randomNumber());
        System.out.println(randomDigit());
    }
    
}
