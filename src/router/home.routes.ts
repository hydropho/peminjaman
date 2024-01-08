import { Layout, Home, Item, Profile } from "@/views/Home";

export default {
  path: "/",
  component: Layout,
  children: [
    // { path: "", redirect: "home" },
    { path: "", component: Home },
    { path: "item", component: Item },
    { path: "profile", component: Profile },
  ],
};
