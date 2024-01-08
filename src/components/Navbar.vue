<script lang="ts">
export default {
  name: "NavbarComponent",
  data() {
    return {
      name: "Aswin",
    };
  },
};
</script>

<template>
  <b-navbar toggleable="lg" variant="white" sticky class="container-xl">
    <b-navbar-brand to="/" active-class="" exact-active-class="">
      <h3>Test</h3>
    </b-navbar-brand>
    <b-navbar-toggle
      target="nav-responsive"
      class="shadow-none border-0"
    ></b-navbar-toggle>

    <b-collapse is-nav>
      <b-navbar-nav class="flex-grow-1 justify-content-center">
        <b-nav-item to="/" active-class="">Home</b-nav-item>
        <b-nav-item to="/item" active-class="">Item</b-nav-item>
      </b-navbar-nav>
      <b-navbar-nav>
        <b-nav-item-dropdown :text="'Hi ' + name" right>
          <b-dropdown-item to="/profile" active-class="" exact-active-class="">
            Profile
          </b-dropdown-item>
          <b-dropdown-divider></b-dropdown-divider>
          <b-dropdown-item to="/" active-class="" exact-active-class="">
            Logout
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>

    <b-sidebar id="nav-responsive" no-header no-slide right width="400%">
      <template #default="{ hide }">
        <div class="h-100 d-flex flex-column">
          <div class="flex-grow-1">
            <b-container fluid class="py-2">
              <h3>Nav</h3>
              <button
                type="button"
                aria-label="Close"
                class="close"
                @click="hide"
              >
                <svg
                  viewBox="0 0 16 16"
                  width="1.8em"
                  height="1.8em"
                  focusable="false"
                  role="img"
                  aria-label="x"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  class="bi-x b-icon bi"
                >
                  <g>
                    <path
                      d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
                    ></path>
                  </g>
                </svg>
              </button>
            </b-container>
            <b-container fluid>
              <b-navbar-nav class="w-100">
                <b-nav-item to="/">Home</b-nav-item>
                <b-nav-item to="/item">Item</b-nav-item>
              </b-navbar-nav>
            </b-container>
          </div>

          <b-container fluid class="flex-column align-items-start mb-2">
            <div class="d-flex mb-3">
              <div class="rounded-circle bg-warning mr-3 circle-icon">AA</div>
              <div class="d-flex flex-column">
                <p class="m-0">Aswin Khairu Adnan</p>
                <p class="m-0">Administrator</p>
              </div>
            </div>
            <b-navbar-nav class="w-100">
              <b-nav-item to="/">
                <b-icon icon="pencil-square"></b-icon> Edit your profile
              </b-nav-item>
              <b-nav-item to="/item">
                <b-icon icon="box-arrow-right"></b-icon> Logout
              </b-nav-item>
            </b-navbar-nav>
          </b-container>
        </div>
      </template>
    </b-sidebar>
  </b-navbar>
</template>

<style scoped>
.circle-icon {
  width: 3rem;
  height: 3rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* .router-link-exact-active {
  background-color: pink;
} */
</style>
