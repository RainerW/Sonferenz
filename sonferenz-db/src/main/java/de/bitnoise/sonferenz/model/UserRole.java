package de.bitnoise.sonferenz.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@Table 
@EqualsAndHashCode(of={"id"})
public class UserRole extends ModelBase implements DoInterface<Integer>
{
  @Id
  @GeneratedValue
  Integer id;

  @Basic
  String name;
  
  @Override
  public boolean equals(Object obj)
  {
    return super.equals(obj);
  }
}
