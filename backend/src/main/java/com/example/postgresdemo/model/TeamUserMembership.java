package com.example.postgresdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.example.postgresdemo.model.TeamUserMembership;
import com.example.postgresdemo.repository.TeamUserMembershipRepository;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@Entity
@Table(name = "teamUserMemberships", indexes = {@Index(name = "teamIdx", columnList="team1", unique = false),
                                                @Index(name = "userIdx", columnList="user1", unique = false)})
public class TeamUserMembership extends AuditModel {

    @Id
    private Long TeamUserMembershipId; // required despite being nonsense

    @Column(name = "team1")
    private Long teamId;

    @Column(name = "user1")
    private Long userId;

    @Column(name = "status")
    private String status; //admin/normal/requesting

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

}
