import React from 'react'

import { Breadcrumb } from 'antd'

import './index.scss'
// eslint-disable-next-line import/no-anonymous-default-export
export default () => {
  return (
    <Breadcrumb separator=">" className="list_bread">
        <Breadcrumb.Item>我的文件</Breadcrumb.Item>
    </Breadcrumb>
  )
}