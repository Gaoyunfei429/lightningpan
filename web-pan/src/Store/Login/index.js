import { action, extendObservable, runInAction } from "mobx";

const OBSERVABLE = {};

class Login {
  constructor() {
    extendObservable(this, { ...OBSERVABLE });
  }
  @action.bound update(data) {
    Object.assign(this, data);
  }
}

export default new Login();
