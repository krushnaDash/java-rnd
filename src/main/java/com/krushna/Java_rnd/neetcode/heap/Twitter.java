package com.krushna.Java_rnd.neetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Implement a simplified version of Twitter which allows users to post tweets,
 * follow/unfollow each other, and view the 10 most recent tweets within their
 * own news feed.
 * 
 * Users and tweets are uniquely identified by their IDs (integers).
 * 
 * Implement the following methods:
 * 
 * Twitter() Initializes the twitter object. void postTweet(int userId, int
 * tweetId) Publish a new tweet with ID tweetId by the user userId. You may
 * assume that each tweetId is unique. List<Integer> getNewsFeed(int userId)
 * Fetches at most the 10 most recent tweet IDs in the user's news feed. Each
 * item must be posted by users who the user is following or by the user
 * themself. Tweets IDs should be ordered from most recent to least recent. void
 * follow(int followerId, int followeeId) The user with ID followerId follows
 * the user with ID followeeId. void unfollow(int followerId, int followeeId)
 * The user with ID followerId unfollows the user with ID followeeId.
 * 
 */
class Twitter {
	// Tweet Map with id and time as counter user_id, [tweet_id, 1]

	Map<Integer, List<int[]>> tweetMap;
	Map<Integer, Set<Integer>> followMap;
	int timecounter;

	public Twitter() {
		tweetMap = new HashMap<>();
		followMap = new HashMap<>();
		timecounter = 0;
	}

	// Publish a new tweet with ID tweetId by the user userId.
	public void postTweet(int userId, int tweetId) {
		tweetMap.computeIfAbsent(userId, k -> new ArrayList<int[]>()).add(new int[] { tweetId, timecounter++ });

	}

	// Fetches at most the 10 most recent tweet IDs in the user's news feed.
	// Each item must be posted by users who the user is following or by the user
	// themself.
	// Tweets IDs should be ordered from most recent to least recent
	public List<Integer> getNewsFeed(int userId) {
		// create an runtime min heap here of size 10, we will add all the element from
		// self and following
		PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
		// take 10 from each and add to the heap and return the heap.

		Set<Integer> userIds = followMap.getOrDefault(userId, new HashSet<>());
		// add self
		userIds.add(userId);
		for (int id : userIds) {
			List<int[]> tweets = tweetMap.get(id);
			if (tweets != null) {
				if (tweets.size() > 10) {
					tweets = tweets.subList(tweets.size() - 10, tweets.size());
				}

				for (int[] tweet : tweets) {
					// add to minHeap by checking
					minHeap.add(tweet);
                    if (minHeap.size() > 10) {
						minHeap.poll();
					}
				}
			}
		}
		List<Integer> feeds = new ArrayList<Integer>();
		while (!minHeap.isEmpty()) {
			feeds.add(minHeap.poll()[0]);
		}
		return feeds.reversed();
	}

	// The user with ID followerId follows the user with ID followeeId.
	public void follow(int followerId, int followeeId) {
		followMap.computeIfAbsent(followerId, k -> new HashSet<Integer>()).add(followeeId);
	}

	// The user with ID followerId unfollows the user with ID followeeId.
	public void unfollow(int followerId, int followeeId) {
		if(followMap.get(followerId) !=null)
            followMap.get(followerId).remove(Integer.valueOf(followeeId));
	}
}