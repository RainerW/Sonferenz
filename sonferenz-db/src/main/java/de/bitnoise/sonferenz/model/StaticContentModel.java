package de.bitnoise.sonferenz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Type;

import com.sun.istack.internal.Nullable;


@Entity
@Data
@Table 
@EqualsAndHashCode(of =
  { "id" })
public class StaticContentModel implements DoInterface<Integer>
{
  @Id
  @GeneratedValue
  Integer id;
  
  @Column
  String name;
  
  @Column
  @Nullable
  Boolean allowHtml;

  @Column
  @Type(type = "text")
  String html;
  
}
