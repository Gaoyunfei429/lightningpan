import React, {useState} from 'react'
import { Input, Button } from 'antd';
import classnames from 'classnames'

import './Login.scss'

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
                <Input className="input" placeholder="密码" />
                <Button className="btn" type="primary">{select === 1 ? `登录` : `注册`}</Button>
            </div>
        </div>
    )
}