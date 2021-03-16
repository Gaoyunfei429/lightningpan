import React, { useState, useEffect } from "react";

import { withRouter } from "react-router-dom";
import { observer, inject } from "mobx-react";
import { Layout, Button } from "antd";

import Head from "../../Components/Header";
import Menu from "../../Components/Menu";

import List from "./List"
import UploadModal from "./UploadModal"
import CreatFolder from './CreatFolder'
import TreeSelectModal from './TreeSelectModal'
import DeleteModal from './DeleteModal'

import { GetQueryString } from '../../Util'

import "./index.scss";

export default withRouter(inject("home")(
  observer(({
    history,
    home: { initialFolderId, hasSelected, loginState, name, getFileList, isModalVisible, update, isCreatModalVisible }, 
  }) => {
    const [collapsed, setCollapsed] = useState(false);
    const [isChild, setIsChild] = useState(false)
    const { Header, Content, Sider } = Layout;
    const destFolderId = GetQueryString('destFolderId')
    const userId = GetQueryString('userId')

    useEffect(() => {
      initData()
    }, [destFolderId]);

    const initData = async () => {
      let data = sessionStorage.getItem('initialFolderId');
      if(data !== destFolderId) {
        setIsChild(true)
      }else {
        setIsChild(false)
      }
      await getFileList(userId, destFolderId);
    }

    const onCollapse = () => {
      setCollapsed(!collapsed);
    };

    const showModal = () => {
      update({isModalVisible: !isModalVisible})
    }

    const creatFolder = () => {
      update({isCreatModalVisible: !isCreatModalVisible})
    }

    const treeSelect = (e) => {
      update({isTreeSelectModalVisible: true, copyOrMove: e})
    }

    const deleteModal = () => {
      update({isDeleteModalVisible: true})
    }

    const onBack = () => {
      history.goBack()
    }

    return (
      <div>
        <Head isLogin={loginState} userName={name} />
        <Layout className="menu_layout">
          <Sider collapsible collapsed={collapsed} onCollapse={onCollapse}>
            <Menu />
          </Sider>
          <Layout className="content_layout">
            <Header>
              {hasSelected ? (
                <div className="features_btn">
                  <Button className="header_button" onClick={()=>treeSelect(false)}>复制到</Button>
                  <Button className="header_button" onClick={()=>treeSelect(true)}>移动到</Button>
                  <Button type="primary" className="header_button" danger onClick={deleteModal}>
                    删除
                  </Button>
                  {
                    isChild && <Button onClick={onBack} className="header_button_back">返回</Button>
                  }
                </div>
              ) : (
                <div className="features_btn">
                  <Button type="primary" className="header_button" onClick={showModal}>
                    上传
                  </Button>
                  <Button onClick={creatFolder} className="header_button_new">新建文件夹</Button>
                  {
                    isChild && <Button onClick={onBack} className="header_button_back">返回</Button>
                  }
                </div>
              )}
            </Header>
            <Content>
              <List />
            </Content>
          </Layout>
        </Layout>
        <UploadModal />
        <CreatFolder />
        <TreeSelectModal />
        <DeleteModal />
      </div>
    );
  })
));
