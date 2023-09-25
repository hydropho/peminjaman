<template>
  <div
    class="row justify-content-center align-items-center"
    style="height: 100vh"
  >
    <LoginForm class="col-md-6 col-lg-4 col-xxl-3" />
  </div>
</template>

<script>
import LoginForm from "../components/login/LoginForm.vue";

export default {
  name: "LoginPage",
  components: {
    LoginForm,
  },
};
</script>

<style>
</style>