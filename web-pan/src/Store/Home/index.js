import { message } from "antd";
import { action, extendObservable, runInAction } from "mobx";

import * as api from '../../Service/Home';

const OBSERVABLE = {
    loginState: true,
    name: 'aaa',
    fillList: [],

    isModalVisible: false
};

class Home {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }

  @action.bound getFileList = async (userId, destFoderId) => { // 之前没有用箭头函数的方式能获取到this，不知道为什么这次不行
    try {
      const data = await api.getFileList({ userId, destFoderId })
      runInAction(() => {
        this.fillList = this.fillList.concat(data.folders.concat(data.files))
        this.fillList.forEach(item => {
          item.key = item.fileId
        });
      })
    } catch (err) {
      message.error(err.msg)
    }
  }

  @action.bound uploadFile = async (destFolderId, param) => {
      const data = await api.uploadFile(destFolderId, param)
      console.log('data', data)
  }
  
  @action.bound update = (data) => {
    Object.assign(this, data);
  }
}

export default new Home();
