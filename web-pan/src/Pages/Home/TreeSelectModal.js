import React, { useEffect, useState } from "react";

import { Modal, TreeSelect, Form } from "antd";
import { observer, inject } from "mobx-react";

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(
    ({
      home: {
        isTreeSelectModalVisible,
        copyOrMove,
        update,
        selectedRowKeys,
        getTreeSelectData,
      },
    }) => {
      const [form] = Form.useForm();
      const [aimsFolderId, setAimsFolderId] = useState("");
      const [treeData, setTreeData] = useState([{id:1, value:1, title: '根目录', isLeaf: false}])

      useEffect(() => {
          initData()
      }, []);

      const initData = async () => {
        const data = await getTreeSelectData(1);
        console.log(data)
        data.folders.forEach(item => {
            item.id = item.folderId
            item.value = item.folderId
            item.pId = item.parentId
            item.title = item.folderName
            item.isLeaf = false
        })
        setTreeData(treeData.concat(data.folders))
      }

      const closeModal = () => {
        update({ isTreeSelectModalVisible: false });
      };

      const onChange = (e) => {
        console.log(e);
      };

      const onLoadData = ({folderId}) => new Promise(async resolve => {
            const data = await getTreeSelectData(1, folderId);
            if(data) {
                console.log(data)
                resolve()
            }
        })
      

      return (
        <Modal
          title={`${copyOrMove ? `移动` : `复制`}到`}
          visible={isTreeSelectModalVisible}
          onCancel={closeModal}
          okText="确定"
          cancelText="取消"
        >
          <Form>
            <Form.Item
                name="destPath"
                rules={[{ required: true, message: '文件夹不能为空' }]}
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
