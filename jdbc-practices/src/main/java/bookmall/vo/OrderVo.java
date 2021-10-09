package bookmall.vo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderVo {
	private Long no;
	private String orderNo;
	private Long price; // 결제금액
	private String shipAddr; // 배송지
	private Long memberNo;

	private Long bookNo;
	private Long quantity;

	private String memberName;
	private String memberEmail;
	private String bookTitle;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo() {
		LocalDateTime now = LocalDateTime.now();
		String nowDate = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		String orderNo = nowDate + getNo();
		this.orderNo = orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getShipAddr() {
		return shipAddr;
	}

	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}

	public Long getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Long getBookNo() {
		return bookNo;
	}

	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long Quantity) {
		this.quantity = Quantity;
	}

	@Override
	public String toString() {
		if(memberEmail != null) {
			return "Order [order_no=" + orderNo + ", name=" + memberName +
					", email=" + memberEmail + ", price=" + price + ", ship_addr=" + shipAddr +"]";
		}
		else if(bookTitle != null) {
			return "Order [order_no=" + orderNo + ", book_no=" + bookNo +
					", book_title=" + bookTitle + ", quantity=" + quantity + "]";
		}
		else if (memberNo != null) {
			return "Order [no=" + no + ", order_no=" + orderNo + ", price=" + price +
					", shipAddr=" + shipAddr + ", memberNo=" + memberNo;
		}
		else {
			return "Order_Book [order_no=" + no + ", book_no=" + bookNo +
					", quantity=" + quantity + ", price=" + price + "]";
		}
	}

}
