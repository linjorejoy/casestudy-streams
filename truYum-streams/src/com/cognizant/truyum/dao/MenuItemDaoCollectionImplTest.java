package com.cognizant.truyum.dao;

import java.util.stream.Stream;

import com.cognizant.truyum.model.MenuItem;
import com.cognizant.truyum.util.DateUtil;

public class MenuItemDaoCollectionImplTest {

	static MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();

	public static void main(String[] args) {
		System.out.println("Menu List Admin Printing...");
		testGetMenuItemListAdmin();
		System.out.println("Menu List Customer Printing...");
		testGetMenuListCustomer();
		System.out.println("Modified Menu and Printing to check...");
		testModifyMenuItem();
		System.out.println("Completed.");

	}

	public static void testGetMenuItemListAdmin() {
		
		Stream<MenuItem> menuItemStream = menuItemDao.getMenuItemListAdmin();
		menuItemStream.forEach(System.out::println);
	}

	public static void testGetMenuListCustomer() {

		menuItemDao.getMenuItemListCustomer().forEach(System.out::println);;

	}

	public static void testModifyMenuItem() {
		MenuItem newMenuItem = new MenuItem(1, "Sandwich", 109.00f, true, new DateUtil().convertToDate("02/07/2017"),
				"MainCourse", true);
		menuItemDao.modifyMenuItem(newMenuItem);
		MenuItem modifiedMenuItem = menuItemDao.getMenuItem(1);
		System.out.println(modifiedMenuItem);

	}
}
