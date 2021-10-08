package bookmall.dao.test;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	private static void insertTest() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setTitle("트와일라잇");
		vo.setPrice(17000L);
		vo.setCategoryNo(1L);
		dao.insert(vo);

		vo = new BookVo();
		vo.setTitle("뉴문");
		vo.setPrice(16000L);
		vo.setCategoryNo(2L);
		dao.insert(vo);

		vo = new BookVo();
		vo.setTitle("이클립스");
		vo.setPrice(15000L);
		vo.setCategoryNo(3L);
		dao.insert(vo);

	}
	

}