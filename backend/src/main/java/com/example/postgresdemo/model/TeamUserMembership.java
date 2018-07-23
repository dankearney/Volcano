package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.postgresdemo.repository.TeamUserMembershipRepository;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;


// TODO: add indexes here for performance. not super relevant on a teeny table
@Entity
@Table(name = "teamUserMemberships")
public class TeamUserMembership extends AuditModel {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long TeamUserMembershipId; // required despite being nonsense

    @Column()
    private Long teamId;

    @Column()
    private Long userId;

    @Column()
    private String status; //admin/normal/requesting

    @Column()
    private String password;

    @Transient
    private User user;

    //getters

    public Long getTeamUserMembershipId() {
        return TeamUserMembershipId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public Long getUserId() {
      return userId;
    }

    public String getStatus() {
      return status;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    //setters

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(String newStatus) {
      this.status = newStatus;
    }

    public void setTeamUserMembershipId(Long tumi) {
        this.TeamUserMembershipId = tumi;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
