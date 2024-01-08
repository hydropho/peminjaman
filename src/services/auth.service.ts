import axios from "axios";
import type { AxiosInstance, AxiosResponse } from "axios";
import { API_URL } from "../config/api";
import type { Auth, Register } from "../models/Auth";

class AuthSerivce {
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create({
      baseURL: API_URL + "/auth",
    });
  }

  login(auth: Auth): Promise<AxiosResponse> {
    return this.axiosInstance.post("/login", auth);
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(register: Register): Promise<AxiosResponse> {
    return this.axiosInstance.post("/register", register);
  }
}

export const authService = new AuthSerivce();
