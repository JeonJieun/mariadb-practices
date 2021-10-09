package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {
	
	public OrderDaoTest() {
		insertTest();
		insertOrderBookTest();
		findAllOrderTest();
		System.out.println();
		System.out.println("===== 주문 책 =====");
		findAllOrderBookTest();
	}

	private static void insertTest() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setPrice(17000L);
		vo.setShipAddr("부산시 해운대구");
		vo.setMemberNo(1L);
		dao.insert(vo);
	}
	
	private static void insertOrderBookTest() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setPrice(17000L);
		vo.setQuantity(1L);
		vo.setBookNo(1L);
		vo.setNo(1L);
		dao.insertOrderBook(vo);
		
		vo = new OrderVo();
		vo.setPrice(32000L);
		vo.setQuantity(2L);
		vo.setBookNo(2L);
		vo.setNo(1L);
		dao.insertOrderBook(vo);
	}
	
	private static void findAllOrderTest() {
		List<OrderVo> list = new OrderDao().findAllOrder();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void findAllOrderBookTest() {
		List<OrderVo> list = new OrderDao().findAllOrderBook();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}

}
