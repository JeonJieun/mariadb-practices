package bookmall.main;

import java.util.List;
import java.util.Scanner;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.OrderDao;
import bookmall.dao.test.BookDaoTest;
import bookmall.dao.test.CartDaoTest;
import bookmall.dao.test.CategoryDaoTest;
import bookmall.dao.test.MemberDaoTest;
import bookmall.dao.test.OrderDaoTest;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		// insert 순서
		// 1. category, member
		// 2. book, orders
		// 3. orders_book, cart
/*
		System.out.println("===== 카테고리 =====");
		new CategoryDaoTest();
		System.out.println();

		System.out.println("===== 멤버 =====");
		new MemberDaoTest();
		System.out.println();

		System.out.println("===== 책 =====");
		new BookDaoTest();
		System.out.println();

		System.out.println("===== 주문 =====");
		new OrderDaoTest();
		System.out.println();

		System.out.println("===== 카트 =====");
		new CartDaoTest();
		System.out.println();
*/
		search();

	}

	public static void search() {

		Scanner sc = new Scanner(System.in);
		int num1 = 5;
		int num2 = 0;
		while (num1 != 0) {
			System.out.println("0. 종료");
			System.out.println("1. 책 검색");
			System.out.println("2. 카트 검색");
			System.out.println("3. 주문 검색");
			System.out.print(">");
			num1 = sc.nextInt();
			sc.nextLine();

			if (num1 == 1) {
				System.out.println("1. 카테고리명 검색");
				System.out.println("2. 책이름 검색");
				System.out.print(">");
				num2 = sc.nextInt();
				sc.nextLine();
				if (num2 == 1) {
					System.out.print("카테고리명 입력 : ");
					String name = sc.nextLine();
					List<BookVo> list = new BookDao().findAll(1, name);
					for (BookVo vo : list) {
						System.out.println(vo);
					}
				}
				
				else if(num2 == 2) {
					System.out.print("책이름 입력 : ");
					String title = sc.nextLine();
					List<BookVo> list = new BookDao().findAll(2, title);
					for (BookVo vo : list) {
						System.out.println(vo);
					}
				}
				else { break; }
			} 
			
			else if (num1 == 2) {
				System.out.print("member_no 입력 : ");
				Long no = sc.nextLong();
				sc.nextLine();
				List<CartVo> list = new CartDao().findAll(no);
				for (CartVo vo : list) {
					System.out.println(vo);
				}
			}
			
			else if (num1 == 3) {
				System.out.println("1. 주문번호로 찾기");
				System.out.println("2. member_no로 찾기");
				System.out.print(">");
				num2 = sc.nextInt();
				sc.nextLine();
				if(num2 == 1) {
					System.out.print("주문번호 입력 : ");
					String orderNo = sc.nextLine();
					List<OrderVo> list = new OrderDao().findAll(orderNo);
					for (OrderVo vo : list) {
						System.out.println(vo);
					}
				}
				else if(num2 == 2) {
					System.out.print("member_no 입력 : ");
					Long no = sc.nextLong();
					sc.nextLine();
					List<OrderVo> list = new OrderDao().findAll(no);
					for (OrderVo vo : list) {
						System.out.println(vo);
					}
				}
				else { break; }
			}
			
			else { break; }
			
			num1 = 5;

		}
		
		sc.close();
	}

}
