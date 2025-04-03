package com.krushna.Java_rnd.dsa;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

public class FileStorageSystem {
	
	int totalSize=0;
	Map<Integer,Integer> collectionSizeMap= new TreeMap<>();
	
	public void addFile(FileMetaData fileMetaData) {
		totalSize+=fileMetaData.size;
		int collectionId=fileMetaData.collectionId;
		if(collectionSizeMap.containsKey(collectionId)) {
			int size=collectionSizeMap.get(collectionId);
			size+=fileMetaData.size;
			collectionSizeMap.put(collectionId, size);
		}else {
			collectionSizeMap.put(collectionId, fileMetaData.size);
		}
	}
	
	public int getTotalSize() {
		return totalSize;
	}
	public List<Integer> getTopNCollection(int n){
		return collectionSizeMap.entrySet().stream()
				.sorted((e1,e2)->e2.getValue()-e1.getValue())
				.limit(n)
				.map(e->e.getKey())
				.toList();
	}

}

class FileMetaData{
	int size;
	int collectionId;
	
	public FileMetaData(int size, int collectionId) {
		this.size=size;
		this.collectionId=collectionId;
	}
}
