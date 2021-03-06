package ru.ifmo.server.auth.access;

import org.apache.tomcat.util.security.MD5Encoder;

import javax.persistence.*;
import java.nio.ByteBuffer;
import java.util.Date;

@Entity
@Table(name = "access_tokens", schema = "crowdfunding")
public class AccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "access_tokens_id_seq")
    private int id;

    @Column(name = "user_id")
    private int userId;

    private long expires;

    @Column(name = "access_token")
    private String accessToken;

    public AccessToken(int userId, Date expires) {
        this.userId = userId;
        this.expires = expires.getTime();
        long time = expires.getTime();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES * 2);
        buffer.putLong(time);
        buffer.putLong(time / 2);
        accessToken = MD5Encoder.encode(buffer.array());
    }

    public AccessToken() {}

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public long getExpires() {
        return expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
