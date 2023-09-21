<template>
  <aside>
    <div class="sidebar-header">
      <h1 class="title">Peminjaman</h1>
    </div>
    <div class="sidebar-body">
      <ul>
        <router-link class="link-item" to="/" exact>
          <span class="material-symbols-outlined"> home </span>
          <li>Alat</li>
        </router-link>
        <router-link class="link-item" to="/kelola-item">
          <span class="material-symbols-outlined"> List </span>
          <li>Kelola Alat</li>
        </router-link>
        <!-- <router-link class="link-item" to="/penduduk">
          <span class="material-symbols-outlined"> group </span>
          <li>Penduduk</li>
        </router-link> -->
        <router-link class="link-item" to="/user">
          <span class="material-symbols-outlined"> person </span>
          <li>User</li>
        </router-link>
      </ul>
    </div>
    <a class="sidebar-footer link-item" @click.prevent="logOut">
      <span class="material-symbols-outlined"> logout </span>
      <p>Logout</p>
    </a>
  </aside>
</template>

<script>
export default {
  name: "SidebarComp",
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login").catch(() => {});
    },
  },
};
</script>

<style lang="scss" scoped>
aside {
  position: fixed;
  //   margin: 1rem 0 1rem 1rem;
  // min-height: (100%;
  min-height: calc(100vh - 2rem);
  // min-height: 100vh;
  // width: calc(4rem + 32px);
  border-radius: 1rem;
  background-color: white;
  padding: 2rem;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  transition: 0.3s ease;

  @media (max-width: 768px) {
    width: calc(4rem + 32px);
    position: fixed;
    z-index: 99;
  }

  .material-symbols-outlined {
    font-size: 1.5rem;
    margin-right: 1rem;

    @media (max-width: 768px) {
      font-size: 2rem;
      margin-right: 2rem;
    }
  }

  .sidebar-header {
    margin-bottom: 1rem;

    .title {
      color: hsl(241.21, 40.41%, 48.04%);
    }
  }

  .sidebar-body {
    ul {
      list-style: none;
    }
  }

  .link-item {
    text-decoration: none;
    color: hsl(221.74, 9.62%, 46.86%);
    display: flex;
    align-items: center;
    font-size: 1rem;
    padding: 0.5rem 1rem;
    border-radius: 0.7rem;
    margin-bottom: 0.5rem;
    cursor: pointer;

    li,
    p {
      padding: 0.5rem;
      width: 100%;
    }

    &:hover {
      color: white;
      background-color: hsl(241.21, 40.41%, 48.04%);
      //   background-color: white;
      width: 100%;
    }

    @media (max-width: 768px) {
      padding: 1rem 0;

      li,
      p {
        display: none;
      }
    }
  }
  .router-link-active,
  .router-link-exact-active {
    color: white;
    background-color: hsl(241.21, 40.41%, 48.04%);
  }

  .sidebar-footer {
    margin-top: auto;
  }
}
</style>