package de.bitnoise.sonferenz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

import org.hibernate.annotations.Type;

import com.sun.istack.internal.Nullable;

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
  
  @OneToOne
  @Nullable
  UserModel creator;
  
  @Column
  @Nullable
  Integer used;
  
  @Column
  Boolean active;
  
  @Column
  String token;
  
  @Column
  @Temporal(TemporalType.TIMESTAMP)
  Date expiry;

  @Column
  @Nullable
  @Type(type = "text")
  String data;

}
