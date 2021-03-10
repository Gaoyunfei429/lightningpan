import React,{ useState, useEffect } from "react";

import { observer, inject } from "mobx-react";
import { toJS } from "mobx";
import { Upload, Modal, message } from "antd";
import { InboxOutlined } from "@ant-design/icons";

const { Dragger } = Upload;

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { 
    isModalVisible, 
    update, 
    uploadFile,
    getFileList,
    fillList,
    deleteFile
   } }) => {
    const [uploadFileList, setUploadFileList] = useState([])
    const [uploadFileObj, setUploadFileObj] = useState({})
    const [uploadData, setUploadData] = useState({})

    const closeModal = () => {
      setUploadFileList([])
      update({ isModalVisible: !isModalVisible });
    };

    useEffect(() => {
      setFileStatus(uploadData, uploadFileObj)
    },[uploadData, uploadFileObj])

    const upload = async (e) => {
      let file = e.file;
      setUploadFileList([...uploadFileList, {name: file.name, status: 'uploading'}])
      const formdata = new FormData();
      formdata.append('mpfs', file);
      const data = await uploadFile(1, formdata)
      await setUploadData(data)
      await setUploadFileObj(file)
    }

    const setFileStatus = async (data, file) => {
      if(data.code === 200) {
        uploadFileList.forEach(item => {
          if(item.name === file.name) {
            item.status = 'done'
            getFileList(1)
            message.success('上传成功！')
          }else {
            return
          }
        })
        setUploadFileList([...uploadFileList])
      }else {
        uploadFileList.forEach(item => {
          if(item.name === file.name) {
            item.status = 'error'
            message.error('上传失败！')
          }else {
            return
          }
        })
        setUploadFileList([...uploadFileList])
      }
    }

    const deletePapers = (e) => {
      toJS(fillList).map(item => {
        if(item.fileName === e.name) {
          const data = deleteFile(item.fileId)
          if (data) {
            getFileList(1)
            const list = uploadFileList.filter(item => {
              return item.name != e.name
            })
            setUploadFileList(list)
            message.success('已删除！')
          }else {
            message.error('错误！')
          }
        }
      })
    }
    
    return (
      <Modal
        title="上传文件"
        visible={isModalVisible}
        onCancel={closeModal}
        footer={false}
      >
        <Dragger
          multiple
          customRequest={upload}
          fileList={uploadFileList}
          onRemove={deletePapers}
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
