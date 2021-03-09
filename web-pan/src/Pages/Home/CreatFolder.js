import React from "react";

import { observer, inject } from "mobx-react";
import { Modal, Input, Form, message } from "antd";

import { GetQueryString } from '../../Util'

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({
    home: {
      isCreatModalVisible,
      update,
      creatFolder,
      getFileList
    } }) => {
    const [form] = Form.useForm();

    const closeModal = () => {
      update({ isCreatModalVisible: !isCreatModalVisible });
    };

    const onFinish = async (e) => {
      console.log(e)
      const destFolderId = GetQueryString('destFolderId')
      const param = {
        ...e,
        destFolderId
      }
      console.log(param)
      const result = await creatFolder(param)
      console.log(result)
      if (result.code === 200 && result.msg === 'success') {
        await getFileList(1, 1)
        message.success('新建成功')
      }else {
        message.error('新建失败，请重试')
      }
      update({ isCreatModalVisible: !isCreatModalVisible });
    }

    const onOk = () => {
      form.submit()
    }

    return (
      <Modal
        visible={isCreatModalVisible}
        onCancel={closeModal}
        okText="确定"
        cancelText="取消"
        title="新建"
        onOk={onOk}
      >
        <Form
          form={form}
          onFinish={onFinish}
          initialValues={{
            srcFolderName: '新建文件夹',
          }}
        >
          <Form.Item
            name="srcFolderName"
            rules={[{ required: true, message: '请输入文件夹名字' }]}
          >
            <Input />
          </Form.Item>
        </Form>
      </Modal>
    );
  })
);
