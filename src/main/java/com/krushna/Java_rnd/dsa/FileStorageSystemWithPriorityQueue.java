package com.krushna.Java_rnd.dsa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Imagine we have a system that stores files, and these files can be grouped
 * into collections. We are interested in knowing where our resources are being
 * taken up.
 * 
 * For this system we would like to generate a report that lists:
 * 
 * - The total size of all files stored; and
 * 
 * - The top N collections (by file size) where N can be a user-defined value
 * 
 */

public class FileStorageSystemWithPriorityQueue {
   int totalSize = 0;
   Map<String, FileInfo> collectionMap= new HashMap<String, FileInfo>();
   PriorityQueue<FileInfo> collectionQueue = new PriorityQueue<FileInfo>(
			Comparator.comparing(fileInfo -> fileInfo.size, Comparator.reverseOrder()));
	
	public void addFile(String collectionName, int size ) {
		totalSize+=size;
		if(collectionMap.get(collectionName) ==null) {
			FileInfo fileInfo= new FileInfo(size, collectionName);
			collectionMap.put(collectionName, fileInfo);
			collectionQueue.add(fileInfo);
		}else {
			FileInfo fileInfo=collectionMap.get(collectionName);
			collectionQueue.remove(fileInfo);
			FileInfo newFilInfo= new FileInfo(fileInfo.size+size, collectionName);
			collectionQueue.add( newFilInfo);
			collectionMap.put(collectionName, newFilInfo);
		}
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	
	public List<FileInfo> getTopNCollection(int n) {
		return collectionQueue.stream().limit(n).toList();
	}
	
	public static void main(String[] args) {
		FileStorageSystemWithPriorityQueue storageSystem= new FileStorageSystemWithPriorityQueue();
		storageSystem.addFile("songs", 10);
		storageSystem.addFile("movie", 5);
		storageSystem.addFile("A", 20);
		storageSystem.addFile("A", 20);
		storageSystem.addFile("songs", 15);
		storageSystem.addFile("songs", 20);
		storageSystem.addFile("Film", 10);
		storageSystem.addFile("C", 30);
		storageSystem.addFile("D", 10);
		storageSystem.addFile("A", 20);
		
		System.out.println(storageSystem.getTotalSize());
		System.out.println(storageSystem.getTopNCollection(2));
		
	}
   
	
}

class FileInfo {
	int size;
	String collectionName;
	public FileInfo(int size, String collectionName) {
		super();
		this.size = size;
		this.collectionName = collectionName;
	}
	@Override
	public String toString() {
		return "FileInfo [size=" + size + ", collectionName=" + collectionName + "]";
	}
	

}
