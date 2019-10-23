package com.ji.burger.dao;

import org.springframework.stereotype.Component;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Component
public class FireStoreDao {

	public static Firestore db;

	public FireStoreDao() {
		Firestore db = FirestoreOptions.getDefaultInstance().getService();
		this.db = db;
	}
	
	public FireStoreDao(String projectId) {
	    // [START fs_initialize_project_id]
	    FirestoreOptions firestoreOptions =
	        FirestoreOptions.getDefaultInstance().toBuilder()
	            .setProjectId(projectId)
	            .build();
	    Firestore db = firestoreOptions.getService();
	    // [END fs_initialize_project_id]
	    this.db = db;
	  }
	
	Firestore getDb() {
		return db;
	}
	
	
}
