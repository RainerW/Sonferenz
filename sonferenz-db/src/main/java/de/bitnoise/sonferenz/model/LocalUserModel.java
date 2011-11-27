package de.bitnoise.sonferenz.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@Table 
@EqualsAndHashCode(of={"id"})
public class LocalUserModel extends ModelBase implements DoInterface<Integer>
{
  @Id
  @GeneratedValue
  Integer id;

  @Column
  String name;
  
  @Column
  String password;

  @Override
  public boolean equals(Object obj)
  {
    return super.equals(obj);
  }
}
