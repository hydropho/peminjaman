import axios from 'axios';

const API_URL = 'http://localhost:8090/api/auth';

class AuthService {
  login(user) {
    return axios
      .post(API_URL + "/login", {
        nim: user.nim,
        password: user.password
      })
      .then(response => {
        if (response.data.success) {
          let data = response.data.data

          // set expire to 1 Day
          const ttl = 86400000
          data.expire = new Date().getTime() + ttl

          // create localstorage
          localStorage.setItem('user', JSON.stringify(data))
        }
        return response.data;
      });
  }

  logout() {
    localStorage.removeItem('user');
  }

  register(user) {
    return axios.post(API_URL + '/register', {
      nim: user.nim,
      name: user.name,
      password: user.password
    }).then(response => {
      return response.data;
    });
  }
}

export default new AuthService();
