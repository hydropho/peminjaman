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
          localStorage.setItem('user', JSON.stringify(response.data.data));
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
