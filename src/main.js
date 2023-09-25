import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router'
import store from './store'
import VeeValidate from 'vee-validate'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(VeeValidate)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
