package com.ji.burger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.common.collect.ImmutableMap;
import com.ji.burger.crawler.CrawlingBurgerKingMenu;
import com.ji.burger.crawler.CrawlingKfcMenu;
import com.ji.burger.crawler.CrawlingMacdonaldMenu;

public class FireStoreController {

	private Firestore db;

	/**
	   * Initialize Firestore using default project ID.
	   */
	  public FireStoreController() {
	    // [START fs_initialize]
	    Firestore db = FirestoreOptions.getDefaultInstance().getService();
	    // [END fs_initialize]
	    this.db = db;
	  }

	public FireStoreController(String projectId) {
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

	/**
	 * Add named test documents with fields first, last, middle (optional), born.
	 *
	 * @param docName document name
	 */
	void addDocument(String docName) throws Exception {
		String pw = "####";
		
		switch (docName) {
		case "BurgerKing": {
			// [START fs_add_data_1]
			
			bergerKing = new CrawlingBurgerKingMenu("jshag90@naver.com", pw);
			ApiFuture<WriteResult> result;
//		    CollectionReference brands = db.collection("brands");
//		    for (Object data : bergerKing.excuteCrawl()) {
//		    	List<Object> catagoryList=(List<Object>) data;
//		    	for(Object info:catagoryList ) {
//		    futures.add(brands.document("BurgerKing").set(new City("San Francisco", "CA", "USA", false, 860000L,
//		            Arrays.asList("west_coast", "norcal"))));
//		    }
		    
			DocumentReference docRef = db.collection("brands").document("BurgerKing");
			
			for (Object data : bergerKing.excuteCrawl()) {
				List<Object> catagoryList=(List<Object>) data;
				for(int i=0; i<catagoryList.size(); i++ ) {
					Map<String,Object> crawlData = (Map<String, Object>) catagoryList.get(i);
					System.out.println("TEST"+crawlData.get("CATEGORY"));
					String index = String.valueOf(i)+String.valueOf(crawlData.get("CATEGORY"));
					crawlData.put("INDEX", index);
					result =  docRef.set(crawlData);
//					System.out.println("Update time : " + result.get().getUpdateTime());
				}
			}
		    
		    
			// Add document data with id "alovelace" using a hashmap
			Map<String, Object> data = new HashMap<>();
			data.put("first", "Ada");
			data.put("last", "Lovelace");
			data.put("born", 1815);
			// asynchronously write data
//			ApiFuture<WriteResult> result = docRef.set(data);
			// ...
			// result.get() blocks on response
			// [END fs_add_data_1]
			break;
		}
		case "Macdonald": {
			// [START fs_add_data_2]
			DocumentReference docRef = db.collection("brands").document("Macdonald");
			// Add document data with an additional field ("middle")
			Map<String, Object> data = new HashMap<>();
			data.put("first", "Alan");
			data.put("middle", "Mathison");
			data.put("last", "Turing");
			data.put("born", 1912);

			ApiFuture<WriteResult> result = docRef.set(data);
			System.out.println("Update time : " + result.get().getUpdateTime());
			// [END fs_add_data_2]
			break;
		}
		case "Kfc": {
			DocumentReference docRef = db.collection("brands").document("Kfc");
			Map<String, Object> data = new ImmutableMap.Builder<String, Object>().put("first", "Charles")
					.put("last", "Babbage").put("born", 1791).build();
			ApiFuture<WriteResult> result = docRef.set(data);
			System.out.println("Update time : " + result.get().getUpdateTime());
			break;
		}
		default:
		}
	}

	void runAQuery() throws Exception {
		// [START fs_add_query]
		// asynchronously query for all users born before 1900
		ApiFuture<QuerySnapshot> query = db.collection("users").whereLessThan("born", 1900).get();
		// ...
		// query.get() blocks on response
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
		// [END fs_add_query]
	}

	void retrieveAllDocuments() throws Exception {
		// [START fs_get_all]
		// asynchronously retrieve all users
		ApiFuture<QuerySnapshot> query = db.collection("brands").get();
		// ...
		// query.get() blocks on response
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
		// [END fs_get_all]
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
