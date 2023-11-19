package com.zenzsol.filtlst.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zenzsol.filtlst.data.db.StoreDataHandler;
import com.zenzsol.filtlst.data.entity.Store;

@Controller
public class StoreListController {
	private static final Logger log = Logger.getLogger(StoreListController.class.getName());
	private static final Integer pageSize = 15;

	@Autowired
	private StoreDataHandler dataHandler;

	//get stores by category
	@GetMapping("/stores/{category}")
	public String displayStoresByCat(ModelMap model, @PathVariable String category) {

		Integer pageNo = 0;		
		Pageable paginate = PageRequest.of(pageNo, pageSize);
		
		category = category.substring(0, 1).toUpperCase() + category.substring(1);

		processStoreList(model, category, paginate);

		return "/zv/content/storeslist";
	}

	private void processStoreList(ModelMap model, String category, Pageable paginate) {
		ArrayList<Store> storesLst = new ArrayList<Store>();
		try {
			storesLst = dataHandler.findByCategory(category, paginate);
			log.info("category " + category);
			log.info("total stores in the category " + storesLst.size());			

		} catch (Exception e) {
			storesLst = new ArrayList<Store>();
			log.info("processStoreList: " + e);
		}
		
		if(storesLst.size() != 0) {			
			model.put("category", storesLst.get(0).getCategory());
		}else {
			model.put("category", "");
		}
		model.put("subcategory", "");
		model.put("storeslst", storesLst);

		if (storesLst.size() < pageSize) {
			model.put("laststore", "end");
		} else {
			model.put("laststore", paginate.getPageNumber() + 1);
		}
	}
	//get next page of stores by category with or without sub category - ajax
	@PostMapping("/nextstorespage")
	public String displayStoresByCat(ModelMap model, @RequestParam("catgry") String category, 
								@RequestParam(value = "curval", required = false, defaultValue = "0") Integer lastStore,
									@RequestParam("subctgry") String subctgry) {

		Pageable paginate = PageRequest.of(lastStore, pageSize);
		if (subctgry.isEmpty()) {
			log.info("stores by category next page " + lastStore);
			
			processStoreList(model, category, paginate);
		} else {
			log.info("store by category and subcat next page " + lastStore);
			processSubCatStoreList(model, category, subctgry, paginate);
		}

		
		return "/zv/content/ajaxstores";
	}
	
	@GetMapping("/stores/sub/{subcategory}")
	public String displayStoresBySubCat(ModelMap model, @PathVariable String subcategory) {
		
		Integer pageNo = 0;		
		Pageable paginate = PageRequest.of(pageNo, pageSize);

		subcategory = subcategory.substring(0, 1).toUpperCase() + subcategory.substring(1);

		processSubCatStoreList(model, "", subcategory, paginate);
		return "/zv/content/storeslist";
	}
	
	private void processSubCatStoreList(ModelMap model, String category, String subCategory, Pageable paginate) {
		ArrayList<Store> storesLst = new ArrayList<Store>();
		try {
			if("".equals(category)) {
				storesLst = dataHandler.findBySubcategory(subCategory, paginate);
			}else {
				storesLst = dataHandler.findByCategoryAndSubcategory(category, subCategory, paginate);
			}
			log.info(" category " + category);
			log.info("sub category " + subCategory);
			log.info("total stores in the sub category " + storesLst.size());

		} catch (Exception e) {
			storesLst = new ArrayList<Store>();
			log.info("processStoreList: " + e);
		}
		
		if(storesLst.size() != 0) {
			model.put("category", storesLst.get(0).getCategory());
			model.put("subcategory", storesLst.get(0).getsubcategory());
		}else {
			model.put("category", "");
			model.put("subcategory", "");
		}
		
		model.put("storeslst", storesLst);

		if (storesLst.size() < pageSize) {
			model.put("laststore", "end");
		} else {
			model.put("laststore", paginate.getPageNumber() + 1);
		}
	}
	
	@GetMapping("/visitstore/{storename}")
	public String visitStore(@PathVariable String storename) {
		Store sdto = null;
		try {
			sdto = dataHandler.findByName(storename);

		} catch (Exception e) {
			log.info("visitStore exception: " + e);
		}

		if (sdto != null && !sdto.getUrl().isEmpty()) {
			return "redirect:" + sdto.getUrl().trim();
		} else {
			return "/zv/common/error";
		}
	}

	@GetMapping("/")
	public String mainPg(ModelMap model) {

		return "/zv/content/mainpage";
	}

}
