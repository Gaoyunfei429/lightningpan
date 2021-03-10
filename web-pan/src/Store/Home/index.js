import { message } from "antd";
import { action, extendObservable, runInAction } from "mobx";

import * as api from '../../Service/Home';

const OBSERVABLE = {
    loginState: true,
    name: 'aaa',
    fillList: [],

    isModalVisible: false,

    selectedRow: {
      folderArr: [],
      fileArr: []
    },
    hasSelected: false,

    isCreatModalVisible: false,

    isTreeSelectModalVisible: false,
    copyOrMove: false,  // false代表复制，true代表移动
    
};

class Home {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }

  @action.bound getFileList = async (userId, destFolderId) => { // 之前没有用箭头函数的方式能获取到this，不知道为什么这次不行
    try {
      const { data } = await api.getFileList({ userId, destFolderId })
      runInAction(() => {
        this.fillList = []
        this.fillList = this.fillList.concat(data.folders.concat(data.files))
        this.fillList.forEach((item, index) => {
          item.key = index
          item.name = item.fileName || item.folderName
        });
        
      })
    } catch (err) {
      message.error(err.msg || '错误')
    }
  }

  @action.bound getTreeSelectData = async (userId, destFolderId) => {
    try {
      const { data } = await api.getFileList({ userId, destFolderId })
      return data
    } catch (err) {
      message.error(err.msg || '错误')
    }
  }

  @action.bound uploadFile = async (destFolderId, param) => {
      return await api.uploadFile(destFolderId, param)
  }
  
  @action.bound deleteFile = async (srcFileId) => {
    return await api.deleteFile(srcFileId)
  }

  @action.bound creatFolder = async (param) => {
    return await api.creatFolder(param)
  }

  @action.bound moveFilesAndFolders = async (param) => {
    return await api.moveFilesAndFolders(param)
  }

  @action.bound update = (data) => {
    Object.assign(this, data);
  }
}

export default new Home();
