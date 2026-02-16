package com.hackerrank.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * 
 * An employee wants to find all possible work schedules for their week. They
 * must work exactly their total weekly hours, and no day can exceed the maximum
 * daily hours, though days off (0 hours) are allowed. Some days may already be
 * fixed by their employer.
 * 
 * 
 * 
 * Let’s suppose the employee’s currently scheduled workweek is represented as a
 * 7-character string, for example: “08??840”. Each character represents one day
 * of the week. The days already scheduled by their employer are the numerical
 * digits, and the flexible days are represented as question marks.
 * 
 * 
 * 
 * Implement a function that takes a workweek string in the format above, and
 * returns an array of all possible valid workweeks that satisfy the weekly and
 * daily hour limits.
 * 
 * 
 * 
 * Example Suppose the given workweek (pattern) = "08??840", total weekly
 * working hours (work_hours) = 24, and maximum daily working hours (day_hours)
 * = 4
 * 
 * The known hours (0+8+8+4+0) sum to 20. The two unknown days must sum to 4
 * without exceeding the daily limit of 4 hours Therefore, the function should
 * return this list, which contains every possible valid schedule for the week:
 * 
 * [ ”0804840”, ”0813840”, ”0822840”, ”0831840”, ”0840840” ]
 * 
 * 
 * 
 */
public class WorkSchedule {
	
	
	 public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
		// This method should return a list of valid schedules based on the given work hours,
		 int assiginedHours = 0;
		 List<String> findSchedules= new ArrayList<String>();
		 int countDays=0;
		 for(char c : pattern.toCharArray()) {
			 if(c!= '?') {
				 assiginedHours += Character.getNumericValue(c);
			 }else {
				 countDays++;
			 }
		 }
		 int needeHours = workHours - assiginedHours;
		 List<String> schdules= new ArrayList<String>();
		 int[] currentSchdule= new int[countDays];
		 backTrackDFS(needeHours, dayHours, schdules, currentSchdule,0);
		 // we got the possible pattern for each ? mark, let create all schedule, by replacing them
		 
		 for(String schdule: schdules) {
			 char[] chars=pattern.toCharArray();
			 for(int i=0,j=0; i < chars.length; ++i) {
				 if(chars[i] == '?') {
					 chars[i]= schdule.charAt(j);
					 ++j;
				 }
			 }
			 findSchedules.add(new String(chars)); 
		 }
		return findSchedules;
		 
     }
	 	// we can pass the whole pattern string and do DFS for the position which are marked as ?
		public static void backTrackDFS(int needHours, int dayHours, List<String> allSchdule, int[] currentSchdule, int index) {
			// base case
			int sum = Arrays.stream(currentSchdule).sum();
			if(sum == needHours && index <= currentSchdule.length) {
				StringBuilder value= new StringBuilder();
				for(int h:currentSchdule) {
					value.append(h);
				}
				allSchdule.add(new String(value));
				return;
			}
			if(sum > needHours || index > currentSchdule.length) {
				return;
			}
			if(sum< needHours && index < currentSchdule.length) {
				// try all the possible number for 0th position and DFS for the next position for all number
				for(int i=0; i <= dayHours; ++i) {
					currentSchdule[index]=i;
					backTrackDFS(needHours, dayHours, allSchdule, currentSchdule, index+1);
					// do the clean up
					currentSchdule[index]=0;
				}
			}
		}
	 

	 
	 
	 
	 public static void main(String[] args) {
		 // Example usage
		 int workHours = 24;
		 int dayHours = 4;
		 String pattern = "08??840"; // Example pattern
		 
		 List<String> schedules = findSchedules(workHours, dayHours, pattern);
		 // Print the schedules
		 for (String schedule : schedules) {
			 System.out.println(schedule);
		 }
	 }
	

}
