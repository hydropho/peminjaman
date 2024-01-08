import Vue from "vue";
import { createPinia, PiniaVuePlugin } from "pinia";
import BootstrapVue, {
  IconsPlugin,
  ToastPlugin,
  ModalPlugin,
} from "bootstrap-vue";
import VeeValidate from "vee-validate";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

import App from "./App.vue";
import router from "./router";
import "./utils/extend.validator";

// import "./assets/main.css";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(ToastPlugin);
Vue.use(ModalPlugin);
Vue.use(PiniaVuePlugin);
Vue.use(VeeValidate, {
  inject: true,
  fieldsBagName: "veeFields",
  errorBagName: "veeErrors",
});

new Vue({
  router,
  pinia: createPinia(),
  render: (h) => h(App),
}).$mount("#app");
