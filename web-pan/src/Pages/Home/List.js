import React, { useEffect } from "react";

import { withRouter } from "react-router-dom";
import { observer, inject } from "mobx-react";
import { Table, Breadcrumb } from "antd";

import "./List.scss";

import { GetQueryString } from "../../Util";

// eslint-disable-next-line import/no-anonymous-default-export
export default withRouter(
  inject("home")(
    observer(
      ({ history, home: { fillList, selectedRow, update, getFileList } }) => {
        const columns = [
          {
            title: "",
            dataIndex: "name",
            key: "name",
            render: (text, record) => {
              return (
                <p className="list_name" onClick={() => jumpToFolder(record)}>
                  {text}
                </p>
              );
            },
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
          if (
            selectedRow.folderArr.length > 0 ||
            selectedRow.fileArr.length > 0
          ) {
            update({ hasSelected: true });
          } else {
            update({ hasSelected: false });
          }
        }, [selectedRow]);

        const getList = () => {
          const destFolderId = GetQueryString("destFolderId");
          getFileList(1, destFolderId);
        };

        const jumpToFolder = (e) => {
          if (e.fileId) {
            console.log("这是一个文件");
            return;
          } else {
            history.push(`/home?destFolderId=${e.folderId}`);
            getList();
          }
        };

        const onSelectChange = (key, e) => {
          // eslint-disable-next-line array-callback-return
          update({
            selectedRow: {
              folderArr: [],
              fileArr: [],
            },
          });
          e.map((item) => {
            if (item.fileId) {
              update({
                selectedRow: {
                  folderArr: [...selectedRow.folderArr],
                  fileArr: [...selectedRow.fileArr, item.fileId],
                },
              });
            } else {
              update({
                selectedRow: {
                  folderArr: [...selectedRow.folderArr, item.folderId],
                  fileArr: [...selectedRow.fileArr],
                },
              });
            }
          });
          // update({selectedRowKeys: [...e]})
        };

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
              }}
            />
          </div>
        );
      }
    )
  )
);
