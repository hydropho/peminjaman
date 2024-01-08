<script lang="ts">
import { useAuthStore } from "@/stores";

export default {
  name: "RegisterForm",
  data() {
    return {
      user: {
        nim: null as number | null,
        name: "" as string,
        password: "" as string,
      },
      confirmPassword: "" as string,
      loading: false,
    };
  },
  methods: {
    async register() {
      this.loading = true;

      const isValid = await this.$validator.validateAll();

      if (isValid) {
        const authStore = useAuthStore();

        const data = await authStore.register(this.user);

        if (data.success) {
          this.$root.$bvToast.toast(data.message + " Please login.", {
            title: "Success",
            variant: "success",
            solid: true,
          });

          this.$router.push("/login");
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
  <b-form
    @submit.prevent="register"
    class="d-flex flex-column align-items-center"
  >
    <b-img
      :src="require('@/assets/poltek.png')"
      alt="logo-poltek"
      width="50"
      class="mb-3"
    ></b-img>
    <h1 class="mb-2">Create account</h1>
    <p class="mb-4">Create an account to borrow some tools.</p>
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
        v-validate="{ required: true, min: 7 }"
        data-vv-as="NIM"
        :state="validateState('nim-input')"
        placeholder="NIM"
      ></b-form-input>
      <b-form-invalid-feedback id="nim-input-invalid-feedback">{{
        veeErrors.first("nim-input")
      }}</b-form-invalid-feedback>
    </b-form-group>

    <b-form-group
      id="name-group"
      label="Name"
      label-for="name-input"
      class="w-100"
    >
      <b-form-input
        id="name-input"
        name="name-input"
        v-model="user.name"
        type="text"
        v-validate="{ required: true }"
        data-vv-as="Name"
        :state="validateState('name-input')"
        placeholder="Name"
      ></b-form-input>
      <b-form-invalid-feedback id="name-input-invalid-feedback">{{
        veeErrors.first("name-input")
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
        ref="password-input"
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

    <b-form-group
      id="confirm-password-group"
      label="Confirm password"
      label-for="confirm-password-input"
      class="w-100"
    >
      <b-form-input
        id="confirm-password-input"
        name="confirm-password-input"
        v-model="confirmPassword"
        type="password"
        v-validate="{ required: true, min: 8, confirmed: 'password-input' }"
        data-vv-as="Confirm password"
        :state="validateState('confirm-password-input')"
        placeholder="Confirm password"
      ></b-form-input>
      <b-form-invalid-feedback id="confirm-password-input-invalid-feedback">{{
        veeErrors.first("confirm-password-input")
      }}</b-form-invalid-feedback>
    </b-form-group>

    <b-button
      type="submit"
      variant="primary"
      class="w-100 mt-3 mb-4 rounded-lg"
    >
      <b-spinner label="Spinning" v-if="loading"></b-spinner>
      <span v-else>Register</span>
    </b-button>
    <span>
      Already have an account?
      <router-link to="/login" class="h6">Login</router-link>
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
