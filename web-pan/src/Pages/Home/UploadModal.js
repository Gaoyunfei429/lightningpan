import React from "react";

import { observer, inject } from "mobx-react";

import axios from 'axios';
import request from '../../Config';

import { Upload, Modal, Input } from "antd";
import { InboxOutlined } from '@ant-design/icons';

const { Dragger } = Upload;
const BASE_URL = "http://42.193.103.37:8080/"

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isModalVisible, update } }) => {
    const closeModal = () => {
      update({isModalVisible: !isModalVisible})
    }

    const upload = (file) => {
      console.log(file.target.files[0])
      let formData = new FormData();
      
      formData.append('file1', file.target.files[0]);
      formData.append('title', '哈哈');

      // axios.post('http://42.193.103.37:8080/uploadFile?destFolderId=1',formData,{
      //     headers: {
      //         'Content-Type': 'application/x-www-form-urlencoded'
      //     }
      // })

      console.log(formData)

      request(`/uploadFile?destFolderId=1`, {
        method: 'post',
        data: {
          formData
        },
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        baseURL: BASE_URL
      });
    }

    return <Modal
          title="上传文件"
          visible={isModalVisible}
          onCancel={closeModal}
    >
      <Input type="file" name="mpfs" onChange={upload}></Input>
      <Dragger
          multiple={true}
          directory
          action="http://42.193.103.37:8080/uploadFile"
      >
        <p className="ant-upload-drag-icon">
          <InboxOutlined />
        </p>
        <p className="ant-upload-hint">
          上传文件或文件夹
        </p>
      </Dragger>
    </Modal>;
  })
);
