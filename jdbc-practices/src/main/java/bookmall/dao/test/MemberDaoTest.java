package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	
	public MemberDaoTest() {
		insertTest();
		findAllTest();
	}
	
	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
	
	private static void insertTest() {
		MemberVo vo = null;
		MemberDao dao = new MemberDao();
		
		vo = new MemberVo();
		vo.setName("김김김");
		vo.setEmail("kim@gmail.com");
		vo.setPassword("1111");
		vo.setPhoneNum("010-1111-1111");
		dao.insert(vo);

		vo = new MemberVo();
		vo.setName("박박박");
		vo.setEmail("park@gmail.com");
		vo.setPassword("2222");
		vo.setPhoneNum("010-2222-2222");
		dao.insert(vo);

	}

}
