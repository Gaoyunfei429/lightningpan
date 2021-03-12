import React, { useState } from "react";

import { Input, Button, Form } from "antd";
import { observer, inject } from "mobx-react";
import classnames from "classnames";

import "./Login.scss";

// eslint-disable-next-line import/no-anonymous-default-export
export default inject("login")(
  observer(({ login: { loginFnc, registerFnc } }) => {
    const [form] = Form.useForm();
    const [select, setSelect] = useState(1);

    const loginOrRegistered = (i) => {
      setSelect(i);
    };

    const onFinish = async (e) => {
        if(select === 1) {
            const data = await loginFnc(e.userId, e.passWord)  
            console.log(data)
        }else {
            const data = await registerFnc(e.userId, e.userName, e.passWord)
            console.log(data)
        }
    };

    return (
      <div className="login">
        <div className="content">
          <div className="span-btn">
            <span
              onClick={() => {
                loginOrRegistered(1);
              }}
              className={classnames({ selected: select === 1 })}
            >
              登录
            </span>
            <span className="point">.</span>
            <span
              onClick={() => {
                loginOrRegistered(2);
              }}
              className={classnames({ selected: select === 2 })}
            >
              注册
            </span>
          </div>
          <Form form={form} onFinish={onFinish}>
            {select === 2 && (
              <Form.Item
                name="userName"
                rules={[{ required: true, message: "请输入你的昵称" }]}
              >
                <Input className="input" placeholder="你的昵称" />
              </Form.Item>
            )}
            <Form.Item
              name="userId"
              rules={[{ required: true, message: "请输入你的手机号" }]}
            >
              <Input className="input" placeholder="手机号" />
            </Form.Item>
            <Form.Item
              name="passWord"
              rules={[{ required: true, message: "请输入你的密码" }]}
            >
              <Input.Password className="input" placeholder="密码" />
            </Form.Item>
            {select === 1 ? (
              <Form.Item
                name="login"
              >
                <Button className="btn" type="primary" htmlType="submit">
                  登录
                </Button>
              </Form.Item>
            ) : (
              <Form.Item
                name="register"
              >
                <Button className="btn" type="primary" htmlType="submit">
                  注册
                </Button>
              </Form.Item>
            )}
          </Form>
        </div>
      </div>
    );
  })
);
