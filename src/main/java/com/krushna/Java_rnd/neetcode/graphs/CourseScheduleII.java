package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 https://leetcode.com/problems/course-schedule-ii/description/
 
 * 
 * here are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 
 *
 */

public class CourseScheduleII {

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// create adjacency map
		Map<Integer, List<Integer>> courseMap= new HashMap<Integer, List<Integer>>();
		
		for(int [] course : prerequisites) {
			courseMap.computeIfAbsent(course[0],  (key) -> new ArrayList<>()).add(course[1]);
		}
		int [] courseCompleted= new int[numCourses];
		int [] visited= new int[numCourses];
		List<Integer> courseOrder= new ArrayList<Integer>();
		
		// 1 ->0
		for(int i=0; i < numCourses; ++i) {
			if( !checkCourse(i, courseMap, courseCompleted, visited, courseOrder)) {
				return new int[0];
			}
			
		}
		
		return courseOrder.stream().mapToInt(i -> i).toArray();
	}
	
	public boolean checkCourse(int course, Map<Integer, List<Integer>> courseMap, int[] courseCompleted, int[] visited,
			List<Integer> courseOrder) {
		if(courseCompleted[course] ==1 ) {
			return true;
		}
		if(visited[course]==1) {
			return false;
		}
		visited[course]=1;
		// check if there is any pre-course to take for this
		if(courseMap.get(course) != null) {
			for(int c: courseMap.get(course)) {
				boolean check= checkCourse(c, courseMap, courseCompleted, visited, courseOrder);
				if(!check) {
					return false;
				}
			}
		}
		// clean up
		visited[course]=0;
		// The idea to get correct order is let function return true only when it is completed earlier
		courseCompleted[course]=1;
		courseOrder.add(course);
		return true;

	}
	
	
	public static void main(String[] args) {
		int [][] pre= {{0,1},{1,2},{2,3},{3,4}};
		CourseScheduleII courseScheduleII= new CourseScheduleII();
		System.out.println(Arrays.toString(courseScheduleII.findOrder(5, pre)));
		
		int [][] pre2= {{0,1}};
		System.out.println(Arrays.toString(courseScheduleII.findOrder(2, pre2)));
	}

}
