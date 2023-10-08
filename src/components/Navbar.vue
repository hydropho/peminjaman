<template>
  <nav class="navbar navbar-expand-lg sticky-top bg-white">
    <div class="container-fluid">
      <router-link to="/" class="text-decoration-none border-0 bg-transparent">
        <span class="navbar-brand">Peminjaman</span></router-link
      >
      <button
        class="navbar-toggler shadow-none border-0"
        type="button"
        data-bs-toggle="offcanvas"
        data-bs-target="#offcanvasNavbar"
        aria-controls="offcanvasNavbar"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div
        class="offcanvas offcanvas-end"
        tabindex="-1"
        id="offcanvasNavbar"
        aria-labelledby="offcanvasNavbarLabel"
      >
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Peminjaman</h5>
          <button
            type="button"
            class="btn-close shadow-none"
            data-bs-dismiss="offcanvas"
            aria-label="Close"
          ></button>
        </div>
        <div
          class="offcanvas-body d-flex align-items-lg-center flex-column flex-lg-row"
        >
          <ul class="navbar-nav justify-content-lg-center flex-grow-1">
            <li class="nav-item mb-2 mb-lg-0 mx-lg-2">
              <router-link to="/" class="nav-link px-3 px-lg-2">
                Home
              </router-link>
            </li>
            <li class="nav-item mb-2 mb-lg-0 mx-lg-2" v-if="currentUser">
              <router-link to="/item" class="nav-link px-3 px-lg-2">
                Item
              </router-link>
            </li>
          </ul>
          <div
            class="d-flex flex-column flex-lg-row justify-content-center align-items-center gap-3"
            v-if="!currentUser"
          >
            <router-link to="/login" class="nav-link">Login </router-link>
            <router-link
              to="/register"
              class="text-decoration-none rounded-pill btn btn-register"
              >Register</router-link
            >
          </div>
          <div class="d-none d-lg-block dropdown" v-if="currentUser">
            <button
              class="btn border-0 d-flex align-items-center p-0"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <span class="me-3"
                >Hello, {{ $store.getters["user/getFirstName"] }}</span
              >
              <i class="bi bi-caret-down-fill"></i>
            </button>
            <ul class="dropdown-menu dropdown-menu-end p-2 mt-3">
              <li>
                <router-link
                  class="btn w-100 d-flex align-items-center border-0"
                  to="/profile"
                  ><i class="bi bi-person fs-5 me-3"></i>Profile</router-link
                >
              </li>
              <hr class="m-2" />
              <li>
                <button
                  class="btn w-100 d-flex align-items-center border-0"
                  @click="logout"
                >
                  <i class="bi bi-box-arrow-right fs-5 me-3"></i>Logout
                </button>
              </li>
            </ul>
          </div>
          <div
            class="d-lg-none dropup d-flex align-items-center"
            v-if="currentUser"
          >
            <button
              class="btn border-0 w-100 d-flex justify-content-between py-3"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Hello,
              {{ $store.getters["user/getFirstName"] }}
              <i class="bi bi-caret-up-fill"></i>
            </button>
            <ul class="dropdown-menu p-2 w-100 m-0">
              <li>
                <router-link
                  class="btn w-100 d-flex align-items-center border-0"
                  to="/profile"
                  ><i class="bi bi-person fs-5 me-3"></i>Profile</router-link
                >
              </li>
              <hr class="m-2" />
              <li>
                <button
                  class="btn w-100 d-flex align-items-center border-0"
                  @click="logout"
                >
                  <i class="bi bi-box-arrow-right fs-5 me-3"></i>Logout
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </nav>
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
.btn-register {
  background-color: salmon;
  color: white;
}

@media (max-width: 992px) {
  .router-link-exact-active {
    background-color: salmon;
    color: white;
    border-radius: 0.5rem;
  }
}
@media (min-width: 992px) {
  .router-link-exact-active {
    border-bottom: 2px solid salmon;
  }
}
</style>