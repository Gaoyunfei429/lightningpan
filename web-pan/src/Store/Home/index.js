import { action, extendObservable } from "mobx";

import * as api from '../../Service/Home';

const OBSERVABLE = {
    loginState: true,
    name: 'aaa',
    fillList: [],
};

class Home {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }

  @action.bound getFileList = async (userId, destFoderId) => { // 之前没有用箭头函数的方式能获取到this，不知道为什么这次不行
    const data = await api.getFileList({ userId, destFoderId })
    console.log(data)
    this.fillList = this.fillList.concat(data.folders.concat(data.files))
    console.log(this.fillList)
  }
  
  @action.bound update(data) {
    Object.assign(this, data);
  }
}

export default new Home();
