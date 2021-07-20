package web.vo;

import java.util.Date;

public class ReplyVO {
    private String id, content;
    private int articleNo;
    private Date writeDate;

	public ReplyVO() {
		super();

	}

	public ReplyVO(String id, String content, int articleNo, Date writeDate) {
		super();
		setId(id);
		setContent(content);
		setArticleNo(articleNo);
		setWriteDate(writeDate);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getArticleNo() {
		return articleNo;
	}

	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}


	

	
    
    
}
