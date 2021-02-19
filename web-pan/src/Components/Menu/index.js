import React from "react";

import { Menu } from "antd";

import "./index.scss";

// eslint-disable-next-line import/no-anonymous-default-export
export default () => {

  return (
    <div className="menu">
      <Menu
        style={{ height: "560px" }}
        defaultSelectedKeys={["1"]}
        mode="inline"
      >
        <Menu.Item
          key="1"
          icon={<img className="menu_icon" src="assets/file.svg" />}
        >
          <span className="menu_text">我的文件</span>
        </Menu.Item>
        <Menu.Item
          key="2"
          icon={<img className="menu_icon" src="assets/friend.svg" />}
        >
          <span className="menu_text">我的朋友</span>
        </Menu.Item>
      </Menu>
    </div>
  );
};
