import React,{ useState } from "react";

import { observer, inject } from "mobx-react";

import { Upload, Modal, message } from "antd";
import { InboxOutlined } from "@ant-design/icons";

const { Dragger } = Upload;

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isModalVisible, update, uploadFile } }) => {
    const [uploadList, setUploadList] = useState([])

    const closeModal = () => {
      update({ isModalVisible: !isModalVisible });
    };

    const upload = async (e) => {
      console.log('e', e)
      let file = e.file;
      console.log('file', file)
      await setUploadList([{name: file.name, uid: file.uid, status: 'uploading'}])
      const formdata = new FormData();
      formdata.append('mpfs', file);
      const data = await uploadFile(1, formdata)
      console.log('data', data)
      if(data.code === 200) {
        await setUploadList([{name: file.name, uid: file.uid, status: 'done'}])
        console.log('uploadList', uploadList)
        uploadList.forEach(item => {
          console.log('item', item)
          if(item.uid === file.uid) {
            setUploadList([...uploadList, {...item, status: 'error'}])
          }else {
            return
          }
        })
      }else {
        uploadList.forEach(item => {
          if(item.uid === file.uid) {
            setUploadList([...uploadList, {...item, status: 'error'}])
          }else {
            return
          }
        })
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
          fileList={uploadList}
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
