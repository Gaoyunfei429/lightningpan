import React, {useState} from 'react'
import { Input, Button } from 'antd';
import classnames from 'classnames'

import './Login.scss'

// eslint-disable-next-line import/no-anonymous-default-export
export default () => {
    const [select, setSelect] = useState(1)

    const loginOrRegistered = (i) => {
        setSelect(i)
    }

    return (
        <div className="login">
            <div className="content">
                <div className="span-btn">
                    <span onClick={() => {loginOrRegistered(1)}} className={classnames({"selected": select===1})}>登录</span>
                    <span className="point">.</span>
                    <span onClick={() => {loginOrRegistered(2)}} className={classnames({"selected": select===2})}>注册</span>
                </div>
                {
                    select === 2 && <Input className="input" placeholder="你的昵称" />
                }
                <Input className="input" placeholder="手机号" />
                <Input.Password  className="input" placeholder="密码" />
                {
                    select === 1 ? 
                    <Button className="btn" type="primary">登录</Button> :
                    <Button className="btn" type="primary">注册</Button>
                }
            </div>
        </div>
    )
}