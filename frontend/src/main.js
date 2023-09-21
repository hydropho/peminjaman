import Vue from 'vue'
import App from './App.vue'
import Vuex from 'vuex'
import VeeValidate from 'vee-validate'
// import 'bootstrap'
// import 'bootstrap/dist/css/bootstrap.min.css'
import { router } from './router'
import store from './store'
// import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

Vue.config.productionTip = false
Vue.use(Vuex)
Vue.use(VeeValidate)

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
