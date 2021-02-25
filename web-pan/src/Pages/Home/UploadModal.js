import React from "react";

import { observer, inject } from "mobx-react";

import { Upload, Modal } from "antd";
import { InboxOutlined } from "@ant-design/icons";

const { Dragger } = Upload;

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isModalVisible, update } }) => {

    const closeModal = () => {
      update({ isModalVisible: !isModalVisible });
    };

    return (
      <Modal
        title="上传文件"
        visible={isModalVisible}
        onCancel={closeModal}
      >
        <Dragger
          name="mpfs"
          action="http://42.193.103.37:8080/uploadFile?destFolderId=1"
        >
          <p className="ant-upload-drag-icon">
            <InboxOutlined />
          </p>
          <p className="ant-upload-hint">上传文件或文件夹</p>
        </Dragger>
      </Modal>
    );
  })
);
