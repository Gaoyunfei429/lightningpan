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
      console.log('e', e)
      e.file.status = 'done'
      let file = e.file;
      const formdata = new FormData();
      formdata.append('mpfs', file);
      const data = await uploadFile(1, formdata)
      console.log('data', data)
      if(data.code === 200) {

      }
    }

    const change = (e) => {
      console.log('change', e)
      e.fileList[0].status = 'done'
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
          onChange={change}
          showUploadList={false}
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
