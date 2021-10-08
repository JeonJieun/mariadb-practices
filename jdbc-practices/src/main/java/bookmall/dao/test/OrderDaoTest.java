package bookmall.dao.test;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {
	
	private static void insertTest() {
		OrderVo vo = null;
		OrderDao dao = new OrderDao();
		
		vo = new OrderVo();
		vo.setOrderNo();
		vo.setPrice();
		vo.setShipAddr("부산시 해운대구");
		vo.setMemberNo();
		dao.insert(vo);

	}

}
