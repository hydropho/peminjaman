<template>
  <div>
    <Navbar />
    <HomeContent />
  </div>
</template>

<script>
import Navbar from "@/components/Navbar.vue";
import HomeContent from "../components/home/HomeContent.vue";

export default {
  name: "HomePage",
  components: {
    Navbar,
    HomeContent,
  },
  computed: {
    currentUser() {
      return this.$store.state.user.user;
    },
  },
};
</script>

<style>
</style>