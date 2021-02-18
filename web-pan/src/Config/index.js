import axios from 'axios';
import { PATHS, history } from '../Router';

axios.defaults.withCredentials = true;

function request(url, options) {
  const method = (options.method || 'get').toLowerCase();
  const opts = {
    url,
    method,
    baseURL: options.baseURL || '',
    headers: options.headers || {}
  };
  const optionData = options.data || {};
  if (method === 'get') opts.params = optionData;
  else opts.data = optionData;
  return axios(opts)
    .then((res) => {
      const response = res || {};
      const { status } = response;
      if (status === 200) { // http请求没问题
        const { data, code } = response.data;
        if (code === 200) { // 如果接口请求没问题直接返回data，有问题则将错误信息全部返回
          return data;
        }
        // 登录超时， 跳登录
        if (code === 101) {
          history.push(PATHS.LOGIN);
        }

        return Promise.reject(response.data || '服务器错误');
      }
      return Promise.reject(new Error(res.msg));
    })
    .catch((err) => {
      if (!err.code) { // 如网络原因错误，打印信息
        // console.error(err.message);
      }
      return Promise.reject(err);
    });
}

// module.exports = request;
export default request;
