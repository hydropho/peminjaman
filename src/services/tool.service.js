import axios from "axios"

const API_URL = 'http://localhost:8090/api/tool'

class ToolService {
    getAll() {
        return axios.get(API_URL + "/getAll")
    }
    delete(id) {
        return axios.delete(API_URL + "/delete/" + id)
    }
}

export default new ToolService;