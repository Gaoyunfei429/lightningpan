import request from '../Config';

const BASE_URL = "http://42.193.103.37:8080/"

export function getFileList(param) {
    return request(`/getFoldersAndFiles`, {
      method: 'GET',
      data: param,
      baseURL: BASE_URL
    });
}