import React, {useEffect} from "react";

import { toJS } from 'mobx';
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

const columns = [
  {
    title: '',
    dataIndex: 'fileName',
    key: 'fileName',
  },
  {
    title: '',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: '',
    dataIndex: 'operating',
    key: 'operating',
    render: () => (
      <a>下载</a>
    )
  }
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
