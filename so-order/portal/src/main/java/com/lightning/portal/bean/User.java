package com.lightning.portal.bean;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

  private long userId;
  private String userName;
  private String password;
}
