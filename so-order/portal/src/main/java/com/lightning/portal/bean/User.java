package com.lightning.portal.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private long userId;
  private String userName;

  public User(String userName) {
    this.userName = userName;
  }
}
