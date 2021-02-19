import React,{ useState, useEffect } from "react";

import { observer, inject } from 'mobx-react';
import { Layout } from 'antd'

import Header from "../../Components/Header";
import Menu from "../../Components/Menu";

import "./index.scss";

export default (inject('home')(observer(({
  home: {
    loginState,
    name,
    getFileList
  }
}) => {
  const [collapsed, setCollapsed] = useState(false)
  const { Sider } = Layout;

  useEffect(() => {
    getFileList(1)
  },[])

  const onCollapse = () => {
    setCollapsed(!collapsed)
  }

  return (
    <div>
      <Header isLogin={loginState} userName={name} />
      <Layout>
        <Sider collapsible collapsed={collapsed} onCollapse={onCollapse}>
          <Menu />
        </Sider>
        <Sider>
          <div style={{width:400, height:300}}></div>
        </Sider>
      </Layout>
    </div>
  );
})))
