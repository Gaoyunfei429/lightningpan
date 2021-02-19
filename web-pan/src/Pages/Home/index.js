import React, { useState, useEffect } from "react";

import { observer, inject } from "mobx-react";
import { Layout, Button } from "antd";

import Head from "../../Components/Header";
import Menu from "../../Components/Menu";

import List from "./List"
import "./index.scss";

export default inject("home")(
  observer(({ home: { loginState, name, getFileList } }) => {
    const [collapsed, setCollapsed] = useState(false);
    const [hasSelect, setHasSelect] = useState(false);
    const { Header, Content, Sider } = Layout;

    useEffect(() => {
      getFileList(1);
    }, []);

    const onCollapse = () => {
      setCollapsed(!collapsed);
    };

    return (
      <div>
        <Head isLogin={loginState} userName={name} />
        <Layout className="menu_layout">
          <Sider collapsible collapsed={collapsed} onCollapse={onCollapse}>
            <Menu />
          </Sider>
          <Layout className="content_layout">
            <Header>
              {hasSelect ? (
                <div className="features_btn">
                  <Button className="header_button">复制到</Button>
                  <Button className="header_button">移动到</Button>
                  <Button type="primary" className="header_button" danger>
                    删除
                  </Button>
                </div>
              ) : (
                <div className="features_btn">
                  <Button type="primary" className="header_button">
                    上传
                  </Button>
                  <Button className="header_button_new">新建文件夹</Button>
                </div>
              )}
            </Header>
            <Content>
              <List />
            </Content>
          </Layout>
        </Layout>
      </div>
    );
  })
);
