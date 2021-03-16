import React from "react";

import { Modal } from "antd";
import { observer, inject } from "mobx-react";

import './DeleteModal.scss'
// eslint-disable-next-line import/no-anonymous-default-export
export default inject("home")(
  observer(({ home: { isDeleteModalVisible, update } }) => {
    const closeModal = () => {
      update({ isDeleteModalVisible: false });
    };

    return (
      <Modal
        title="您确定删除吗？"
        okText="确定"
        cancelText="取消"
        visible={isDeleteModalVisible}
        onCancel={closeModal}
        className="delete_modal"
      >
      </Modal>
    );
  })
);
