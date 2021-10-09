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
			String sql = "insert into orders_book values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, vo.getQuantity());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getBookNo());
			pstmt.setLong(4, vo.getNo());
			
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
			
			if(result) {
				update(findNoInsertVo(vo));
			}

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
	
	public boolean update(OrderVo vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = 
				" update orders " +
				" set order_no=?, price=?, ship_addr=?, member_no=? " +
				" where no =? ";
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setString(1, vo.getOrderNo());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setString(3, vo.getShipAddr());
			pstmt.setLong(4, vo.getMemberNo());
			pstmt.setLong(5, vo.getNo());
			
			
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
			String sql = " select a.order_no, b.no, b.title, d.quantity " +
						 " from orders a, book b, member c, orders_book d " +
						 " where c.no =? " +
						 " and a.member_no = c.no " +
						 " and a.no = d.orders_no " +
						 " and b.no = d.book_no " + 
						 " order by a.order_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			pstmt.setLong(1, no);
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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

	public List<OrderVo> findAllOrder(){
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = " select no, order_no, price, ship_addr, member_no " +
						 " from orders " +
						 " order by order_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String orderNo = rs.getString(2);
				Long price = rs.getLong(3);
				String shipAddr = rs.getString(4);
				Long memberNo = rs.getLong(5);
				
				
				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setOrderNo(orderNo);
				vo.setPrice(price);
				vo.setShipAddr(shipAddr);
				vo.setMemberNo(memberNo);
				
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
	
	public List<OrderVo> findAllOrderBook(){
		List<OrderVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			//3. SQL 준비
			String sql = " select quantity, price, book_no, orders_no " +
						 " from orders_book " +
						 " order by orders_no desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long quantity = rs.getLong(1);
				Long price = rs.getLong(2);
				Long bookNo = rs.getLong(3);
				Long no = rs.getLong(4);
				
				
				OrderVo vo = new OrderVo();
				vo.setQuantity(quantity);
				vo.setPrice(price);
				vo.setBookNo(bookNo);
				vo.setNo(no);
				
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
	
	public OrderVo findNoInsertVo(OrderVo vo) {
		
		Long no = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
			
			//3. SQL 준비
			String sql = " select no " +
						 " from orders " +
						 " where order_no = '1' " ;
			
			pstmt = conn.prepareStatement(sql);
			
			//4. 바인딩(binding)
			
			//5. SQL 실행
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				no = rs.getLong(1);
			}
			vo.setNo(no);
			vo.setOrderNo();

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
		
		return vo;
	}
}
