<template>
  <header class="navbar bg-primary sticky-top navbar-expand-lg">
    <nav class="container-fluid flex-wrap flex-lg-nowrap">
      <a class="navbar-brand fs-1">Peminjaman</a>
      <button
        class="navbar-toggler d-lg-none"
        type="button"
        data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasNavbar"
        aria-controls="offcanvasNavbar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="offcanvas-lg offcanvas-end flex-grow-1"
        tabindex="-1"
        id="offcanvasNavbar"
        aria-labelledby="offcanvasNavbarLabel"
      >
        <div class="offcanvas-header">
          <h1 class="offcanvas-title" id="offcanvasNavbarLabel">Peminjaman</h1>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="offcanvas"
            data-bs-target="#offcanvasNavbar"
            aria-label="Close"
          ></button>
        </div>
        <div class="offcanvas-body py-0 justify-content-lg-end d-flex">
          <ul class="navbar-nav">
            <li class="nav-item d-lg-none" v-if="!currentUser">
              <router-link to="/login" class="nav-link">
                <a>Login</a>
              </router-link>
            </li>
            <li class="nav-item d-lg-none" v-if="!currentUser">
              <router-link to="/register" class="nav-link">
                <a>Register</a>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to="/" class="nav-link">
                <a>Home</a>
              </router-link>
            </li>
            <li class="nav-item" v-if="currentUser">
              <router-link to="/item" class="nav-link">
                <a>Item</a>
              </router-link>
            </li>
            <li class="nav-item d-none d-lg-block" v-if="!currentUser">
              <router-link to="/login" class="nav-link">
                <a>Login</a>
              </router-link>
            </li>
            <li class="nav-item d-none d-lg-block" v-if="!currentUser">
              <router-link to="/register" class="nav-link">
                <a>Register</a>
              </router-link>
            </li>
            <li class="nav-item d-none d-lg-block dropdown" v-if="currentUser">
              <button
                class="nav-link"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                {{ currentUser.name }}
              </button>
              <ul class="dropdown-menu dropdown-menu-end">
                <li>
                  <router-link class="dropdown-item" to="/profile"
                    >Profile</router-link
                  >
                </li>
                <li>
                  <button class="dropdown-item" @click.prevent="logout">
                    Logout
                  </button>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "NavbarComponent",
  computed: {
    currentUser() {
      return this.$store.state.user.user;
    },
  },
  methods: {
    logout() {
      this.$store.dispatch("user/logout");
      this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
.router-link-exact-active {
  color: red;
}
</style>