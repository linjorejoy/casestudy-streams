package com.cognizant.truyum.dao;

import java.util.stream.Stream;

import com.cognizant.truyum.model.MenuItem;

public interface CartDao {
	
	public void addCartItem(long userId, long menuItemId);
	
	public Stream<MenuItem> getAllCartItems(long userId) throws CartEmptyException;
	
	public void removeCartItem(long userId, long menuItemId);
	
}
