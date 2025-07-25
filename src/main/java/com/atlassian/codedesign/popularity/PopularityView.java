package com.atlassian.codedesign.popularity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Example of contents are video, pages, posts etc. There can be two actions
 * associated with a content id:
 * 
 * increasePopularity → increases the popularity of the content by 1. The
 * popularity increases when someone comments on the content or likes the
 * content
 * 
 * decreasePopularity → decreases the popularity of the content by 1. The
 * popularity decreases when a spam bot's/users comments are deleted from the
 * content or its likes are removed from the content
 * 
 * content ids are positive integers
 * 
 * Implement a class that can return the mostPopular content id at any time
 * while consuming the stream of content ids and its associated action. If there
 * are no contentIds with popularity greater than 0, return -1
 * 
 */
// Space complxity o(n)
public class PopularityView {
	
	Map<Integer, Content> conetneMap= new HashMap<Integer, Content>();
	
	Content maxContent = null;
	// One way
	Map<Integer, Integer> conetneIndexMap= new HashMap<Integer, Integer>();
	List<Content> unprocesssElement = new ArrayList<Content>();
	
	
	List<Content> sortedContents= new LinkedList<Content>();
	
	public static void main(String[] args) {
		PopularityView popularityView = new PopularityView();
		popularityView.changePopularity(1001, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1001, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1001, true);
		System.out.println(popularityView.getMosPopularConent());
		
		
		popularityView.changePopularity(1001, false);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1002, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1002, true);
		System.out.println(popularityView.getMosPopularConent());
		
		
		popularityView.changePopularity(1003, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1003, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1003, true);
		System.out.println(popularityView.getMosPopularConent());
		popularityView.changePopularity(1003, true);
		System.out.println(popularityView.getMosPopularConent());
		
		
		
	}
	
	// o(1)
	//  O lon n
	public Content changePopularity(int contentId, boolean increase) {
		Content content=null;
		if(conetneMap.get(contentId) == null) {
			content=new Content(contentId, increase ? 1: 0 );
			
		}else {
			content = conetneMap.get(contentId);
			if(increase)
			content.setPopulality(content.getPopulality()+1);
			else {
			content.setPopulality(content.getPopulality()-1);
			}
		}
		conetneMap.put(contentId, content);
		unprocesssElement.add(content);
		
		// Async
		
		// find the new position where the content should moved
		// Binary search, find the position
		
		//sortedContents.remove(content);
		
		int postion= findPostion(sortedContents, content);
		sortedContents.add(postion,content);
		
		return content;
	}
	@Scheduled
	public void rearragnePopularity() {
		for(Content content : unprocesssElement) {
			// all the operation 
		}
	}
	
	// Sort and retrun topmost
	//  o(n2)
	// 
	public Content getMosPopularConent() {
		List<Content> contents= new ArrayList<Content>();
		for(int key: conetneMap.keySet()) {
			contents.add(conetneMap.get(key));
		}
		Collections.sort(contents);
		return contents.get(0);
		
	}
	/**
	 * Binary search to find the postion where the elemeent should be insert
	 * @param sortedContents
	 * @param content
	 * @return
	 */
	public int findPostion(List<Content> sortedContents, Content content) {
		return 0;
		
	}
	
	// Sort and retrun topmost
	//  o(n2)
	// 
	public Content getMosPopularConentV2() {
		return sortedContents.get(0);
		
	}

}
