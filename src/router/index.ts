import Vue from "vue";
import VueRouter from "vue-router";

import authRoutes from "./auth.routes";
import homeRoutes from "./home.routes";
// import HomePage from "@/views/Home/HomePage.vue";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  linkActiveClass: "active",
  routes: [
    // {
    //   path: "/",
    //   name: "home",
    //   component: HomePage,
    // },
    { ...homeRoutes },
    { ...authRoutes },
  ],
});

export default router;
