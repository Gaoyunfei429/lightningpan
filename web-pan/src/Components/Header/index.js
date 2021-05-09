import React from "react";

import "./index.scss";

// eslint-disable-next-line import/no-anonymous-default-export
export default (props) => {
  const { isLogin, userName, exit } = props;
  return (
    <div className="header">
      <img className="logo" src="assets/OIP.jpg" />
      {isLogin ? (
        <div className="login_state_box">
          <span className="state_text">{userName}</span>
          <span>|</span>
          <span className="state_text" onClick={exit}>退出</span>
        </div>
      ) : (
        <div className="login_state_box">
          <span className="state_text">登录</span>
          <span>|</span>
          <span className="state_text">注册</span>
        </div>
      )}
    </div>
  );
};
