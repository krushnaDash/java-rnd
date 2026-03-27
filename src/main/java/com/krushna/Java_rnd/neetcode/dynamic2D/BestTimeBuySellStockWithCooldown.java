package com.krushna.Java_rnd.neetcode.dynamic2D;

public class BestTimeBuySellStockWithCooldown {
    public int maxProfit(int[] prices) {
        //return  maxProfixDFS(prices,0,true);
        Integer[][] dp= new Integer[prices.length][2];
        //return  maxProfixDFSWithDP(prices,0,1,dp);
        return  bottomUp(prices);
    }

    // can we do any bottom up solution with a matrix

    public int bottomUp(int[] prices){
        int [][] dp= new int[prices.length+2][2];
        for(int i=prices.length-1; i>=0; --i){
            dp[i][1]=Math.max(dp[i+1][1], // skip today
                    dp[i+1][0]-prices[i]); // buy today and pay price
            dp[i][0]= Math.max(dp[i+1][0], // skip today
                    dp[i+2][1] + prices[i]); // Sell today
        }
        return dp[0][1];// return buy choice
    }

    // 1 = canBuy
    //0 = no
    public int maxProfixDFSWithDP(int[] prices, int i, int canBuy, Integer[][] dp){
        int profit=0;
        if(i>=prices.length){
            return  0;
        }
        if(dp[i][canBuy] !=null){
            return  dp[i][canBuy];
        }
        if(canBuy==1){
            // two choice, buy or colldonw
            int buy=maxProfixDFSWithDP(prices, i+1, 0,dp)-prices[i];
            int skip=maxProfixDFSWithDP(prices, i+1, 1,dp);
            profit= Math.max(buy,skip);

        }else{
            int sell=maxProfixDFSWithDP(prices, i+2, 1,dp)+prices[i]; //skip one day can't buy immediately
            int hold=maxProfixDFSWithDP(prices, i+1, 0,dp);
            profit= Math.max(sell,hold);
        }
        dp[i][canBuy]=profit;
        return  profit;
    }



    // lets try to solve with DFS
    // lets have simple state canBuy= true, you can buy, false, you can sell, have stock
    // after sell skip one day, as cooldown and make canBuy as true again
    public int maxProfixDFS(int[] prices, int i, boolean canBuy){
        int profit=0;
        if(i>=prices.length){
          return  0;
        }
        if(canBuy){
            // two choice, buy or colldonw
            int buy=maxProfixDFS(prices, i+1, false)-prices[i];
            int skip=maxProfixDFS(prices, i+1, true);
            profit= Math.max(buy,skip);

        }else{
            int sell=maxProfixDFS(prices, i+2, true)+prices[i]; //skip one day can't buy immediately
            int hold=maxProfixDFS(prices, i+1, false);
            profit= Math.max(sell,hold);
        }
        return  profit;
    }

    public static void main(String[] args) {
        BestTimeBuySellStockWithCooldown btst= new BestTimeBuySellStockWithCooldown();
        int [] prices= {1,2,3,0,2};
        int total=btst.maxProfit(prices);
        System.out.println(total);
    }
}
