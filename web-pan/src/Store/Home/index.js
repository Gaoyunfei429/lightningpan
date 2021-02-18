import { action, extendObservable } from "mobx";

import * as api from '../../Service/Home';

const OBSERVABLE = {
    loginState: true,
    name: 'aaa',
    fillList: []
};

class Home {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }

  @action.bound async getFileList(userId, destFoderId) {
    const data = await api.getFileList({ userId, destFoderId })
    console.log(data)
  }
  
  @action.bound update(data) {
    Object.assign(this, data);
  }
}

export default new Home();
