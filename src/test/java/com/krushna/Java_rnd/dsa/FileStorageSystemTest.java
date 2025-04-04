package com.krushna.Java_rnd.dsa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FileStorageSystemTest {

	@Test
	void testBinarySearch() {
		FileStorageSystem fileStorageSystem = new FileStorageSystem();
		List<FileMetaData> fileMetaDatas= new ArrayList<FileMetaData>();
		
		fileMetaDatas.add(new FileMetaData(10, 1));

		assertEquals(0,fileStorageSystem.binarySearch(new FileMetaData(20, 1),fileMetaDatas));
		assertEquals(1,fileStorageSystem.binarySearch(new FileMetaData(5, 1),fileMetaDatas));
		
		fileMetaDatas.add(new FileMetaData(5, 2));
		
		assertEquals(0,fileStorageSystem.binarySearch(new FileMetaData(20, 1),fileMetaDatas));
		assertEquals(1,fileStorageSystem.binarySearch(new FileMetaData(9, 1),fileMetaDatas));
		assertEquals(2,fileStorageSystem.binarySearch(new FileMetaData(4, 1),fileMetaDatas));

	}

	@Test
	void testAddFile() {
		FileStorageSystem fileStorageSystem = new FileStorageSystem();
		fileStorageSystem.addFile(new FileMetaData(100, 1));
		fileStorageSystem.addFile(new FileMetaData(200, 2));
		fileStorageSystem.addFile(new FileMetaData(300, 1));
		fileStorageSystem.addFile(new FileMetaData(600, 3));

		assertEquals (1200,fileStorageSystem.getTotalSize());
		assertEquals (3,fileStorageSystem.getTopNCollection(1).get(0));
		assertEquals (1,fileStorageSystem.getTopNCollection(2).get(1));
		
	}


}
