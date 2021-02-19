import React, { useState, useEffect } from "react";

import { observer, inject } from "mobx-react";
import { Layout } from "antd";

import Head from "../../Components/Header";
import Menu from "../../Components/Menu";

import "./index.scss";

export default inject("home")(
  observer(({ home: { loginState, name, getFileList } }) => {
    const [collapsed, setCollapsed] = useState(false);
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
              aaa
            </Header>
            <Content>
              bbb
            </Content>
          </Layout>
        </Layout>
      </div>
    );
  })
);
