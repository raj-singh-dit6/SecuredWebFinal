package com.securedweb.model.tenant;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId",nullable = false )
    private User user;

    @Column(nullable = false)
    private Date expiryDate;
    
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;


    public void setExpiryDate(int hours){
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, hours);
        this.expiryDate =  now.getTime();
        System.err.println(now.getTime());
        System.err.println(expiryDate);
    }

    public boolean isExpired() {
    	SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	Date d1 = null;
		Date d2 = null;
		long diffHours=0;
		String currentTime =  formatter.format(new Date());
		String expiryTime =  formatter.format(this.getExpiryDate());		
    	try {
			d1 = formatter.parse(currentTime);
			d2 = formatter.parse(expiryTime);
			long diff = d2.getTime() - d1.getTime();
			diffHours = diff / (60 * 60 * 1000) % 24;
			System.err.print(diffHours + " hours, ");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return diffHours>72;
    }

}