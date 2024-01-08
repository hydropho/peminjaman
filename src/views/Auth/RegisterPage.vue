<script lang="ts">
import RegisterForm from "@/components/register/RegisterForm.vue";
export default {
  name: "RegisterPage",
  components: {
    RegisterForm,
  },
};
</script>

<template>
  <div class="row justify-content-center align-items-center h-100">
    <RegisterForm class="col-md-6 col-lg-5 col-xl-3" />
  </div>
</template>
