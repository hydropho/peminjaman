import axios from "axios"

const API_URL = 'http://localhost:8090/api/tool'

class ToolService {
    getAll() {
        return axios.get(API_URL + "/getAll")
    }
    getById(id) {
        return axios.get(API_URL + `/getById/${id}`)
    }
    add(data) {
        return axios.post(API_URL + '/add', data, {headers: {'Content-Type': 'multipart/form-data'}})
    }
    delete(id) {
        return axios.delete(API_URL + "/delete/" + id)
    }
}

export default new ToolService;