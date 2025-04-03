package com.krushna.Java_rnd.dsa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

 class FileStorageSystemTest {
	
	@Test
	 void testAddFile() {
		FileStorageSystem fileStorageSystem = new FileStorageSystem();
		fileStorageSystem.addFile(new FileMetaData(100, 1));
		fileStorageSystem.addFile(new FileMetaData(200, 2));
		fileStorageSystem.addFile(new FileMetaData(300, 1));
		
		assert fileStorageSystem.getTotalSize() == 600;
		assert fileStorageSystem.getTopNCollection(1).get(0) == 1;
		assert fileStorageSystem.getTopNCollection(2).get(1) == 2;
	}
     @Test
	 void testGetTotalSize() {
		FileStorageSystem fileStorageSystem = new FileStorageSystem();
		fileStorageSystem.addFile(new FileMetaData(100, 1));
		fileStorageSystem.addFile(new FileMetaData(200, 2));
		
		assert fileStorageSystem.getTotalSize() == 300;
		assertThat(fileStorageSystem.getTopNCollection(1)).containsExactly(2);
	}	
	
	

}
