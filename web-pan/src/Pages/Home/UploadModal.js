import React from "react";

import { observer, inject } from "mobx-react";

import { Upload, Modal, message } from "antd";
import { InboxOutlined } from "@ant-design/icons";

const { Dragger } = Upload;

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isModalVisible, update, uploadFile } }) => {

    const closeModal = () => {
      update({ isModalVisible: !isModalVisible });
    };

    const upload = async (e) => {
      let file = e.file;
      const formdata = new FormData();
      formdata.append('mpfs', file);
      uploadFile(1, formdata)
    }

    return (
      <Modal
        title="上传文件"
        visible={isModalVisible}
        onCancel={closeModal}
        onOk={closeModal}
      >
        <Dragger
          multiple
          customRequest={upload}
        >
          <p className="ant-upload-drag-icon">
            <InboxOutlined />
          </p>
          <p className="ant-upload-hint">上传文件</p>
        </Dragger>
      </Modal>
    );
  })
);
