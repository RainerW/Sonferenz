package de.bitnoise.sonferenz.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
//@Table 
@EqualsAndHashCode(of={"id"})
public class UserModel extends ModelBase implements DoInterface<Integer>
{
  @Id
  @GeneratedValue
  Integer id;

  @Column
  String name;
  
  @Column
  String email;
  
  @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
  AuthMapping provider;

  @ManyToMany
  Set<UserRole> roles;
  
  @Override
  public String toString()
  {
    return getName();
  }

}
