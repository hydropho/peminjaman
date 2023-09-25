<template>
  <header class="navbar sticky-top navbar-expand-lg">
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
            <li class="nav-item d-lg-none">
              <router-link to="/login" class="nav-link">
                <a>Login</a>
              </router-link>
            </li>
            <li class="nav-item d-lg-none">
              <router-link to="/register" class="nav-link">
                <a>Register</a>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to="/" class="nav-link">
                <a>Home</a>
              </router-link>
            </li>
            <li class="nav-item">
              <router-link to="/item" class="nav-link">
                <a>Item</a>
              </router-link>
            </li>
            <li class="nav-item d-none d-lg-block">
              <router-link to="/login" class="nav-link">
                <a>Login</a>
              </router-link>
            </li>
            <li class="nav-item d-none d-lg-block">
              <router-link to="/register" class="nav-link">
                <a>Register</a>
              </router-link>
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
};
</script>

<style scoped>
.router-link-exact-active {
  color: red;
}
</style>