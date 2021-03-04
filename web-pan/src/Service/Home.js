import request from '../Config';

const BASE_URL = "http://42.193.103.37:8080/"

export function getFileList(param) {
    return request(`/getFoldersAndFiles`, {
      method: 'GET',
      data: param,
      baseURL: BASE_URL
    });
}

export function uploadFile(destFolderId, param) {
    return request(`/uploadFile?destFolderId=${destFolderId}`, {
      method: 'POST',
      data: param,
      baseURL: BASE_URL,
      headers: { "Content-Type": "multipart/form-data" }
    });
}

export function deleteFile(srcFileId) {
    return request(`/deleteFile?srcFileId=${srcFileId}`, {
      method: 'GET',
      baseURL: BASE_URL,
    });
}

export function creatFolder(param) {
  return request(`/makeDir`, {
    method: 'GET',
    data: param,
    baseURL: BASE_URL
  });
}