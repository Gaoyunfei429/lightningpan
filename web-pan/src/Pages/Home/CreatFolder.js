import React from "react";

import { observer, inject } from "mobx-react";
import { Modal, Input, Form } from "antd";

import { GetQueryString } from '../../Util'

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ 
    home: { 
      isCreatModalVisible, 
      update,
      creatFolder
    }}) => {
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
                srcFoldername: '新建文件夹',
            }}
            >
              <Form.Item 
                name="srcFoldername"
                rules={[{ required: true, message: '请输入文件夹名字' }]}
              >
                <Input />
              </Form.Item>
          </Form>
      </Modal>
    );
  })
);
