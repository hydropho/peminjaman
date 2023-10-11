<template>
  <form
    class="p-5 border rounded-3 d-flex flex-column align-items-center"
    @submit.prevent="login"
    method="POST"
  >
    <img src="@/assets/poltek.png" alt="logo-poltek" width="50" class="mb-5" />
    <h2 class="my-3 fw-bold">Welcome back</h2>
    <div class="mb-3 w-100">
      <label for="nim" class="form-label">NIM</label>
      <input
        type="number"
        class="form-control"
        id="nim"
        v-model="user.nim"
        v-validate="'required|min:7'"
        name="nim"
      />

      <p v-if="errors.has('nim')" class="text-danger">{{ nimError }}</p>
    </div>
    <div class="mb-3 w-100">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input
        type="password"
        class="form-control"
        id="exampleInputPassword1"
        v-model="user.password"
        v-validate="'required'"
        name="password"
      />
      <p v-if="errors.has('password')" class="text-danger">
        Password cannot be empty!
      </p>
    </div>
    <p v-if="message" class="text-danger">
      {{ message }}
    </p>
    <button type="submit" class="btn btn-primary w-100 mb-3">
      <span v-if="loading" class="spinner-border spinner-border-sm"></span
      ><span v-else>Login</span>
    </button>
    <h6>
      Don't have an account?
      <router-link to="/register">Register</router-link>
    </h6>
  </form>
</template>

<script>
export default {
  name: "FormLoginComponent",
  data() {
    return {
      user: {
        nim: "",
        password: "",
      },
      message: "",
      loading: false,
    };
  },
  watch: {
    'user.nim'() {
      if (this.user.nim.length > 7) {
        this.user.nim = this.user.nim.slice(0,7)
      } 
      this.message = "";
    },
    'user.password'() {
      this.message = "";
    }
  },
  computed: {
    nimError() {
      switch (this.errors.first('nim')) {
        case 'The nim field must be numeric and may contain decimal points':
          return 'NIM cannot be empty!'
        case 'The nim field must be at least 7 characters':
          return 'NIM must be at least 7 characters!';
        default:
          return '';
      }
    }
  },
  methods: {
    login() {
      this.message = "";
      this.$validator.validateAll().then((isValid) => {
        this.loading = true;
        if (isValid) {
          this.$store.dispatch("user/login", this.user).then(
            () => {
              this.$router.push("/");
              this.loading = false;
            }
          ).catch(
            error => {
              this.message = error.message;
              this.loading = false;
            }
          );
        }
        this.loading = false;
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