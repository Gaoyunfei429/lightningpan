import React, { useState } from "react";

import { Modal, TreeSelect, Form, message } from "antd";
import { observer, inject } from "mobx-react";

import { GetQueryString } from "../../Util"

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(
    ({
      home: {
        isTreeSelectModalVisible,
        copyOrMove,
        update,
        selectedRow,
        getTreeSelectData,
        moveFilesAndFolders,
        copyFilesAndFolders,
        getFileList,
      },
    }) => {
      const [form] = Form.useForm();
      const [aimsFolderId, setAimsFolderId] = useState("");
      const [treeData, setTreeData] = useState([
        { id: 1, value: 1, title: "根目录", isLeaf: false },
      ]);

      const closeModal = () => {
        form.setFieldsValue({destPath: undefined})
        setTreeData([
          { id: 1, value: 1, title: "根目录", isLeaf: false },
        ])
        update({ isTreeSelectModalVisible: false });
      };

      const onChange = (e) => {
        setAimsFolderId(e);
      };

      const onLoadData = ({ folderId }) =>
        new Promise(async (resolve, reject) => {
          const data = await getTreeSelectData(1, folderId);
          if (data) {
            data.folders.forEach((item) => {
              item.id = item.folderId;
              item.value = item.folderId;
              item.pId = item.parentId;
              item.title = item.folderName;
              item.isLeaf = false;
            });
            setTreeData([ ...treeData, ...data.folders])
            resolve();
          } else {
            message.error("出错啦~");
            reject();
          }
        });

      const onFinish = async () => {
        const destFolderId = GetQueryString("destFolderId");
        const userId = GetQueryString("userId");
        update({selectedRowKeys: null})
        copyOrMove ? 
        moveFilesAndFolders({
          srcFileIds: selectedRow.fileArr,
          srcFolderIds: selectedRow.folderArr,
          destFolderId: aimsFolderId,
        }).then(res=>{
          if (res.code === 200) {
            getFileList(userId, destFolderId).then(res=>{
              message.success('移动成功')
              update({
                selectedRow: {
                  folderArr: [],
                  fileArr: []
                }
              })
              closeModal()
            })
          }else {
            message.error('移动失败')
            update({
              selectedRow: {
                folderArr: [],
                fileArr: []
              }
            })
            closeModal()
          }
        }).catch(err => {
          message.error('服务器出错啦~')
        }):
        copyFilesAndFolders({
          srcFileIds: selectedRow.fileArr,
          srcFolderIds: selectedRow.folderArr,
          destFolderId: aimsFolderId,
        }).then(res=>{
          if (res.code === 200) {
            getFileList(userId, destFolderId).then(res=>{
              message.success('复制成功')
              update({
                selectedRow: {
                  folderArr: [],
                  fileArr: []
                }
              })
              closeModal()
            })
          }else {
            message.error('复制失败')
            update({
              selectedRow: {
                folderArr: [],
                fileArr: []
              }
            })
            closeModal()
          }
        }).catch(err => {
          message.error('服务器出错啦~')
        })
      };

      const onOk = () => {
        form.submit();
      };

      return (
        <Modal
          title={`${copyOrMove ? `移动` : `复制`}到`}
          visible={isTreeSelectModalVisible}
          onCancel={closeModal}
          okText="确定"
          cancelText="取消"
          onOk={onOk}
          destroyOnClose
        >
          <Form onFinish={onFinish} form={form}>
            <Form.Item
              name="destPath"
              rules={[{ required: true, message: "文件夹不能为空" }]}
            >
              <TreeSelect
                treeDataSimpleMode
                style={{ width: "100%" }}
                treeData={treeData}
                onChange={onChange}
                loadData={onLoadData}
                placeholder="请选择文件夹"
              />
            </Form.Item>
          </Form>
        </Modal>
      );
    }
  )
);
