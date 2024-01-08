import { Layout, Login, Register } from "@/views/Auth";

export default {
  path: "/",
  component: Layout,
  children: [
    // { path: "", redirect: "login" },
    { path: "login", component: Login },
    { path: "register", component: Register },
  ],
};
