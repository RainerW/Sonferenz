package de.bitnoise.sonferenz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.sun.istack.internal.Nullable;


import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name="action")
public class ActionModel implements DoInterface<Integer>
{
  @Id
  @GeneratedValue
  Integer id;

  @Column
  String action;
  
  @Column
  @Nullable
  UserModel creator;
  
  @Column
  Integer used;
  
  @Column
  String token;
  
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  Date expiry;

  @Column
  @Type(type = "text")
  String data;
}
