<script lang="ts">
import { useAuthStore } from "@/stores";

export default {
  name: "LoginForm",
  data() {
    return {
      user: {
        nim: null as number | null,
        password: "" as string,
      },
      loading: false,
    };
  },
  methods: {
    async login() {
      this.loading = true;

      const isValid = await this.$validator.validateAll();

      if (isValid) {
        const authStore = useAuthStore();

        const data = await authStore.login(this.user);

        if (data.success) {
          this.$bvToast.toast(data.message, {
            title: "Success",
            variant: "success",
            solid: true,
          });
        } else {
          this.$bvToast.toast(data.message, {
            title: "Error",
            variant: "danger",
            solid: true,
          });
        }

        this.loading = false;
      } else {
        this.loading = false;
      }
    },
    validateState(ref: string): boolean | null {
      if (
        this.veeFields[ref] &&
        (this.veeFields[ref].dirty || this.veeFields[ref].validated)
      ) {
        return !this.veeErrors.has(ref);
      }
      return null;
    },
  },
  watch: {
    "user.nim"(newNim: number | null) {
      if (newNim !== null) {
        const nimString = newNim.toString();
        const maxLength = 7;

        if (nimString.length > maxLength) {
          const truncatedValue = parseInt(nimString.slice(0, maxLength), 10);
          this.$nextTick(() => {
            this.user.nim = truncatedValue;
          });
        }
      }
    },
  },
};
</script>

<template>
  <b-form @submit.prevent="login" class="d-flex flex-column align-items-center">
    <b-img
      :src="require('@/assets/poltek.png')"
      alt="logo-poltek"
      width="50"
      class="mb-3"
    ></b-img>
    <h1 class="mb-2">Login to app</h1>
    <p class="mb-4">Welcome back. Please enter your details.</p>
    <b-form-group
      id="nim-group"
      label="NIM"
      label-for="nim-input"
      class="w-100"
    >
      <b-form-input
        id="nim-input"
        name="nim-input"
        v-model="user.nim"
        type="number"
        v-validate="{ required: true, min: 7, max: 7 }"
        data-vv-as="NIM"
        :state="validateState('nim-input')"
        placeholder="NIM"
      ></b-form-input>
      <b-form-invalid-feedback id="nim-input-invalid-feedback">{{
        veeErrors.first("nim-input")
      }}</b-form-invalid-feedback>
    </b-form-group>

    <b-form-group
      id="password-group"
      label="Password"
      label-for="password-input"
      class="w-100"
    >
      <b-form-input
        id="password-input"
        name="password-input"
        v-model="user.password"
        type="password"
        v-validate="{ required: true, min: 8 }"
        data-vv-as="Password"
        :state="validateState('password-input')"
        placeholder="Password"
      ></b-form-input>
      <b-form-invalid-feedback id="password-input-invalid-feedback">{{
        veeErrors.first("password-input")
      }}</b-form-invalid-feedback>
    </b-form-group>

    <b-button
      type="submit"
      variant="primary"
      class="w-100 mt-3 mb-4 rounded-lg"
    >
      <b-spinner label="Spinning" v-if="loading"></b-spinner>
      <span class="font-weight-medium" v-else>Login</span>
    </b-button>
    <span>
      Don't have an account?
      <router-link to="/register" class="h6">Register</router-link>
    </span>
  </b-form>
</template>

<style scoped>
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

@media only screen and (max-width: 768px) {
  .border {
    border: none !important;
  }
}
</style>
