package com.krushna.Java_rnd.dsa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	int totalSize = 0;
	Map<Integer, Integer> collectionIndexMap = new HashMap<>();

	// We will use a sorted linked list to store the collection size and for each
	// addition file we will do a binary search
	// to find the right position to insert the new file size.
	// This will help us to keep the collection size in sorted order and we can
	// easily get the top N collections.

	List<FileMetaData> fileMetaDataList = new LinkedList<>();

	public void addFile(FileMetaData fileMetaData) {
		totalSize += fileMetaData.size;

		int collectionId = fileMetaData.collectionId;
		int postion = 0;

		if (collectionIndexMap.containsKey(collectionId)) {

			int index = collectionIndexMap.get(collectionId);
			int size = fileMetaDataList.get(index).size;
			size += fileMetaData.size;

			// create the new FileMetaData Object

			FileMetaData newFileMetaData = new FileMetaData(size, collectionId);

			// remove the old one
			fileMetaDataList.remove(index);

			postion = binarySearch(newFileMetaData, fileMetaDataList);

		} else {
			postion = binarySearch(fileMetaData, fileMetaDataList);
		}

		// Insert the element in right position
		fileMetaDataList.add(postion, fileMetaData);

		collectionIndexMap.put(collectionId, postion);

	}

	public int binarySearch(FileMetaData fileMetaData, List<FileMetaData> dataSet) {
		int low = 0;
		int high = dataSet.size() - 1;

		// For one element
		if (low == high) {
			return dataSet.get(0).size > fileMetaData.size ? 1 : low;
		}

		while (low <= high) {
			int mid = (low + high) / 2;
			if (dataSet.get(mid).size > fileMetaData.size) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}

		return low;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public List<Integer> getTopNCollectionOld(int n) {
		return collectionIndexMap.entrySet().stream().sorted((e1, e2) -> e2.getValue() - e1.getValue()).limit(n)
				.map(e -> e.getKey()).toList();
	}

	public List<Integer> getTopNCollection(int n) {
		return fileMetaDataList.subList(0, n).stream().map(e -> e.collectionId).toList();
	}

	public static void main(String[] args) {
		FileStorageSystem fileStorageSystem = new FileStorageSystem();

		fileStorageSystem.addFile(new FileMetaData(10, 1));
		fileStorageSystem.addFile(new FileMetaData(20, 2));
		fileStorageSystem.addFile(new FileMetaData(5, 1));
		fileStorageSystem.addFile(new FileMetaData(30, 3));

		System.out.println(fileStorageSystem.getTopNCollection(2));
	}

}

class FileMetaData {
	int size;
	int collectionId;

	public FileMetaData(int size, int collectionId) {
		this.size = size;
		this.collectionId = collectionId;
	}
}
