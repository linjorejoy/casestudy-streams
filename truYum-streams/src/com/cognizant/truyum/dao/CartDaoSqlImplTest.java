package com.cognizant.truyum.dao;

public class CartDaoSqlImplTest {
	private static CartDaoSqlImpl cartDaoImpl = new CartDaoSqlImpl();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Inside CartDaoSqlImplTest's Main");
		System.out.println("AddCartItem invoked");
		testAddCartItem();
		System.out.println("getAllCartItem invoked");
		testGetAllCartItem();
		System.out.println("removeCartItem invoked");
		testRemoveCartItem();
		testGetAllCartItem();
		System.out.println("Completed..");
		
	}
	
	public static void testAddCartItem() {
		
		cartDaoImpl.addCartItem(1, 2);
		cartDaoImpl.addCartItem(1, 3);
		cartDaoImpl.addCartItem(2, 2);
		cartDaoImpl.addCartItem(2, 3);
		cartDaoImpl.addCartItem(2, 4);
	}
	
	public static void testGetAllCartItem() {
		long userId = 1;

		try {
			cartDaoImpl.getAllCartItems(userId).forEach(System.out::println);
			
		} catch (CartEmptyException e) {
			System.out.printf("The user id number %f did not buy anything\n",userId);
			e.printStackTrace();
		}
	}
	
	public static void testRemoveCartItem() {

		long userId = 2;
		System.out.println("\nBefore Delelting");
		try {
			cartDaoImpl.getAllCartItems(userId).forEach(System.out::println);
			
			long menuItemId = 4;
			cartDaoImpl.removeCartItem(userId, menuItemId);
			System.out.println("\nAfter Delelting");
			
			cartDaoImpl.getAllCartItems(userId).forEach(System.out::println);
			
		} catch (CartEmptyException e) {
			System.out.printf("The user id number %f did not buy anything\n",userId);
			e.printStackTrace();
		}
		System.out.println();
	}
}
