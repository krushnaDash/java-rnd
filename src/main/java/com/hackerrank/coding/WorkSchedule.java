package com.hackerrank.coding;

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
 * def findSchedules(workHours, dayHours, pattern):

    # hours already assigned
    assigned = sum(map(int,[x for x in pattern if not x == '?']))
    # additional hours needed
    needed = workHours - assigned
    
    result = []

    def backtrack(cr_pattern, index, need):
        if index == len(pattern):
            if need == 0:
                result.append(cr_pattern)
            return
        if pattern[index] == '?':
            for i in range(0, min(dayHours + 1, need + 1)):
                backtrack(cr_pattern + str(i), index + 1, need - i)
        else:
            backtrack(cr_pattern + pattern[index], index + 1, need)

    backtrack("", 0, needed)
    result.sort()

    return result
 * 
 * 
 */
public class WorkSchedule {
	
	
	 public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
		    // Write your code here
		    // This method should return a list of valid schedules based on the given work hours,
		 int assiginedHours = 0;
		 for(char c : pattern.toCharArray()) {
			 if(c!= '?') {
				 assiginedHours += Character.getNumericValue(c);
			 }
		 }
		 int needeHours = workHours - assiginedHours;
		 return null;
		 
     }
	 
	/**
	  *
	 * Here we can use the backtracking algorithm, with below condition to check valid or in valid 
	 * 1. open bracket== close bracket == n
	 * 2. close bracket < open bracket
	 * 3. Start with open brackert
	 * 
	 * for this we need to have recursive function
	 * ((()))
	 * ((())
	 */
	  
	 public static void  backTrack(String currentPattern, int needHours, int index) {
		 
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
