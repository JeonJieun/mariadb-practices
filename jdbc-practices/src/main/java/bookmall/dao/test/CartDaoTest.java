package bookmall.dao.test;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {
	private static void insertTest() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		vo = new CartVo();
		vo.setQuantitiy(1L);
		vo.setBookNo(1L);
		vo.setMemberNo(1L);
		dao.insert(vo);

		vo = new CartVo();
		vo.setQuantitiy(1L);
		vo.setBookNo(1L);
		vo.setMemberNo(2L);
		dao.insert(vo);

	}
}
