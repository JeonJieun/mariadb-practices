package bookmall.vo;

public class CartVo {
	private Long quantitiy;
	private Long bookNo;
	private Long memberNo;
	public Long getQuantitiy() {
		return quantitiy;
	}
	public void setQuantitiy(Long quantitiy) {
		this.quantitiy = quantitiy;
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
	@Override
	public String toString() {
		return "CartVo [quantitiy=" + quantitiy + ", bookNo=" + bookNo + ", memberNo=" + memberNo + "]";
	}

	
}
