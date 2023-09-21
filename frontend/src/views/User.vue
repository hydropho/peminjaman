<template>
  <div class="container">
    <Sidebar class="sidebar" />
    <User class="content" />
  </div>
</template>

<script>
import Sidebar from "../components/Sidebar";
import User from "../components/User";
export default {
  name: "UserPage",
  components: {
    Sidebar,
    User,
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.replace({ name: "login" }).catch(() => {});
    }
  },
};
</script>

<style lang="scss" scoped>
.container {
  min-height: 100vh;
  width: 100vw;
  display: flex;
  padding: 1rem;
  padding-right: 2rem;

  background-color: hsl(228, 100%, 98.04%);

  .content {
    margin-left: calc(19.5rem);
    flex: 1 1 0;

    @media (max-width: 768px) {
      margin-left: 2rem;
      padding-left: 6rem;
    }
  }
}
</style>