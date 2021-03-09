import React, { useState, useEffect } from "react";

import { toJS } from "mobx";
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

const columns = [
  {
    title: "",
    dataIndex: "name",
    key: "name",
    render: (text, record)  => {
      console.log(text, record);
      return(
        <p className="list_name">{text}</p>
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

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { fillList, selectedRowKeys, hasSelected, update } }) => {

    useEffect(() => {
      console.log("a", selectedRowKeys);
      if (selectedRowKeys.length > 0) {
        update({hasSelected: true})
      }else {
        update({hasSelected: false})
      }
    }, [selectedRowKeys]);

    const onSelectChange = (e) => {
      console.log('selectedRowKeys changed: ', e);
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
  })
);
