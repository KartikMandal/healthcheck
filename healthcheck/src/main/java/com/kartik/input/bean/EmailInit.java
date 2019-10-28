/**
 * 
 */
package com.kartik.input.bean;

import javax.mail.Session;

/**
 * @author kmandal
 *
 */
public class EmailInit {
	private Session mySession;

    private String to;

    private String from;

    private String subject;

    private String body;

    /**
	 * @return the mySession
	 */
	public Session getMySession() {
		return mySession;
	}

	/**
	 * @param mySession the mySession to set
	 */
	public void setMySession(Session mySession) {
		this.mySession = mySession;
	}

	public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
