package com.cognizant.truyum.dao;

import java.util.stream.Stream;

import com.cognizant.truyum.model.MenuItem;

public interface MenuItemDao {
	
	public Stream<MenuItem> getMenuItemListAdmin();
	
	public Stream<MenuItem> getMenuItemListCustomer();
	
	public void modifyMenuItem(MenuItem menuItem);
	
	public MenuItem getMenuItem(long menuItemId);
	
}
