package org.com.springframework.json.service;

import java.util.*;
class Hcf_array
{
    public static int hcf(int a,int b)
    {
        int gcd=0;
        for(int i=a;i>0;i--)
        {
            if(a%i==0 && b%i==0)
            {
                gcd=i;
                break;
            }
        }
        return gcd;
    }
    public static void main(String args[])
    {
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i]=sc.nextInt();
        }
        int ans=arr[0];
        for(int i=1;i<n;i++)
        {
            ans=hcf(ans,arr[i]);
        }
        System.out.print(ans);
    }
}