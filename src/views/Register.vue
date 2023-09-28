<template>
  <div class="container-fluid vh-100">
    <div class="row justify-content-center align-items-center h-100">
      <RegisterForm class="col-md-6 col-lg-4 col-xxl-3" />
    </div>
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