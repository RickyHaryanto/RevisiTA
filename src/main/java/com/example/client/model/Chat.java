package com.example.client.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)

    private Long chat_id;
    private String chat_pertanyaan;
	private String chat_jawaban;
	private String chat_date;
	private String chat_time;
	private String chat_dateadmin;
	private String chat_timeadmin;
    private Long user_id;

    public Chat() {
	}

	protected Chat(String chat_pertanyaan,String chat_jawaban,String chat_date, String chat_time,String chat_dateadmin, String chat_timeadmin,Long user_id) {
		super();
		this.chat_pertanyaan = chat_pertanyaan;
		this.chat_jawaban = chat_jawaban;
		this.chat_date = chat_date;
		this.chat_time = chat_time;
		this.chat_dateadmin = chat_dateadmin;
		this.chat_timeadmin = chat_timeadmin;
		this.user_id = user_id;
		
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getChat_id() {
		return chat_id;
	}
	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
    }


    public String getChat_pertanyaan() {
		return chat_pertanyaan;
	}
	public void setChat_pertanyaan(String chat_pertanyaan) {
		this.chat_pertanyaan = chat_pertanyaan;
    }



    public String getChat_jawaban() {
		return chat_jawaban;
	}
	public void setChat_jawaban(String chat_jawaban) {
		this.chat_jawaban = chat_jawaban;
	}
	
	public String getChat_date() {
		return chat_date;
	}
	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}
	
	public String getChat_time() {
		return chat_time;
	}
	public void setChat_time(String chat_time) {
		this.chat_time = chat_time;
	}



	public String getChat_dateadmin() {
		return chat_dateadmin;
	}
	public void setChat_dateadmin(String chat_dateadmin) {
		this.chat_dateadmin = chat_dateadmin;
	}
	
	public String getChat_timeadmin() {
		return chat_timeadmin;
	}
	public void setChat_timeadmin(String chat_timeadmin) {
		this.chat_timeadmin = chat_timeadmin;
	}

    public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
    }
}