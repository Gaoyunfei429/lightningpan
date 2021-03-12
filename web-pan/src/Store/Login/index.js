import { message } from "antd";
import { action, extendObservable, runInAction } from "mobx";

import * as api from '../../Service/Login';

const OBSERVABLE = {
  userData: {}
};

class Login {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }

  @action.bound loginFnc = async (userId, password) => { // 之前没有用箭头函数的方式能获取到this，不知道为什么这次不行
    try {
      const data = await api.login({ userId, password })
      return data
    } catch (err) {
      message.error(err.msg || '出错啦~')
    }
  }

  @action.bound registerFnc = async (userId, username, password) => { // 之前没有用箭头函数的方式能获取到this，不知道为什么这次不行
    try {
      const data = await api.register({ userId, username, password })
      return data
    } catch (err) {
      message.error(err.msg || '出错啦~')
    }
  }
  
  @action.bound update(data) {
    Object.assign(this, data);
  }
}

export default new Login();
