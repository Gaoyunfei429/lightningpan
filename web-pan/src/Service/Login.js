import request from '../Config';

const BASE_URL = "http://42.193.103.37:8080/"

export function login(param) {
    return request(`/login`, {
      method: 'GET',
      data: param,
      baseURL: BASE_URL
    });
}

export function register(param) {
    return request(`/register`, {
      method: 'GET',
      data: param,
      baseURL: BASE_URL
    });
}
