package com.krushna.Java_rnd.neetcode.math_geometry;

public class MultiplyStrings {

    // using simple approach, how we do by hand
    public String multiply(String num1, String num2) {
        if(num2.equals("0") || num1.equals("0")){
            return "0";
        }
        if (num1.length() < num2.length()) {
            return multiply(num2, num1);
        }

        String total="";
        for(int i=num2.length()-1; i>=0; --i){
            int num=num2.charAt(i)-'0';
            String result=multi(num1,num);
            result= result+getZero((num2.length()-1)-i);
            total=sum(total,result);
        }
        return total;
    }
    public String getZero(int count){
        StringBuilder res= new StringBuilder();
        for(int i=0; i< count; ++i){
            res.append(0);
        }
        return res.toString();
    }

    public String multi(String s, int num){
        StringBuilder result= new StringBuilder();
        int carry=0;
        for(int i=s.length()-1; i>=0; --i){
            int mul=((s.charAt(i)-'0')*num)+carry;
            if(mul>=10){
                carry= mul/10;
                mul=mul%10;
            }else {
                carry=0;
            }
            result.append(mul);
        }
        if(carry!=0){
            result.append(carry);
        }
        return result.reverse().toString();
    }


    // this will sum two string and return the result
    public String sum(String s1, String s2){
        StringBuilder result= new StringBuilder();
        int carry=0;
        int i=s1.length()-1;
        int j=s2.length()-1;
        while( i>=0 || j >=0 ){
            int sum=0;
            if(i>=0 && j>= 0){
                sum= s1.charAt(i)-'0'+s2.charAt(j)-'0'+carry; // char to int, we need to minus '0'
                --i; --j;
            }else if (i >=0){
                sum=carry+Integer.valueOf(s1.charAt(i)+""); // or make it string
                --i;
            }else{
                sum=carry+Integer.valueOf(s2.charAt(j)+"");
                --j;
            }
            if(sum >=10){
                carry= sum/10;
                sum=sum%10;
            }else {
                carry=0;
            }
            result.append(sum);
        }
        if(carry !=0){
            result.append(carry);
        }
        return result.reverse().toString();

    }

    public static void main(String[] args) {
        MultiplyStrings ms= new MultiplyStrings();
        System.out.println(ms.sum("738", "6150"));
        System.out.println(ms.multi("123", 6));

        System.out.println(ms.multiply("123", "456"));

    }
}
