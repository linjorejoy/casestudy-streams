package com.cognizant.truyum.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.cognizant.truyum.model.Cart;
import com.cognizant.truyum.model.MenuItem;

public class CartDaoCollectionImpl implements CartDao {
	private static Map<Long, Cart> userCarts;

	public CartDaoCollectionImpl() {
		super();
		if (userCarts == null) {
			userCarts = new HashMap<Long, Cart>();
		}
	}

	public Map<Long, Cart> getUserCarts() {
		return userCarts;
	}

	public void setUserCarts(Map<Long, Cart> userCarts) {
		CartDaoCollectionImpl.userCarts = userCarts;
	}

	@Override
	public void addCartItem(long userId, long menuItemId) {

		MenuItemDao menuItemDao = new MenuItemDaoCollectionImpl();
		MenuItem item = menuItemDao.getMenuItem(menuItemId);

		if (userCarts.containsKey(userId)) {
			List<MenuItem> menuItemList = userCarts.get(userId).getMenuItemList();
			menuItemList.add(item);
			userCarts.get(userId).setMenuItemList(menuItemList);
		} else {
			List<MenuItem> newUserMenuList = new ArrayList<>();
			newUserMenuList.add(item);
			Cart cart = new Cart(newUserMenuList);
			userCarts.put(userId, cart);
		}

	}

	@Override
	public Stream<MenuItem> getAllCartItems(long userId) throws CartEmptyException {
		
		Stream<MenuItem> cartItemStream = null;
		
		if (userCarts.get(userId).getMenuItemList().isEmpty()) {
			
			throw new CartEmptyException();
			
		} else {
			cartItemStream = userCarts
					.get(userId)
					.getMenuItemList()
					.stream();
			
			userCarts.get(userId).setTotal(userCarts
					.get(userId)
					.getMenuItemList()
					.stream().mapToDouble(d -> d.getPrice())
					.sum());
		}
		return cartItemStream;
	}

	
	
	@Override
	public void removeCartItem(long userId, long menuItemId) {
		
		Cart cart = userCarts.get(userId);
		List<MenuItem> allCartItems = cart.getMenuItemList();
		MenuItem itemToRemove = null;

		for (MenuItem item : allCartItems) {
			if (item.getId() == menuItemId) {
				itemToRemove = item;
				break;
			}
		}
		allCartItems.remove(itemToRemove);
		cart.setMenuItemList(allCartItems);
		userCarts.put(userId, cart);
	}
}
