package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImpl implements MenuItemDao {

	private static List<MenuItem> menuItemList;

	public MenuItemDaoCollectionImpl() {
		super();
		List<MenuItem> newList = new ArrayList<>();
		if (menuItemList == null) {
			
			// Including Sample Data
			
			newList.add(new MenuItem(1, "Sandwich", 99.0f, true, new DateUtil().convertToDate("15/03/2017"),
					"Main Course", true));
			newList.add(new MenuItem(2, "Burger", 129.0f, true, new DateUtil().convertToDate("23/12/2017"),
					"Main Course", false));
			newList.add(new MenuItem(3, "Pizza", 149.0f, true, new DateUtil().convertToDate("21/08/2018"),
					"Main Course", false));
			newList.add(new MenuItem(4, "French Fries", 57.0f, false, new DateUtil().convertToDate("02/07/2017"),
					"Starters", true));
			newList.add(new MenuItem(5, "Chocolate Brownie", 32.0f, true, new DateUtil().convertToDate("02/11/2022"),
					"Dessert", true));
			
		}
		menuItemList = newList;
	}

	public MenuItemDaoCollectionImpl(List<MenuItem> menuItemList) {

		super();
		MenuItemDaoCollectionImpl.menuItemList = menuItemList;
	}

	public List<MenuItem> getMenuItemList() {

		return menuItemList;
	}

	public void setMenuItemList(List<MenuItem> menuItemList) {

		MenuItemDaoCollectionImpl.menuItemList = menuItemList;
	}

	public Stream<MenuItem> getMenuItemListAdmin() {

		return menuItemList.stream();
	}

	
	public Stream<MenuItem> getMenuItemListCustomer() {
		
		Date currDate = new DateUtil().convertToDate("20/10/2020");
		
		return menuItemList.stream().filter(p -> p.isActive() &&  currDate.after(p.getDateOfLaunch()));
		
	}

	public void modifyMenuItem(MenuItem menuItem) {

		for (MenuItem eachItem : menuItemList) {
			if (menuItem.equals(eachItem)) {
				eachItem.setId(menuItem.getId());
				eachItem.setName(menuItem.getName());
				eachItem.setPrice(menuItem.getPrice());
				eachItem.setActive(menuItem.isActive());
				eachItem.setDateOfLaunch(menuItem.getDateOfLaunch());
				eachItem.setCategory(menuItem.getCategory());
				eachItem.setFreeDelivery(menuItem.isFreeDelivery());
//				eachItem = menuItem;
				return;
			}
		}
		menuItemList.add(menuItem);
	}

	public MenuItem getMenuItem(long menuItemId) {

		Optional<MenuItem> menuItemOptional = menuItemList.stream().filter(p -> p.getId() == menuItemId).findFirst();
		for (MenuItem menuItem : menuItemList) {
			if (menuItem.getId() == menuItemId) {
				return menuItem;
			}
		}
		return menuItemOptional.get();
	}
	
}
