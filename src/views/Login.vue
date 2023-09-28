<template>
  <div class="container-fluid vh-100">
    <div class="row justify-content-center align-items-center h-100">
      <LoginForm class="col-md-6 col-lg-4 col-xxl-3" />
    </div>
  </div>
</template>

<script>
import LoginForm from "../components/login/LoginForm.vue";

export default {
  name: "LoginPage",
  components: {
    LoginForm,
  },
  computed: {
    loggedIn() {
      return this.$store.state.user.loggedIn;
    },
  },
  created() {
    if (this.loggedIn) {
      this.$router.push("/");
    }
  },
};
</script>

<style>
</style>