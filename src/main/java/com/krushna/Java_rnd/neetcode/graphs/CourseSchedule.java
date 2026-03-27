package com.krushna.Java_rnd.neetcode.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 
 
 There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return true if you can finish all courses. Otherwise, return false.

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

 */
public class CourseSchedule {

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		// lets create a map from the prerequisites, to represent the adjacency List
		Map<Integer, List<Integer>> courseMap= new HashMap<Integer, List<Integer>>();
		for(int [] course: prerequisites) {
			courseMap.computeIfAbsent(course[0],  (k) -> new ArrayList<>()).add(course[1]);
		}
		// new lets do DFS for each course and Have set to keep completed course for optimisation.
		int[] completedCourse= new int[numCourses];
		int [] visitied= new int[numCourses];
		
		for(int i=0; i< numCourses; ++i) {
			// DFS
			boolean check= dfs(i, courseMap, completedCourse, visitied);
			if(check) {
				completedCourse[i]=1;
			}else {
				return false;
			}
			
		}
		return true;
		
	}
	public boolean dfs(int key, Map<Integer, List<Integer>> courseMap, int[] completedCourse, int[] visited) {
		if(completedCourse[key]==1 || courseMap.get(key) == null) {
			return true;
		}
		if(visited[key]==1) {
			return false;
		}
		visited[key]=1;
		
		for(int i: courseMap.get(key)) {
			// if any DFS fail return false and break the loop 
			if(!dfs(i, courseMap, completedCourse, visited)) {
				return false;
			}
			// optimisation, we should mark them complete which are return true
			else{
				completedCourse[i]=1;
			}
		}
		// clean up
		visited[key]=0;
		return true;
	}
	
	public static void main(String[] args) {
		int[][] courses= new int[][] {{1,0},{0,3},{0,2},{3,2},{2,5},{4,5},{5,6},{2,4}};
		System.out.println(new CourseSchedule().canFinish(7, courses));
	}

}
