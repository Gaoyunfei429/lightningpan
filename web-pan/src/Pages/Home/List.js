import React, {useEffect} from "react";

import { toJS } from 'mobx';
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

const columns = [
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '年龄',
    dataIndex: 'age',
    key: 'age',
  },
  {
    title: '住址',
    dataIndex: 'address',
    key: 'address',
  },
];

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { fillList } }) => {
    useEffect(() => {
      console.log('a',toJS(fillList))
    },[fillList])
    return (
      <div>
        <Breadcrumb separator=">">
          <Breadcrumb.Item>我的文件</Breadcrumb.Item>
        </Breadcrumb>
        <Table columns={columns} dataSource={fillList} showHeader={false} />
      </div>
    );
  })
);
