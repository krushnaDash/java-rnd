package com.krushna.Java_rnd.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

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
public class FileCalculator {
	
	public static void main(String[] args) {
		FileStorage fileStorage= new FileStorage();
		
		fileStorage.addFile(new FileObject(20, 1));
		fileStorage.addFile(new FileObject(2, 2));
		fileStorage.addFile(new FileObject(10, 1));
		fileStorage.addFile(new FileObject(3, 2));
		
		System.out.println(fileStorage.reportTotalSize());
		System.out.println(fileStorage.reportTopNCollection(1));
		
	}

}

class FileStorage{
	int totalSize=0;
	Map<Integer, List<FileObject>> fileStorage= new HashMap<Integer, List<FileObject>>();
	Map<Integer, Integer> collectionSizeMap=new HashMap<Integer, Integer>();


	/**
	 *
	 *@param fileObject
	 * during storage file lets calculate the total size and collection size
	 */
	public void addFile(FileObject fileObject) {
		totalSize+=fileObject.size;
		List<FileObject> fileList;
		int collectionId=fileObject.collectionId;
		fileList=fileStorage.get(collectionId);
		
		if(fileList ==null) {
			fileList= new ArrayList<>();
			fileList.add(fileObject);
			fileStorage.put(fileObject.collectionId,fileList);
			collectionSizeMap.put(fileObject.collectionId, fileObject.size);
		}else {
			fileList.add(fileObject);
			collectionSizeMap.put(fileObject.collectionId,collectionSizeMap.get(fileObject.collectionId)+fileObject.size);
		}
	}
	
	public int reportTotalSize() {
		return totalSize;
	}
	
	public List<FileObject> reportTopNCollection(int n) {
		List<FileObject> collectionAndSize= new ArrayList<FileObject>();
		
		for(int ids: collectionSizeMap.keySet()) {
			collectionAndSize.add( new FileObject(collectionSizeMap.get(ids), ids));
		}
		Collections.sort(collectionAndSize);
		
		return collectionAndSize.subList(0, n);
	}
	
	
}

class FileObject implements Comparable<FileObject>{
	int size;
	int collectionId;
	public FileObject(int size, int collectionId) {
		super();
		this.size = size;
		this.collectionId = collectionId;
	}
	@Override
	public int compareTo(FileObject fobject) {	
		return Integer.compare( fobject.size, this.size) ;
	}
	
	@Override
	public String toString() {
		return "FileObject [size=" + size + ", collectionId=" + collectionId + "]";
	}
	
	
}
