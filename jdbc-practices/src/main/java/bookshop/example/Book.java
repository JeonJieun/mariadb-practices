package bookshop.example;

public class Book{
	
	private int bookNo; //번호
	private String title; //제목
	private String author; //작가
	private int stateCode; //대여유무 1: 대여가능, 0 : 대여불가
	
	Book(int bookNo,String title,String author){
		setStateCode(1);
		setBookNo(bookNo);
		setTitle(title);
		setAuthor(author);
	}
	
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	public void rent() { //대여기능
		if(getStateCode()==1) {
			setStateCode(0);
			System.out.println(getTitle()+"이(가) 대여 됐습니다.");
		}
		else System.out.println("대여할 수 없음");
	}
	public void print() { //stateCode가 1이면 “재고있음”으로 0이면 “대여중”
		String str;
		if(getStateCode()==1) { str = "재고있음"; }
		else { str = "대여중"; }
		System.out.println(
				"책 번호:"+getBookNo()+
				", 책 제목:"+getTitle()+
				", 작가:"+getAuthor()+
				", 대여 유무:"+str);
		
	}

}