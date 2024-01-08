import { defineStore } from "pinia";
import type { Auth, Register, Response } from "@/models";
import { authService } from "@/services/auth.service";

export const useAuthStore = defineStore({
  id: "auth",
  state: () => ({
    user: null,
    isLoggedIn: false,
  }),
  actions: {
    async login(auth: Auth): Promise<Response> {
      const response = await authService.login(auth);

      const data = {
        success: response.data.success,
        message: response.data.message,
      };

      if (data.success) {
        this.user = response.data.data;
        return data;
      } else {
        return data;
      }
    },
    async register(register: Register) {
      const response = await authService.register(register);

      const data = {
        success: response.data.success,
        message: response.data.message,
      };

      if (data.success) {
        return data;
      } else {
        return data;
      }
    },
  },
  getters: {},
});
