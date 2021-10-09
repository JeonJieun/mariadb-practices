package bookmall.vo;

public class CartVo {
	private Long quantity;
	private Long bookNo;
	private Long memberNo;
	
	private String bookName;
	private Long bookPrice;
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Long getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(Long bookPrice) {
		this.bookPrice = bookPrice;
	}
	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", bookName=" + bookName + ", bookPrice=" + bookPrice + "]";
	}
	
	
	
}