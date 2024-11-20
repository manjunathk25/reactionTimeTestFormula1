package com.f1.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.f1.dao.GamesDAO;
import com.f1.dao.GamesDAOImp;
import com.f1.dto.Games;
import com.f1.dto.Player;

public class GameArea {
	static double best;
    public static void playGame(Player p, Games g) {
    	g.setPlayerId(p.getPid());
    	Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        GamesDAO gdao = new GamesDAOImp();
        List<Double> reactionTimes = g.getReactionTimes();
        if(reactionTimes == null) {
        reactionTimes = new ArrayList<Double>();
        }

        // Simulate the lights
        for (int i = 1; i <= 5; i++) {
           System.out.print("light ");
           try {
               Thread.sleep(1000); // Wait for 1 second
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       
   }
        // Simulate random waiting time
        try {
            Thread.sleep(rd.nextInt(200, 3001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long promptTime = System.nanoTime();
        //System.out.println(promptTime);
        
        System.out.println("\nEnter Enter Key");
        long startTime = System.nanoTime();
        //System.out.println(startTime);
        try {
			int input = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        if(startTime < promptTime) {
        	System.out.println("FOUl, You enterd too early");
        }
        
        else {
        long endTime = System.nanoTime();
        double reactionTime = (endTime - startTime) / 1000000;
        //Foul check
        if(reactionTime <= 0) {
        	System.out.println("FOUl, You enterd too early");
        }
        //Calculate reaction time
        else { 
        reactionTime /= 1000; 
        System.out.println(reactionTime+" s");
        reactionTimes.add(reactionTime);
        g.setReactionTimes(reactionTimes);
        
        //setting best reaction time
        if(g.getReactionTimes() != null) {
        	best = Collections.min(reactionTimes);
        	g.setBest(best);
        }
        
        //insert into games
        boolean res = gdao.insertGames(g);
        if(res) {
           //update best game
        	boolean res1 = gdao.updateBestGames(p, g);
        	if(res1) {
        		System.out.println("updated successfully");
        	}
        	else {
        		System.out.println("update failed");
        	}
            }

        }
      }
    }
}

