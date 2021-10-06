package bookmall.vo;

public class OrderVo {
	private Long no;
	private Long orderNo;
	private Long price;
	private String shipAddr;
	private Long memberNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
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
	@Override
	public String toString() {
		return "OrderVo [no=" + no + ", orderNo=" + orderNo + ", price=" + price + ", shipAddr=" + shipAddr
				+ ", memberNo=" + memberNo + "]";
	}
	
	
	

}
