<template>
  <div
    class="row justify-content-center align-items-center"
    style="height: 100vh"
  >
    <RegisterForm class="col-md-6 col-lg-4 col-xxl-3" />
  </div>
</template>

<script>
import RegisterForm from "../components/register/RegisterForm.vue";
export default {
  name: "RegisterPage",
  components: {
    RegisterForm,
  },
};
</script>

<style>
</style>