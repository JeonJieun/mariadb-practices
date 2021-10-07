package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
//findAll, insert, update
public class BookDao {
	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3306/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}
	
	//전체 출력
	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				" select a.no, a.title, a.price, b.name as categoryName " +
				" from book a, category b " +
				" where a.category_no = b.no " +
				" order by a.no desc ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				String categoryName = rs.getString(4);
				
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
//카테고리 별 또는 검색 결과의 도서 리스트
	public List<BookVo> findAll(int num, String str) {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql;
			
			if(num==1) { //카테고리 이름
				sql = 
				" select a.no, a.title, a.price, b.name as categoryName " +
				" from book a, category b " +
				" where a.category_no = b.no " +
				" and b.name=? " +
				" order by a.no desc ";
			}
			
			else{ //책 이름
				sql = 
						" select a.no, a.title, a.price, b.name as categoryName " +
						" from book a, category b " +
						" where a.category_no = b.no " +
						" and a.title=? " +
						" order by a.no desc ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, str);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				String categoryName = rs.getString(4);
				
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryName(categoryName);
				
				result.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}


	public boolean update(Long no, String title, Long price, Long categoryNo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				"update book" +
				"   set no=?, title=?, price=?, category_no=?" +
				" where no=?";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, no);
			pstmt.setString(2, title);
			pstmt.setLong(3, price);
			pstmt.setLong(4, categoryNo);
			pstmt.setLong(5, no);
			
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

}
