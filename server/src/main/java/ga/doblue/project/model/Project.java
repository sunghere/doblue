package ga.doblue.project.model;

import java.io.Serializable;

/**
 * Created by SungHere on 2017-06-02.
 */

public class Project implements Serializable {

    private int seq;
    private String url="";
    private String img="";
    private String title="";
    private String content="";
    private String sdate="";
    private String edate="";
    private int completed;
    private int del = 0;
    private String category;

	public Project(int seq, String url, String img, String title, String content, String sdate, String edate,
			int completed, int del, String category) {
		super();
		this.seq = seq;
		this.url = url;
		this.img = img;
		this.title = title;
		this.content = content;
		this.sdate = sdate;
		this.edate = edate;
		this.completed = completed;
		this.del = del;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Project [seq=" + seq + ", url=" + url + ", img=" + img + ", title=" + title + ", content=" + content
				+ ", sdate=" + sdate + ", edate=" + edate + ", completed=" + completed + ", del=" + del + ", category="
				+ category + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Project() {
    }

  
}
