import React from "react";

import { Modal, message } from "antd";
import { observer, inject } from "mobx-react";

import { GetQueryString } from "../../Util"

import './DeleteModal.scss'
// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { 
    isDeleteModalVisible, 
    update, 
    deleteFilesAndFolders,
    selectedRow,
    getFileList
  }}) => {
    const closeModal = () => {
      update({ isDeleteModalVisible: false });
    };

    const onOk = () => {
      const destFolderId = GetQueryString("destFolderId");
      const userId = GetQueryString("userId");
      update({selectedRowKeys: null})
      deleteFilesAndFolders({
        srcFileIds: selectedRow.fileArr,
        srcFolderIds: selectedRow.folderArr,
      }).then(res=>{
        console.log(res);
        if (res.code === 200) {
          getFileList(userId, destFolderId).then(res=>{
            message.success('删除成功')
            update({
              selectedRow: {
                folderArr: [],
                fileArr: []
              }
            })
            closeModal()
          })
        }else {
          message.error('删除失败')
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
    }

    return (
      <Modal
        title="您确定删除吗？"
        okText="确定"
        cancelText="取消"
        visible={isDeleteModalVisible}
        onCancel={closeModal}
        onOk={onOk}
        className="delete_modal"
      >
      </Modal>
    );
  })
);
