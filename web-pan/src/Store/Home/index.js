import { action, extendObservable } from "mobx";

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
    const data = await api.getFileList({ userId, destFoderId })
    this.fillList = this.fillList.concat(data.folders.concat(data.files))
  }
  
  @action.bound update = (data) => {
    Object.assign(this, data);
  }
}

export default new Home();
