import React, { useEffect } from "react";

import { withRouter } from "react-router-dom";
import { observer, inject } from "mobx-react";
import { message, Table } from "antd";

import { GetQueryString } from "../../Util";

import "./List.scss";

// eslint-disable-next-line import/no-anonymous-default-export
export default withRouter(
  inject("home")(
    observer(
      ({ history, home: { fillList, selectedRowKeys, selectedRow, update, getFileList } }) => {
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
            render: (text, record, index) => 
                <span className="list_operating" onClick={()=>downLoad(record)}>下载</span>
            ,
          },
        ];

        const downLoad = async (e) => {
          if (e.fileName) {
            window.open(`http://42.193.103.37:8080/downloadFile?srcFileId=${e.fileId}`)
          }else {
            message.error('目前仅支持下载文件')
          }
        };

        useEffect(() => {
          if (
            selectedRow.folderArr.length > 0 ||
            selectedRow.fileArr.length > 0
          ) {
            update({ hasSelected: true });
          } else {
            update({ hasSelected: false });
          }
        }, [selectedRow.folderArr, selectedRow.fileArr]);

        const getList = () => {
          const destFolderId = GetQueryString("destFolderId");
          const userId = GetQueryString("userId");
          getFileList(userId, destFolderId);
        };

        const jumpToFolder = (e) => {
          const userId = GetQueryString("userId");
          if (e.fileId) {
            console.log("这是一个文件");
            return;
          } else {
            history.push(`/home?destFolderId=${e.folderId}&userId=${userId}`);
            getList();
          }
        };

        const onSelectChange = (key, e) => {
          // eslint-disable-next-line array-callback-return
          update({selectedRowKeys : key})
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
        };

        return (
          <div>
            <Table
              className="list_table"
              columns={columns}
              dataSource={fillList}
              showHeader={false}
              rowSelection={{
                selectedRowKeys,
                onChange: onSelectChange,
              }}
            />
          </div>
        );
      }
    )
  )
);
