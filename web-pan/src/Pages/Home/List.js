import React, { useState, useEffect } from "react";

import { withRouter } from 'react-router-dom';
import { toJS } from "mobx";
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

import { GetQueryString } from '../../Util'

// eslint-disable-next-line import/no-anonymous-default-export
export default withRouter(inject("home")(
  observer(({ 
    history, 
    home: { 
      fillList, 
      selectedRowKeys, 
      update,
      getFileList
    }}) => {

    const columns = [
      {
        title: "",
        dataIndex: "name",
        key: "name",
        render: (text, record)  => {
          return(
            <p className="list_name" onClick={()=>jumpToFolder(record)}>{text}</p>
          );
        }
      },
      {
        title: "",
        dataIndex: "time",
        key: "time",
      },
      {
        title: "",
        dataIndex: "operating",
        key: "operating",
        render: () => (
          <div>
            <span className="list_operating">下载</span>
          </div>
        ),
      },
    ];

    useEffect(() => {
      if (selectedRowKeys.length > 0) {
        update({hasSelected: true})
      }else {
        update({hasSelected: false})
      }
    }, [selectedRowKeys]);

    const getList = () => {
      const destFolderId = GetQueryString('destFolderId')
      getFileList(1, destFolderId);
    }

    const jumpToFolder = (e) => {
      if (e.fileId) {
        console.log('这是一个文件')
        return
      } else {
        history.push(`/home?destFolderId=${e.folderId}`)
        getList()
      }
    }

    const onSelectChange = (e) => {
      update({selectedRowKeys: [...e]})
    }

    return (
      <div>
        <Breadcrumb separator=">" className="list_bread">
          <Breadcrumb.Item>我的文件</Breadcrumb.Item>
        </Breadcrumb>
        <Table
          className="list_table"
          columns={columns}
          dataSource={fillList}
          showHeader={false}
          rowSelection={{
            // selectedRowKeys,
            onChange: onSelectChange,
            type: "checkout",
          }}
        />
      </div>
    );
  }))
);
