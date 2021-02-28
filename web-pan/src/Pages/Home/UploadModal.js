import React,{ useState, useEffect } from "react";

import { observer, inject } from "mobx-react";

import { Upload, Modal, message } from "antd";
import { InboxOutlined } from "@ant-design/icons";

const { Dragger } = Upload;

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isModalVisible, update, uploadFile } }) => {
    const [uploadFileList, setUploadFileList] = useState([])
    const [uploadFileObj, setUploadFileObj] = useState({})
    const [uploadData, setUploadData] = useState({})

    const closeModal = () => {
      update({ isModalVisible: !isModalVisible });
    };

    useEffect(() => {
      console.log('effect', uploadFileList)
      setFileStatus(uploadData, uploadFileObj)
    },[uploadData, uploadFileObj])

    const upload = async (e) => {
      console.log('e', e)
      let file = e.file;
      console.log('file', file)
      setUploadFileList([...uploadFileList, {name: file.name, status: 'uploading'}])
      const formdata = new FormData();
      formdata.append('mpfs', file);
      const data = await uploadFile(1, formdata)
      setUploadData(data)
      setUploadFileObj(file)
    }

    const setFileStatus = async (data, file) => {
      console.log('data', data)
      console.log('file', file)
      if(data.code === 200) {
        console.log('item', uploadFileList)
        uploadFileList.forEach(item => {
          if(item.name === file.name) {
            console.log(item)
            item.status = 'done'
            // setUploadFileList([...uploadFileList, {...item, status: 'done'}])
          }else {
            return
          }
        })
        setUploadFileList([...uploadFileList])
      }else {
        uploadFileList.forEach(item => {
          if(item.name === file.name) {
            item.status = 'error'
            // setUploadFileList([...uploadFileList, {...item, status: 'error'}])
          }else {
            return
          }
        })
        setUploadFileList([...uploadFileList])
      }
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
          fileList={uploadFileList}
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
