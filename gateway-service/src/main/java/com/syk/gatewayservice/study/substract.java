package com.syk.gatewayservice.study;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author 沈永康
 * @Date 2022/9/30 18:37
 * @Version 1.0
 */


public class substract {
    public static void main(String[] args) {
        int a =11;
        int b =0;
        int c=1;
        Set<Integer> aa= new HashSet<>();
        while (a !=2) {
            // let b full from a 求mod
            while(a>7){
                System.out.println(a+" "+b);
                a = a -(7-b);
                b=7;
                System.out.println(a+" "+b);
                b=0;
            }
            if(c++>50){
                System.out.println(a+" "+b);
                break;
            }
            // move a to b
            System.out.println(a+" "+b);
            b=a;
            aa.add(b);
            a=0;
            System.out.println(a+" "+b);
            a=11;
        }
        System.out.println(aa);
    }


}
