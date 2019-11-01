package com.ji.burger.controller;

import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.ji.burger.util.CrawlingBurgerKingMenu;
import com.ji.burger.util.CrawlingKfcMenu;
import com.ji.burger.util.CrawlingMacdonaldMenu;

public class FireStoreController {

	private Firestore db;

	public FireStoreController() {
		Firestore db = FirestoreOptions.getDefaultInstance().getService();
		this.db = db;
	}

	public FireStoreController(String projectId) {
		FirestoreOptions firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder().setProjectId(projectId)
				.build();
		Firestore db = firestoreOptions.getService();
		this.db = db;
	}

	Firestore getDb() {
		return db;
	}

	void addDocument(String docName) throws Exception {
		String pw = "###";

		switch (docName) {
		case "BurgerKing": {

			bergerKing = new CrawlingBurgerKingMenu("jshag90@naver.com", pw);

			List<Object> catagoryList = (List<Object>) bergerKing.excuteCrawl();
			int index = 1;
			for (Object prdData : catagoryList) {
				Map<String, Object> fieldMap = (Map<String, Object>) prdData;
				String docNameStr = fieldMap.get("CATEGORY").toString() + "_" + String.valueOf(index++);
				DocumentReference docRef = db.collection(docName).document(docNameStr);

				String priceStr = String.valueOf(fieldMap.get("PRICE")).replace(",", "");
				fieldMap.put("PRICE", Integer.valueOf(priceStr));

				ApiFuture<WriteResult> result = docRef.set(fieldMap);
			}

			break;

		}
		case "Macdonald": {

			macdonald = new CrawlingMacdonaldMenu("wltjsgkr90@hanmail.net", pw);

			List<Object> catagoryList = (List<Object>) macdonald.excuteCrawl();
			int index = 1;
			for (Object prdData : catagoryList) {
				Map<String, Object> fieldMap = (Map<String, Object>) prdData;
				String docNameStr = fieldMap.get("CATEGORY").toString() + "_" + String.valueOf(index++);
				DocumentReference docRef = db.collection(docName).document(docNameStr);

				String priceStr = String.valueOf(fieldMap.get("PRICE")).replace(",", "");
				fieldMap.put("PRICE", Integer.valueOf(priceStr));

				ApiFuture<WriteResult> result = docRef.set(fieldMap);

			}

			break;
		}
		case "Kfc": {

			kfc = new CrawlingKfcMenu();
			List<Object> catagoryList = (List<Object>) kfc.excuteCrawl();
			int index = 1;
			for (Object prdData : catagoryList) {
				Map<String, Object> fieldMap = (Map<String, Object>) prdData;
				String docNameStr = fieldMap.get("CATEGORY").toString() + "_" + String.valueOf(index++);
				DocumentReference docRef = db.collection(docName).document(docNameStr);

				String priceStr = String.valueOf(fieldMap.get("PRICE")).replace(",", "");
				fieldMap.put("PRICE", Integer.valueOf(priceStr));

				ApiFuture<WriteResult> result = docRef.set(fieldMap);
			}
			break;
		}
		default:
		}
	}

	void runAQuery(String docName) throws Exception {
		ApiFuture<QuerySnapshot> query = db.collection(docName).get();

		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			System.out.println(document.getData().toString());
		}
	}

	void retrieveAllDocuments() throws Exception {
		ApiFuture<QuerySnapshot> query = db.collection("brands").get();
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
			System.out.println("User: " + document.getId());
			System.out.println("First: " + document.getString("first"));
			if (document.contains("middle")) {
				System.out.println("Middle: " + document.getString("middle"));
			}
			System.out.println("Last: " + document.getString("last"));
			System.out.println("Born: " + document.getLong("born"));
		}
	}

	void run() throws Exception {
		String[] docNames = { "BurgerKing", "Macdonald", "Kfc" };

		// Adding document 1
		System.out.println("########## Adding document 1 ##########");
		addDocument(docNames[0]);

		// Adding document 2
		System.out.println("########## Adding document 2 ##########");
		addDocument(docNames[1]);

		// Adding document 3
		System.out.println("########## Adding document 3 ##########");
		addDocument(docNames[2]);

		// retrieve all users born before 1900
//		System.out.println("########## users born before 1900 ##########");
//		runAQuery();

		// retrieve all users
//		System.out.println("########## All users ##########");
//		retrieveAllDocuments();
//		System.out.println("###################################");
	}

	/**
	 * A quick start application to get started with Firestore.
	 *
	 * @param args firestore-project-id (optional)
	 */

	private static CrawlingBurgerKingMenu bergerKing;
	private static CrawlingMacdonaldMenu macdonald;
	private static CrawlingKfcMenu kfc;

	public static void main(String[] args) throws Exception {
		String projectId = "bergerbergerprj";
		FireStoreController quickStart = new FireStoreController(projectId);
		quickStart.run();
	}

}
