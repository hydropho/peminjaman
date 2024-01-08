<script lang="ts">
import Navbar from "@/components/Navbar.vue";

export default {
  name: "HomeLayout",
  components: {
    Navbar,
  },
  data() {
    return {
      sidebarOpen: true,
    };
  },
};
</script>

<template>
  <div>
    <Navbar></Navbar>

    <b-container fluid="xl" class="container-main">
      <router-view></router-view>
    </b-container>
  </div>
</template>

<style scoped>
.container-main {
  /* background-color: rgb(235, 242, 248); */
}
</style>
