import React,{ useEffect } from "react";

import Header from "../../Components/Header";
import { observer, inject } from 'mobx-react';

export default (inject('home')(observer(({
  home: {
    loginState,
    name,
    getFileList
  }
}) => {
  useEffect(() => {
    getFileList(1)
  },[])
  return (
    <div>
      <Header isLogin={loginState} userName={name} />
    </div>
  );
})))
