<template>
  <form
    class="p-5 border rounded-3 d-flex flex-column align-items-center"
    @submit.prevent="register"
  >
    <img src="@/assets/poltek.png" alt="logo-poltek" width="50" class="mb-5" />
    <h2 class="my-3 fw-bold">Create your account</h2>
    <div class="mb-3 w-100">
      <label for="nim" class="form-label">NIM</label>
      <input
        type="number"
        class="form-control"
        id="nim"
        v-model="user.nim"
        v-validate="'required|max:7'"
        name="nim"
      />

      <p v-if="errors.has('nim')" class="text-danger">
        {{ errors.first("nim") }}
      </p>
    </div>
    <div class="mb-3 w-100">
      <label for="name" class="form-label">Name</label>
      <input
        type="text"
        class="form-control"
        id="name"
        v-model="user.name"
        v-validate="'required'"
        name="name"
      />

      <p v-if="errors.has('name')" class="text-danger">Name cannot be empty!</p>
    </div>
    <div class="mb-3 w-100">
      <label for="password" class="form-label">Password</label>
      <input
        type="password"
        class="form-control"
        id="password"
        v-model="user.password"
        v-validate="'required|min:8'"
        name="password"
        ref="password"
      />
      <p v-if="errors.has('password')" class="text-danger">
        {{ errors.first("password") }}
      </p>
    </div>
    <div class="mb-3 w-100">
      <label for="confirmation-password" class="form-label"
        >Confirmation password</label
      >
      <input
        type="password"
        class="form-control"
        id="confirmation-password"
        v-model="confirmPassword"
        v-validate="'required|min:8|confirmed:password'"
        name="confirmation-password"
      />
      <p v-if="errors.has('confirmation-password')" class="text-danger">
        {{ errors.first("confirmation-password") }}
      </p>
    </div>
    <p v-if="message" class="text-danger">
      {{ message }}
    </p>
    <button
      type="submit"
      class="btn btn-primary w-100 mb-3"
      :disabled="loading"
    >
      <span v-if="loading" class="spinner-border spinner-border-sm"></span>
      <span v-else>Register</span>
    </button>
    <h6>
      Already have an account?
      <router-link to="/login">Login</router-link>
    </h6>
  </form>
</template>

<script>
import AuthService from "@/services/auth.service";

export default {
  name: "RegisterFormComponent",
  data() {
    return {
      user: {
        nim: "",
        name: "",
        password: "",
      },
      confirmPassword: "",
      message: "",
      loading: false,
    };
  },
  methods: {
    register() {
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          this.loading = true;
          AuthService.register(this.user).then(
            () => {
              this.$router.replace("/login");
              this.loading = false;
            },
            () => {
              this.message = "User already exist";
              this.loading = false;
            }
          );
        }
      });
    },
  },
};
</script>

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