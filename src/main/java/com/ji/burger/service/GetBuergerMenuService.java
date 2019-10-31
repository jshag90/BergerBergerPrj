package com.ji.burger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Query.Direction;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.ji.burger.dao.FireStoreDao;

@Service
public class GetBuergerMenuService {

	@Autowired
	FireStoreDao dao;

	private String prjId = "bergerbergerprj";
	
	public List<Object> getBurgerCategorys(String brand){
		List<Object> result = new ArrayList<Object>();
		dao = new FireStoreDao(prjId);
		
		if (brand.equalsIgnoreCase("kfc")) {
			brand = "Kfc";
		} else if (brand.equalsIgnoreCase("burgerking")) {
			brand = "BurgerKing";
		} else if (brand.equalsIgnoreCase("macdonald")) {
			brand = "Macdonald";
		} else {
			brand = "Macdonald";
		}
		
		ApiFuture<QuerySnapshot> query = dao.db.collection(brand).get();
		
		QuerySnapshot querySnapshot;
		try {
			querySnapshot = query.get();
			List<Object> tempResult = new ArrayList<Object>();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents) {
				System.out.println(document.getData());
				tempResult.add(document.getData().get("CATEGORY"));
			}
			result = tempResult.stream().distinct().collect(Collectors.toList());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public List<Object> getBurgerMenus(String brand, String category, String priceOrder) {
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
		Direction direction = priceOrder.equals("ASC") ? Direction.ASCENDING:Direction.DESCENDING;
		
		if (category.equalsIgnoreCase("all") )
		{
			query = dao.db.collection(brand).orderBy("PRICE", direction).get();
		} else {
			query = dao.db.collection(brand).whereEqualTo("CATEGORY", category).orderBy("PRICE", direction).get();
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
