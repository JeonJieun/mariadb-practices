package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	
	public BookDaoTest() {
		insertTest();
		findAllTest();
	}
	
	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setTitle("해리포터");
		vo.setPrice(17000L);
		vo.setCategoryNo(1L);
		dao.insert(vo);

		vo = new BookVo();
		vo.setTitle("JAVA 프로그래밍");
		vo.setPrice(16000L);
		vo.setCategoryNo(2L);
		dao.insert(vo);

		vo = new BookVo();
		vo.setTitle("총, 균, 쇠");
		vo.setPrice(15000L);
		vo.setCategoryNo(3L);
		dao.insert(vo);

	}
	

}