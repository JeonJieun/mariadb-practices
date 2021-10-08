package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderVo;

public class OrderDao {
	
	public boolean insertOrderBook(OrderVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "insert into orders values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, vo.getQuantity());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getBookNo());
			pstmt.setString(4, vo.getOrderNo());
			
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
	
	public boolean insert(OrderVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "insert into orders values(null, '1', 1, '1', 1)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
			
			update(vo.getOrderNo(), vo.getPrice(), vo.getShipAddr(), vo.getMemberNo());
			
			
			
			
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
	
	public boolean update(String orderNo, Long Price, String shipAddr, Long memberNo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				" update orders " +
				" set order_no=?, price=?, ship_addr=?, memeber_no=? " +
				" where order_no = '1' ";
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
	
	public List<OrderVo> findAll(String orderNo) {
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = " select a.order_no, b.name, b.email, a.price, a.ship_addr " +
						 " from orders a, member b " +
						 " where a.member_no = b.no " +
						 " and a.order_no =? " +
						 " order by a.order_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, orderNo);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String order_no = rs.getString(1);
				String memberName = rs.getString(2);
				String memberEmail = rs.getString(3);
				Long price = rs.getLong(4);
				String shipAddr = rs.getString(5);
				
				
				OrderVo vo = new OrderVo();
				vo.setOrderNo(order_no);
				vo.setMemberName(memberName);
				vo.setMemberEmail(memberEmail);
				vo.setPrice(price);
				vo.setShipAddr(shipAddr);
				
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
	
	public List<OrderVo> findAll(Long no) {
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = " select a.order_no, b.no, b.title, c.quantity " +
						 " from orders a, book b, cart c, orders_book d, member e " +
						 " where e.no =? " +
						 " and a.member_no = e.no and e.no = c.member_no and c.book_no = b.no " +
						 " and b.no = d.book_no and d.order_no = a.no" +
						 " order by a.order_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, no);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				//select a.order_no, b.no, b.title, c.quantity
				String orderNo = rs.getString(1);
				Long bookNo = rs.getLong(2);
				String bookTitle = rs.getString(3);
				Long quantity = rs.getLong(4);
				
				
				OrderVo vo = new OrderVo();
				vo.setOrderNo(orderNo);
				vo.setBookNo(bookNo);
				vo.setBookTitle(bookTitle);
				vo.setQuantity(quantity);
				
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


}
