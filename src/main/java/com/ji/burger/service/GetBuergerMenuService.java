package com.ji.burger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.Query.Direction;
import com.ji.burger.dao.FireStoreDao;

@Service
public class GetBuergerMenuService {

	@Autowired
	FireStoreDao dao;

	private String prjId = "bergerbergerprj";

	public List<Object> getBergerMenus(String brand, String category) {
		List<Object> result = new ArrayList<Object>();
		dao = new FireStoreDao(prjId);

		if (brand.equalsIgnoreCase("kfc")) {
			brand = "Kfc";
		} else if (brand.equalsIgnoreCase("burgerking")) {
			brand = "BurgerKing";
		} else {
			brand = "Macdonald";
		}

		ApiFuture<QuerySnapshot> query;
		if (category.equalsIgnoreCase("all") )
		{
			query = dao.db.collection(brand).get();
		}else {
			query = dao.db.collection(brand).whereEqualTo("CATEGORY", category).orderBy("PRICE").get();
		}

		QuerySnapshot querySnapshot;
		try {
			querySnapshot = query.get();
			
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {

					result.add(document.getData());
				
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return result;

	}
}
