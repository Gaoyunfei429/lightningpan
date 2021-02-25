import React, { useEffect } from "react";

import { toJS } from "mobx";
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

const columns = [
  {
    title: "",
    dataIndex: "fileName",
    key: "fileName",
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
        {/* <span className="list_operating_parting">|</span> */}
        {/* <span className="list_operating">删除</span> */}
      </div>
    ),
  },
];

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { fillList } }) => {
    useEffect(() => {
      console.log("a", toJS(fillList));
    }, [fillList]);
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
            type: "checkout",
          }}
        />
      </div>
    );
  })
);
