package com.zenzsol.filtlst.data.db;

import java.util.ArrayList;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zenzsol.filtlst.data.entity.Store;


public interface StoreDataHandler extends JpaRepository<Store, String> {

	  	Store findByName(String store);

		ArrayList<Store> findByCategory(String cat, Pageable page) throws Exception;

		ArrayList<Store> findByCategoryAndSubcategory(String cat, String subCat, Pageable page) throws Exception;

		ArrayList<Store> findBySubcategory(String subCat, Pageable page) throws Exception;
}
